package com.salvadormontiel.openai.input;

public class ResponseFormat {
    public String type;

    public ResponseFormat(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ResponseFormat{" +
                "type='" + type + '\'' +
                '}';
    }
}
