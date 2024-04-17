package com.salvadormontiel.openai.output;

import com.squareup.moshi.Json;

public class Choice {
    @Json(name = "finish_reason") public String finishReason;
    public int index;
    public Message message;
    @Json(name = "logprobs") public LogProbs logProbs;

    public Choice(String finishReason, int index, Message message, LogProbs logProbs) {
        this.finishReason = finishReason;
        this.index = index;
        this.message = message;
        this.logProbs = logProbs;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "finishReason='" + finishReason + '\'' +
                ", index=" + index +
                ", message=" + message +
                ", logProbs=" + logProbs +
                '}';
    }
}
