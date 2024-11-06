package org.fsq.requestbodygenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fsq.requestbodygenerator.data.TypeInfo;

@Data @AllArgsConstructor @NoArgsConstructor
public class Entry {
    private TypeInfo typeInfo;
    private String fieldName;
}
