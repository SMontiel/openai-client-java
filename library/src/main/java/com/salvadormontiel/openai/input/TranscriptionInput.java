package com.salvadormontiel.openai.input;

import com.salvadormontiel.openai.utils.Pair;
import com.squareup.moshi.Json;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class TranscriptionInput {
    public Path file;
    public String model;
    public String language;
    public String prompt;
    @Json(name = "response_format") public String responseFormat;
    public Double temperature;
    @Json(name = "timestamp_granularities[]") public List<String> timestampGranularities;

    public TranscriptionInput(Path file, String model, String language, String prompt, String responseFormat,
                              Double temperature, List<String> timestampGranularities) {
        this.file = notNull(file);
        this.model = notNull(model);
        this.language = language;
        this.prompt = prompt;
        this.responseFormat = responseFormat;
        this.temperature = temperature;
        this.timestampGranularities = timestampGranularities;
    }

    public List<Pair<String, Object>> toMultiMap() {
        List<Pair<String, Object>> map = new ArrayList<>();
        map.add(Pair.of("file", this.file));
        map.add(Pair.of("model", this.model));
        if (this.language != null) {
            map.add(Pair.of("language", this.language));
        }
        if (this.prompt != null) {
            map.add(Pair.of("prompt", this.prompt));
        }
        if (this.responseFormat != null) {
            map.add(Pair.of("response_format", this.responseFormat));
        }
        if (this.temperature != null) {
            map.add(Pair.of("temperature", this.temperature));
        }
        if (this.timestampGranularities != null) {
            for (String value : this.timestampGranularities) {
                map.add(Pair.of("timestamp_granularities[]", value));
            }
        }

        return map;
    }

    @Override
    public String toString() {
        return "TranscriptionInput{" +
                "file=" + file +
                ", model='" + model + '\'' +
                ", language='" + language + '\'' +
                ", prompt='" + prompt + '\'' +
                ", responseFormat='" + responseFormat + '\'' +
                ", temperature=" + temperature +
                ", timestampGranularities=" + timestampGranularities +
                '}';
    }

    public static class Builder {
        private Path file;
        private String model;
        private String language;
        private String prompt;
        private String responseFormat;
        private Double temperature;
        private List<String> timestampGranularities;

        public Builder setFile(Path file) {
            this.file = file;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setLanguage(String language) {
            this.language = language;
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

        public Builder addTimestampGranularity(String timestampGranularity) {
            if (this.timestampGranularities == null) {
                this.timestampGranularities = new ArrayList<>(2);
            }
            this.timestampGranularities.add(timestampGranularity);
            return this;
        }

        public TranscriptionInput build() {
            return new TranscriptionInput(file, model, language, prompt, responseFormat, temperature, timestampGranularities);
        }
    }
}
