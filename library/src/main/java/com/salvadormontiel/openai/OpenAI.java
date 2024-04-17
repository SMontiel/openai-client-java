package com.salvadormontiel.openai;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class OpenAI {
    private final String apiKey;

    public OpenAI(String apiKey) {
        this.apiKey = notNull(apiKey);
    }

    public Chat chat() {
        return new Chat(apiKey);
    }
}
