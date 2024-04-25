package com.salvadormontiel.openai.input;

import com.salvadormontiel.openai.utils.Pair;
import com.squareup.moshi.Json;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class ImageVariationInput {
    public Path image;
    public String model;
    public Integer n;
    @Json(name = "response_format") public String responseFormat;
    public String size;
    public String user;

    public ImageVariationInput(Path image, String model, Integer n, String responseFormat, String size, String user) {
        this.image = notNull(image);
        this.model = model;
        this.n = n;
        this.responseFormat = responseFormat;
        this.size = size;
        this.user = user;
    }

    public List<Pair<String, Object>> toMultiMap() {
        List<Pair<String, Object>> map = new ArrayList<>();
        map.add(Pair.of("image", this.image));
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
        return "ImageVariationInput{" +
                "image=" + image +
                ", model='" + model + '\'' +
                ", n=" + n +
                ", responseFormat='" + responseFormat + '\'' +
                ", size='" + size + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public static class Builder {
        private Path image;
        private String model;
        private Integer n;
        private String responseFormat;
        private String size;
        private String user;

        public Builder setImage(Path image) {
            this.image = image;
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

        public Builder setResponseFormat(String responseFormat) {
            this.responseFormat = responseFormat;
            return this;
        }

        public Builder setSize(String size) {
            this.size = size;
            return this;
        }

        public Builder setUser(String user) {
            this.user = user;
            return this;
        }

        public ImageVariationInput build() {
            return new ImageVariationInput(image, model, n, responseFormat, size, user);
        }
    }
}
