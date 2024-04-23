package com.salvadormontiel.openai.response;

import com.squareup.moshi.Json;

import java.util.List;

public class Embeddings {
    public String object;
    public List<Embedding> data;
    public String model;
    public Usage usage;

    public Embeddings(String object, List<Embedding> data, String model, Usage usage) {
        this.object = object;
        this.data = data;
        this.model = model;
        this.usage = usage;
    }

    @Override
    public String toString() {
        return "Embeddings{" +
                "object='" + object + '\'' +
                ", data=" + data +
                ", model='" + model + '\'' +
                ", usage=" + usage +
                '}';
    }

    public static class Embedding {
        public String object;
        public int index;
        public Object embedding;

        public Embedding(String object, int index, List<Double> embedding) {
            this.object = object;
            this.index = index;
            this.embedding = embedding;
        }

        public Embedding(String object, int index, String embedding) {
            this.object = object;
            this.index = index;
            this.embedding = embedding;
        }

        @Override
        public String toString() {
            return "Embedding{" +
                    "object='" + object + '\'' +
                    ", index=" + index +
                    ", embedding=" + embedding +
                    '}';
        }
    }

    public static class Usage {
        @Json(name = "prompt_tokens") public int promptTokens;
        @Json(name = "total_tokens") public int totalTokens;

        public Usage(int promptTokens, int totalTokens) {
            this.promptTokens = promptTokens;
            this.totalTokens = totalTokens;
        }

        @Override
        public String toString() {
            return "Usage{" +
                    "promptTokens=" + promptTokens +
                    ", totalTokens=" + totalTokens +
                    '}';
        }
    }
}
