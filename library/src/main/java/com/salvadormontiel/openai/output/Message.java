package com.salvadormontiel.openai.output;

import com.salvadormontiel.openai.input.ToolCall;
import com.squareup.moshi.Json;

import java.util.List;

public class Message {
    public String content;
    @Json(name = "tool_calls") public List<ToolCall> toolCalls;
    public String role;

    public Message(String content, List<ToolCall> toolCalls, String role) {
        this.content = content;
        this.toolCalls = toolCalls;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", toolCalls=" + toolCalls +
                ", role='" + role + '\'' +
                '}';
    }
}
