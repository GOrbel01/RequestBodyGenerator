package org.fsq.requestbodygenerator.mapper;

import org.fsq.requestbodygenerator.data.Constants;
import org.fsq.requestbodygenerator.data.Modifiers;
import org.fsq.requestbodygenerator.exception.PojoDataInputException;
import org.fsq.requestbodygenerator.utils.StringUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InputTrimmer {
    /**
     * Trim and Format the Input String to be a valid format for
     * the application to handle
     * @param input Input String provided in the Request body
     * @return List of TypeAndNamePair with each trimmed and formatted
     * into a usable structure
     */
    public List<TypeAndNamePair> trimAndPrepareInput(String input) {
        if (input == null || input.isEmpty()) {
            throw new PojoDataInputException("Input cannot be null or blank");
        }
        String[] items = input.split(System.lineSeparator());
        List<TypeAndNamePair> typeAndNamePairs = new ArrayList<>();
        for (String s : items) {
            TypeAndNamePair entry = new TypeAndNamePair();
            if (s.length() < Constants.MIN_LENGTH) {
                throw new PojoDataInputException("Syntax cannot be correct with < 4 Characters on a Line"); //Maybe better to handle ex and just skip the line
            }
            String stripped = s.strip();
            if (s.charAt(0) != '@') { //Want to Ignore Annotations
                String[] split = StringUtil.splitAll(stripped, " ");
                if (split.length < 1) {
                    throw new PojoDataInputException("Each line must contain at least a Type declaration and variable name");
                }
                if (Modifiers.isModifier(split[0])) {
                    entry.setType(split[1]);
                    entry.setFieldName(split[2].replaceAll(";", ""));
                } else {
                    entry.setType(split[0]);
                    entry.setFieldName(split[1].replaceAll(";", ""));
                }
                typeAndNamePairs.add(entry);
            }
        }
        return typeAndNamePairs;
    }
}
