package com.salvadormontiel.openai.input;

import com.squareup.moshi.Json;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class ImageInput {
    public String prompt;
    public String model;
    public Integer n;
    public String quality;
    @Json(name = "response_format") public String responseFormat;
    public String size;
    public String style;
    public String user;

    public ImageInput(String prompt, String model, Integer n, String quality, String responseFormat, String size,
                      String style, String user) {
        this.prompt = notNull(prompt);
        this.model = model;
        this.n = n;
        this.quality = quality;
        this.responseFormat = responseFormat;
        this.size = size;
        this.style = style;
        this.user = user;
    }

    @Override
    public String toString() {
        return "ImageInput{" +
                "prompt='" + prompt + '\'' +
                ", model='" + model + '\'' +
                ", n=" + n +
                ", quality='" + quality + '\'' +
                ", responseFormat='" + responseFormat + '\'' +
                ", size='" + size + '\'' +
                ", style='" + style + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public static class Builder {
        private String prompt;
        private String model;
        private Integer n;
        private String quality;
        private String responseFormat;
        private String size;
        private String style;
        private String user;

        public Builder setPrompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setN(int n) {
            this.n = n;
            return this;
        }

        public Builder setQuality(String quality) {
            this.quality = quality;
            return this;
        }

        public Builder setResponseFormat(String responseFormat) {
            this.responseFormat = responseFormat;
            return this;
        }

        public Builder setSize(String size) {
            this.size = size;
            return this;
        }

        public Builder setStyle(String style) {
            this.style = style;
            return this;
        }

        public Builder setUser(String user) {
            this.user = user;
            return this;
        }

        public ImageInput build() {
            return new ImageInput(prompt, model, n, quality, responseFormat, size, style, user);
        }
    }
}
