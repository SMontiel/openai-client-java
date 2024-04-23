package com.salvadormontiel.openai.input;

import org.jetbrains.annotations.NotNull;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class ToolCall {
    public String id;
    public String type;
    public Function function;

    ToolCall(@NotNull String id, @NotNull String type, @NotNull Function function) {
        this.id = notNull(id);
        this.type = notNull(type);
        this.function = notNull(function);
    }

    @Override
    public String toString() {
        return "ToolCall{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", function=" + function +
                '}';
    }

    public static class Function {
        public String name;
        public String arguments;

        Function(@NotNull String name, @NotNull String arguments) {
            this.name = notNull(name);
            this.arguments = notNull(arguments);
        }

        @Override
        public String toString() {
            return "Function{" +
                    "name='" + name + '\'' +
                    ", arguments='" + arguments + '\'' +
                    '}';
        }
    }

    public static class Builder {
        private String id;
        private String type;
        private Function function;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setFunction(String name, String arguments) {
            this.function = new Function(name, arguments);
            return this;
        }

        public ToolCall build() {
            return new ToolCall(id, type, function);
        }
    }
}
