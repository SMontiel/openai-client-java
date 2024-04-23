package com.salvadormontiel.openai.input;

import com.squareup.moshi.Json;

import java.util.List;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class EmbeddingsInput {
    public Object input;
    public String model;
    @Json(name = "encoding_format") public String encodingFormat;
    public Integer dimensions;
    public String user;

    public EmbeddingsInput(Object input, String model, String encodingFormat, Integer dimensions, String user) {
        this.input = notNull(input);
        this.model = notNull(model);
        this.encodingFormat = encodingFormat;
        this.dimensions = dimensions;
        this.user = user;
    }

    @Override
    public String toString() {
        return "EmbeddingsInput{" +
                "input=" + input +
                ", model='" + model + '\'' +
                ", encodingFormat='" + encodingFormat + '\'' +
                ", dimensions=" + dimensions +
                ", user='" + user + '\'' +
                '}';
    }

    public static class Builder {
        private Object input;
        private String model;
        private String encodingFormat;
        private Integer dimensions;
        private String user;

        public Builder setInput(String input) {
            this.input = input;
            return this;
        }

        public Builder setInputAsStrings(List<String> input) {
            this.input = input;
            return this;
        }

        public Builder setInputAsIntegers(List<Integer> input) {
            this.input = input;
            return this;
        }

        public Builder setInputAsListOfLists(List<List<Integer>> input) {
            this.input = input;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setEncodingFormat(String encodingFormat) {
            this.encodingFormat = encodingFormat;
            return this;
        }

        public Builder setDimensions(Integer dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder setUser(String user) {
            this.user = user;
            return this;
        }

        public EmbeddingsInput build() {
            return new EmbeddingsInput(input, model, encodingFormat, dimensions, user);
        }
    }
}
