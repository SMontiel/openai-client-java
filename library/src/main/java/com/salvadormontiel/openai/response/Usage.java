package com.salvadormontiel.openai.response;

import com.squareup.moshi.Json;

public class Usage {
    @Json(name = "completion_tokens") public int completionTokens;
    @Json(name = "prompt_tokens") public int promptTokens;
    @Json(name = "total_tokens") public int totalTokens;

    public Usage(int completionTokens, int promptTokens, int totalTokens) {
        this.completionTokens = completionTokens;
        this.promptTokens = promptTokens;
        this.totalTokens = totalTokens;
    }

    @Override
    public String toString() {
        return "Usage{" +
                "completionTokens=" + completionTokens +
                ", promptTokens=" + promptTokens +
                ", totalTokens=" + totalTokens +
                '}';
    }
}
