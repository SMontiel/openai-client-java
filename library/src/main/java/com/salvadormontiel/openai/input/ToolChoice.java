package com.salvadormontiel.openai.input;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class ToolChoice {
    public String type;
    public Function function;

    public ToolChoice(String type, Function function) {
        this.type = notNull(type);
        this.function = notNull(function);
    }

    public static class Function {
        public String name;

        public Function(String name) {
            this.name = notNull(name);
        }

        @Override
        public String toString() {
            return "Function{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
