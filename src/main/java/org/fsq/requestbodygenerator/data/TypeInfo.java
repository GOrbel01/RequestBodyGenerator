package org.fsq.requestbodygenerator.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class TypeInfo {
    private Collections collections;
    private Types type;

    public TypeInfo(Types type) {
        this.type = type;
    }

    public boolean isCollectionType() {
        return collections != null;
    }

    public boolean isComplexType() {
        return type.isComplexType();
    }

    public Class<?> getCollectionType() {
        try {
            return Class.forName(collections.getCollectionType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
