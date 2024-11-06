package org.fsq.requestbodygenerator.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.fsq.requestbodygenerator.json.EntryJsonBuilder;
import org.fsq.requestbodygenerator.mapper.InputMapper;
import org.fsq.requestbodygenerator.mapper.InputTrimmer;
import org.fsq.requestbodygenerator.model.Entries;
import org.springframework.stereotype.Service;

@Service
public class InputProcessingService {

    private InputTrimmer inputTrimmer;

    private InputMapper inputMapper;

    private EntryJsonBuilder entryJsonBuilder;

    public InputProcessingService(InputTrimmer inputTrimmer, InputMapper inputMapper, EntryJsonBuilder entryJsonBuilder) {
        this.inputTrimmer = inputTrimmer;
        this.inputMapper = inputMapper;
        this.entryJsonBuilder = entryJsonBuilder;
    }

    public ObjectNode processInput(String input) {
       Entries entries = inputMapper.mapInputToEntries(inputTrimmer.trimAndPrepareInput(input));
       return entryJsonBuilder.buildJsonFromEntries(entries);
    }
}
