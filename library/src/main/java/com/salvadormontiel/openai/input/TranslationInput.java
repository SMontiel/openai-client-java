package com.salvadormontiel.openai.input;

import com.salvadormontiel.openai.utils.Pair;
import com.squareup.moshi.Json;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class TranslationInput {
    public Path file;
    public String model;
    public String prompt;
    @Json(name = "response_format") public String responseFormat;
    public Double temperature;

    public TranslationInput(Path file, String model, String prompt, String responseFormat, Double temperature) {
        this.file = notNull(file);
        this.model = notNull(model);
        this.prompt = prompt;
        this.responseFormat = responseFormat;
        this.temperature = temperature;
    }

    public List<Pair<String, Object>> toMultiMap() {
        List<Pair<String, Object>> map = new ArrayList<>();
        map.add(Pair.of("file", this.file));
        map.add(Pair.of("model", this.model));
        if (this.prompt != null) {
            map.add(Pair.of("prompt", this.prompt));
        }
        if (this.responseFormat != null) {
            map.add(Pair.of("response_format", this.responseFormat));
        }
        if (this.temperature != null) {
            map.add(Pair.of("temperature", this.temperature));
        }

        return map;
    }

    @Override
    public String toString() {
        return "TranslationInput{" +
                "file=" + file +
                ", model='" + model + '\'' +
                ", prompt='" + prompt + '\'' +
                ", responseFormat='" + responseFormat + '\'' +
                ", temperature=" + temperature +
                '}';
    }

    public static class Builder {
        private Path file;
        private String model;
        private String prompt;
        private String responseFormat;
        private Double temperature;

        public Builder setFile(Path file) {
            this.file = file;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setPrompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public Builder setResponseFormat(String responseFormat) {
            this.responseFormat = responseFormat;
            return this;
        }

        public Builder setTemperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public TranslationInput build() {
            return new TranslationInput(file, model, prompt, responseFormat, temperature);
        }
    }
}
