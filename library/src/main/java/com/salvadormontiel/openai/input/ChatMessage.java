package com.salvadormontiel.openai.input;

import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class ChatMessage {
    public String role;
    public String name;
    public Object content;
    @Json(name = "tool_calls") public List<ToolCall> toolCalls;
    @Json(name = "tool_call_id") public String toolCallId;

    ChatMessage(@NotNull String role, String name, Object content, List<ToolCall> toolCalls, String toolCallId) {
        this.role = notNull(role);
        this.name = name;
        this.content = content;
        this.toolCalls = toolCalls;
        this.toolCallId = toolCallId;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "role='" + role + '\'' +
                (name != null ? (", name='" + name + '\'') : "") +
                ", content=" + content +
                (toolCalls != null ? (", toolCalls=" + toolCalls) : "") +
                (toolCallId != null ? (", toolCallId=" + toolCallId) : "") +
                '}';
    }

    public static class Builder {

        public SystemBuilder asSystemRole() {
            return new SystemBuilder();
        }

        public UserBuilder asUserRole() {
            return new UserBuilder();
        }

        public AssistantBuilder asAssistantRole() {
            return new AssistantBuilder();
        }

        public ToolBuilder asToolRole() {
            return new ToolBuilder();
        }
    }

    public static class SystemBuilder {
        private String name;
        private String content;

        public SystemBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public SystemBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public ChatMessage build() {
            return new ChatMessage("system", name, notNull(content), null, null);
        }
    }

    public static class UserBuilder {
        private String name;
        private String content;
        private final List<ContentPart> contentParts = new ArrayList<>();

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public UserBuilder addTextContentPart(String text) {
            this.contentParts.add(ContentPart.asText(text));
            return this;
        }

        public UserBuilder addImageUrlContentPart(String imageUrl) {
            this.contentParts.add(ContentPart.asImageUrl(imageUrl));
            return this;
        }

        // TODO: add unit testing
        public ChatMessage build() {
            if (content == null && contentParts.isEmpty()) {
                throw new RuntimeException("Set content or content parts");
            }
            if (content != null && !contentParts.isEmpty()) {
                throw new RuntimeException("Set one of content or content parts");
            }

            return new ChatMessage("user", name, Objects.requireNonNullElse(content, contentParts), null, null);

        }
    }

    public static class AssistantBuilder {
        private String name;
        private String content;
        public final List<ToolCall> toolCalls = new ArrayList<>();

        public AssistantBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public AssistantBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public AssistantBuilder addToolCall(ToolCall toolCall) {
            this.toolCalls.add(toolCall);
            return this;
        }

        // TODO: add unit testing
        public ChatMessage build() {
            if (toolCalls.isEmpty() && content == null) {
                throw new RuntimeException("'content' is required unless 'tool_calls' is specified");
            }

            return new ChatMessage("assistant", name, content, toolCalls, null);
        }
    }

    public static class ToolBuilder {
        private String content;
        private String toolCallId;

        public ToolBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public ToolBuilder setToolCallId(String toolCallId) {
            this.toolCallId = toolCallId;
            return this;
        }

        public ChatMessage build() {
            return new ChatMessage("tool", null, notNull(content), null, notNull(toolCallId));
        }
    }
}
