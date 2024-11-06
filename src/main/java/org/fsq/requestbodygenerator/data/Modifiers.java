package org.fsq.requestbodygenerator.data;

import java.util.Optional;

public enum Modifiers {
    PUBLIC("public"),PROTECTED("protected"),PRIVATE("private"),STATIC("static");

    private String text;

    Modifiers(String s) {
        this.text = s;
    }

    public boolean matches(String s) {
        return this.text.equalsIgnoreCase(s);
    }

    public static boolean isModifier(String s) {
        return findModifier(s).isPresent();
    }

    public static Optional<String> findModifier(String s) {
        for (Modifiers m : values()) {
            if (m.matches(s)) {
                return Optional.of(m.text);
            }
        }
        return Optional.empty();
    }
}
