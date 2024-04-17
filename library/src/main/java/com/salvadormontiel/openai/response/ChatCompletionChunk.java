package com.salvadormontiel.openai.response;

import com.salvadormontiel.openai.output.LogProbs;
import com.squareup.moshi.Json;

import java.util.List;

public class ChatCompletionChunk {
    public String id;
    public List<Choice> choices;
    public int created;
    public String model;
    @Json(name = "system_fingerprint") public String systemFingerprint;
    public String object;

    public ChatCompletionChunk(String id, List<Choice> choices, int created, String model, String systemFingerprint, String object) {
        this.id = id;
        this.choices = choices;
        this.created = created;
        this.model = model;
        this.systemFingerprint = systemFingerprint;
        this.object = object;
    }

    @Override
    public String toString() {
        return "ChatCompletionChunk{" +
                "id='" + id + '\'' +
                ", choices=" + choices +
                ", created=" + created +
                ", model='" + model + '\'' +
                ", systemFingerprint='" + systemFingerprint + '\'' +
                ", object='" + object + '\'' +
                '}';
    }

    public static class Choice {
        public Delta delta;
        @Json(name = "logprobs") public LogProbs logProbs;
        @Json(name = "finish_reason") public String finishReason;
        public int index;

        public Choice(Delta delta, LogProbs logProbs, String finishReason, int index) {
            this.delta = delta;
            this.logProbs = logProbs;
            this.finishReason = finishReason;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Choice{" +
                    "delta=" + delta +
                    ", logProbs=" + logProbs +
                    ", finishReason='" + finishReason + '\'' +
                    ", index=" + index +
                    '}';
        }

        public static class Delta {
            public String content;
            @Json(name = "tool_calls") public List<ToolCall> toolCalls;
            public String role;

            public Delta(String content, List<ToolCall> toolCalls, String role) {
                this.content = content;
                this.toolCalls = toolCalls;
                this.role = role;
            }

            @Override
            public String toString() {
                return "Delta{" +
                        "content='" + content + '\'' +
                        ", toolCalls=" + toolCalls +
                        ", role='" + role + '\'' +
                        '}';
            }

            public static class ToolCall {
                public int index;
                public String id;
                public String type;
                public Function function;

                public ToolCall(int index, String id, String type, Function function) {
                    this.index = index;
                    this.id = id;
                    this.type = type;
                    this.function = function;
                }

                @Override
                public String toString() {
                    return "ToolCall{" +
                            "index=" + index +
                            ", id='" + id + '\'' +
                            ", type='" + type + '\'' +
                            ", function=" + function +
                            '}';
                }

                public static class Function {
                    public String name;
                    public String arguments;

                    public Function(String name, String arguments) {
                        this.name = name;
                        this.arguments = arguments;
                    }

                    @Override
                    public String toString() {
                        return "Function{" +
                                "name='" + name + '\'' +
                                ", arguments='" + arguments + '\'' +
                                '}';
                    }
                }
            }
        }
    }
}
