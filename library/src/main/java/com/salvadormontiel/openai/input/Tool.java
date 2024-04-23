package com.salvadormontiel.openai.input;

import java.util.Map;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class Tool {
    public String type;
    public Function function;

    Tool(String type, Function function) {
        this.type = type;
        this.function = function;
    }

    public static class Function {
        public String name;
        public String description;
        public Map<String, Map<String, Object>> parameters;

        Function(String name, String description, Map<String, Map<String, Object>> parameters) {
            this.name = notNull(name);
            this.description = description;
            this.parameters = parameters;
        }
    }
}
