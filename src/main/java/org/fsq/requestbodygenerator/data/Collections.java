package org.fsq.requestbodygenerator.data;

public enum Collections {
    LIST("[]","List","Set"),MAP("Map");

    private String[] typedAs;

    Collections(String... typedAs) {
        this.typedAs = typedAs;
    }

    public boolean isListType() {
        return this == LIST;
    }

    public String getCollectionType() {
        return this == MAP ? "java.util.HashMap" : "java.util.ArrayList";
    }
}
