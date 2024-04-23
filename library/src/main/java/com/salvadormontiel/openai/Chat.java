package com.salvadormontiel.openai;

public class Chat {
    private final String apiKey;

    public Chat(String apiKey) {
        this.apiKey = apiKey;
    }

    public Completions completions() {
        return new Completions(apiKey);
    }
}
