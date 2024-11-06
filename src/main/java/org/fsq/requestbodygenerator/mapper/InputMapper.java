package org.fsq.requestbodygenerator.mapper;

import org.fsq.requestbodygenerator.data.Collections;
import org.fsq.requestbodygenerator.data.TypeInfo;
import org.fsq.requestbodygenerator.data.Types;
import org.fsq.requestbodygenerator.model.Entries;
import org.fsq.requestbodygenerator.model.Entry;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class InputMapper {

    /**
     * Take set of TypeAndNamePair and convert them into
     * Entry classes. Returning an Entries wrapper for them
     * @param pairs Input as TypeAndNamePair(String,String)
     * @return Mapped set of Entries in the Entries wrapper
     */
    public Entries mapInputToEntries(List<TypeAndNamePair> pairs) {
        Entries entries = new Entries();
        for (TypeAndNamePair p : pairs) {
            Entry e = new Entry();
            e.setFieldName(p.getFieldName());

            TypeInfo t = new TypeInfo();
            checkAndSetCollectionType(p.getType(),t);
            checkAndSetDataType(p.getType(),t);

            e.setTypeInfo(t);

            entries.addEntry(e);
        }
        return entries;
    }

    /**
     * Check which collection types are present for Type
     * List. Array and Set are all mapped as LIST (because they produce the same
     * JSON data). Would need to change if Nesting/Multidimensional arrays supported
     * @param typeStr Type String from TypeAndNamePair containing type data
     * @param typeInfo Mapped type class to build
     */
    public void checkAndSetCollectionType(String typeStr, TypeInfo typeInfo) {
        if (typeStr.contains("List")) {
            typeInfo.setCollections(Collections.LIST);
        } else if (typeStr.contains("[]")) {
            typeInfo.setCollections(Collections.LIST);
        } else if (typeStr.contains("Set")) {
            typeInfo.setCollections(Collections.LIST);
        } else if (typeStr.contains("Map")) {
            String[] split = typeStr.split(","); //[0] = Key [1] = Value TODO in Next Iteration
            typeInfo.setCollections(Collections.MAP);
        }
    }

    /**
     * Uses the Enum to determine the appropriate mapping
     * @see Types#findType
     * @param typeStr Type String from TypeAndNamePair containing type data
     * @param typeInfo Mapped type class to build
     */
    public void checkAndSetDataType(String typeStr, TypeInfo typeInfo) {
        Types t = Types.findType(typeStr);
        typeInfo.setType(t);
    }
}
