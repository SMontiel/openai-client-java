package com.salvadormontiel.openai.output;

import com.squareup.moshi.Json;

import java.util.List;

public class LogProbs {
    public Content content;

    public LogProbs(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "LogProbs{" +
                "content=" + content +
                '}';
    }

    public static class Content {
        public String token;
        @Json(name = "logprob") public Double logProb;
        public List<String> bytes;
        @Json(name = "top_logprobs") public List<TopLogProbs> topLogProbs;

        public Content(String token, Double logProb, List<String> bytes, List<TopLogProbs> topLogProbs) {
            this.token = token;
            this.logProb = logProb;
            this.bytes = bytes;
            this.topLogProbs = topLogProbs;
        }

        @Override
        public String toString() {
            return "Content{" +
                    "token='" + token + '\'' +
                    ", logProb=" + logProb +
                    ", bytes=" + bytes +
                    ", topLogProbs=" + topLogProbs +
                    '}';
        }

        public static class TopLogProbs {
            public String token;
            public Double logprob;
            public List<String> bytes;

            public TopLogProbs(String token, Double logprob, List<String> bytes) {
                this.token = token;
                this.logprob = logprob;
                this.bytes = bytes;
            }

            @Override
            public String toString() {
                return "TopLogProbs{" +
                        "token='" + token + '\'' +
                        ", logprob=" + logprob +
                        ", bytes=" + bytes +
                        '}';
            }
        }
    }
}
