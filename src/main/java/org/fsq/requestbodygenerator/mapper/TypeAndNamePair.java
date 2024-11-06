package org.fsq.requestbodygenerator.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class TypeAndNamePair {
    private String type;
    private String fieldName;
}
