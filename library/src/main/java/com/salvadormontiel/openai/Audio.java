package com.salvadormontiel.openai;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class Audio {
    private final String apiKey;

    public Audio(String apiKey) {
        this.apiKey = notNull(apiKey);
    }

    public Speech speech() {
        return new Speech(apiKey);
    }

    public Transcriptions transcriptions() {
        return new Transcriptions(apiKey);
    }

    public Translations translations() {
        return new Translations(apiKey);
    }
}
