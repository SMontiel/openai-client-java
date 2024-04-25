package com.salvadormontiel.openai.input;

import com.salvadormontiel.openai.utils.Pair;
import com.squareup.moshi.Json;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class ImageEditInput {
    public Path image;
    public String prompt;
    public Path mask;
    public String model;
    public Integer n;
    public String size;
    @Json(name = "response_format") public String responseFormat;
    public String user;

    public ImageEditInput(Path image, String prompt, Path mask, String model, Integer n, String size,
                          String responseFormat, String user) {
        this.image = notNull(image);
        this.prompt = notNull(prompt);
        this.mask = mask;
        this.model = model;
        this.n = n;
        this.size = size;
        this.responseFormat = responseFormat;
        this.user = user;
    }

    public List<Pair<String, Object>> toMultiMap() {
        List<Pair<String, Object>> map = new ArrayList<>();
        map.add(Pair.of("image", this.image));
        map.add(Pair.of("prompt", this.prompt));
        if (this.mask != null) {
            map.add(Pair.of("mask", this.mask));
        }
        if (this.model != null) {
            map.add(Pair.of("model", this.model));
        }
        if (this.n != null) {
            map.add(Pair.of("n", this.n));
        }
        if (this.size != null) {
            map.add(Pair.of("size", this.size));
        }
        if (this.responseFormat != null) {
            map.add(Pair.of("response_format", this.responseFormat));
        }
        if (this.user != null) {
            map.add(Pair.of("user", this.user));
        }

        return map;
    }

    @Override
    public String toString() {
        return "ImageEditInput{" +
                "image=" + image +
                ", prompt='" + prompt + '\'' +
                ", mask=" + mask +
                ", model='" + model + '\'' +
                ", n=" + n +
                ", size='" + size + '\'' +
                ", responseFormat='" + responseFormat + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public static class Builder {
        private Path image;
        private String prompt;
        private Path mask;
        private String model;
        private Integer n;
        private String size;
        private String responseFormat;
        private String user;

        public Builder setImage(Path image) {
            this.image = image;
            return this;
        }

        public Builder setPrompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public Builder setMask(Path mask) {
            this.mask = mask;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setN(Integer n) {
            this.n = n;
            return this;
        }

        public Builder setSize(String size) {
            this.size = size;
            return this;
        }

        public Builder setResponseFormat(String responseFormat) {
            this.responseFormat = responseFormat;
            return this;
        }

        public Builder setUser(String user) {
            this.user = user;
            return this;
        }

        public ImageEditInput build() {
            return new ImageEditInput(image, prompt, mask, model, n, size, responseFormat, user);
        }
    }
}
