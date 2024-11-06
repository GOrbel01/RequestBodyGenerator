package org.fsq.requestbodygenerator.data;

import java.util.Arrays;

public enum Types {
    LONG(Long.class,"Long","long"),INT(Integer.class,"int","Integer"),SHORT(Short.class,"Short","short"),
    BYTE(Byte.class,"Byte","byte"),BOOLEAN(Boolean.class,"Boolean","boolean"),STRING(String.class,"String"),COMPLEX(Object.class,"Complex");

    private String[] typedAs;
    private Class<?> collectionType;

    Types(Class<?> collectionType, String... typedAs) {
        this.typedAs = typedAs;
        this.collectionType = collectionType;
    }

    public boolean isNumeric() {
        return this == LONG || this == INT || this == SHORT || this == BYTE;
    }

    /**
     * Attempts to see if there is a match for
     * Input String with one of the Enum types
     * Initially attempts to see if an exact match is found
     * otherwise search if the in contains the typedAs
     * @param in Input String
     * @return True if matched false otherwise
     */
    public boolean matches(String in) {
        boolean res = Arrays.asList(this.typedAs).contains(in);
        if (!res) {
            return containsMatch(in);
        }
        return res;
    }

    /**
     * Takes an Input String and attempts to
     * find if the input String exists as part of one of the typedAs fields
     * @param in String to match
     * @return True if found, false otherwise
     */
    public boolean containsMatch(String in) {
        for (String s : this.typedAs) {
            if (in.contains(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean isComplexType() {
        return this == COMPLEX;
    }

    public static Types findType(String type) {
        for (Types t : values()) {
            if (t.matches(type)) {
                return t;
            }
        }
        return COMPLEX;
    }
}
