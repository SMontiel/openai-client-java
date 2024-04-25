package com.salvadormontiel.openai;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class OpenAI {
    private final String apiKey;

    public OpenAI(String apiKey) {
        this.apiKey = notNull(apiKey);
    }

    public Audio audio() {
        return new Audio(apiKey);
    }

    public Chat chat() {
        return new Chat(apiKey);
    }

    public Embeddings embeddings() {
        return new Embeddings(apiKey);
    }

    public Images images() {
        return new Images(apiKey);
    }

    public Models models() {
        return new Models(apiKey);
    }
}
