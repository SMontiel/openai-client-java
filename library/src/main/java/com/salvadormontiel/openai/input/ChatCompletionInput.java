package com.salvadormontiel.openai.input;

import com.squareup.moshi.Json;

import java.util.List;
import java.util.Map;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class ChatCompletionInput {
    public List<ChatMessage> messages;
    public String model;
    @Json(name = "frequency_penalty") public int frequencyPenalty;
    @Json(name = "logit_bias") public Map<String, Integer> logitBias;
    public boolean logprobs;
    @Json(name = "top_logprobs") public Integer topLogprobs;
    @Json(name = "max_tokens") public Integer maxTokens;
    public Integer n;
    @Json(name = "presence_penalty") public Double presencePenalty;
    @Json(name = "response_format") public ResponseFormat responseFormat;
    public Integer seed;
    public Object stop;
    public Boolean stream;
    public Double temperature;
    @Json(name = "top_p") public Double topP;
    public List<Tool> tools;
    @Json(name = "tool_choice") public Object toolChoice;
    public String user;

    ChatCompletionInput(List<ChatMessage> messages, String model, int frequencyPenalty, Map<String, Integer> logitBias,
                        boolean logprobs, Integer topLogprobs, Integer maxTokens, Integer n, Double presencePenalty,
                        ResponseFormat responseFormat, Integer seed, Object stop, Boolean stream, Double temperature,
                        Double topP, List<Tool> tools, Object toolChoice, String user) {
        this.messages = notNull(messages);
        this.model = notNull(model);
        this.frequencyPenalty = frequencyPenalty;
        this.logitBias = logitBias;
        this.logprobs = logprobs;
        this.topLogprobs = topLogprobs;
        this.maxTokens = maxTokens;
        this.n = n;
        this.presencePenalty = presencePenalty;
        this.responseFormat = responseFormat;
        this.seed = seed;
        this.stop = stop;
        this.stream = stream;
        this.temperature = temperature;
        this.topP = topP;
        this.tools = tools;
        this.toolChoice = toolChoice;
        this.user = user;
    }

    @Override
    public String toString() {
        return "ChatCompletionInput{" +
                "messages=" + messages +
                ", model='" + model + '\'' +
                ", frequencyPenalty=" + frequencyPenalty +
                ", logitBias=" + logitBias +
                ", logprobs=" + logprobs +
                ", topLogprobs=" + topLogprobs +
                ", maxTokens=" + maxTokens +
                ", n=" + n +
                ", presencePenalty=" + presencePenalty +
                ", responseFormat=" + responseFormat +
                ", seed=" + seed +
                ", stop=" + stop +
                ", stream=" + stream +
                ", temperature=" + temperature +
                ", topP=" + topP +
                ", tools=" + tools +
                ", toolChoice=" + toolChoice +
                ", user='" + user + '\'' +
                '}';
    }

    public static class Builder {
        private List<ChatMessage> messages;
        private String model;
        private int frequencyPenalty = 0;
        private Map<String, Integer> logitBias;
        private boolean logprobs = false;
        private Integer topLogprobs;
        private Integer maxTokens;
        private Integer n = 1;
        private Double presencePenalty = 0.0;
        private ResponseFormat responseFormat;
        private Integer seed;
        private Object stop;
        private Boolean stream = false;
        private Double temperature = 1.0;
        private Double topP = 1.0;
        private List<Tool> tools;
        private Object toolChoice;
        private String user;

        public Builder setMessages(List<ChatMessage> messages) {
            this.messages = messages;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setFrequencyPenalty(int frequencyPenalty) {
            this.frequencyPenalty = frequencyPenalty;
            return this;
        }

        public Builder setLogitBias(Map<String, Integer> logitBias) {
            this.logitBias = logitBias;
            return this;
        }

        public Builder setLogprobs(boolean logprobs) {
            this.logprobs = logprobs;
            return this;
        }

        public Builder setTopLogprobs(Integer topLogprobs) {
            this.topLogprobs = topLogprobs;
            return this;
        }

        public Builder setMaxTokens(Integer maxTokens) {
            this.maxTokens = maxTokens;
            return this;
        }

        public Builder setN(Integer n) {
            this.n = n;
            return this;
        }

        public Builder setPresencePenalty(Double presencePenalty) {
            this.presencePenalty = presencePenalty;
            return this;
        }

        public Builder setResponseFormat(ResponseFormat responseFormat) {
            this.responseFormat = responseFormat;
            return this;
        }

        public Builder setSeed(Integer seed) {
            this.seed = seed;
            return this;
        }

        public Builder setStop(String stop) {
            this.stop = stop;
            return this;
        }

        public Builder setStop(List<Object> stop) {
            this.stop = stop;
            return this;
        }

        public Builder setStream(boolean stream) {
            this.stream = stream;
            return this;
        }

        public Builder setTemperature(Double temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder setTopP(Double topP) {
            this.topP = topP;
            return this;
        }

        public Builder setTools(List<Tool> tools) {
            this.tools = tools;
            return this;
        }

        public Builder setToolChoice(String toolChoice) {
            this.toolChoice = toolChoice;
            return this;
        }

        public Builder setToolChoice(ToolChoice toolChoice) {
            this.toolChoice = toolChoice;
            return this;
        }

        public Builder setUser(String user) {
            this.user = user;
            return this;
        }

        public ChatCompletionInput build() {
            return new ChatCompletionInput(messages, model, frequencyPenalty, logitBias, logprobs, topLogprobs, maxTokens, n, presencePenalty, responseFormat, seed, stop, stream, temperature, topP, tools, toolChoice, user);
        }
    }
}
