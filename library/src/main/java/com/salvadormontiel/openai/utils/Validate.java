package com.salvadormontiel.openai.utils;

public class Validate {

    public static <T> T notNull(T value) {
        if (value == null) {
            throw new RuntimeException("Argument should not be null");
        }

        return value;
    }
}
