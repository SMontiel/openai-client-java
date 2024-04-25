package com.salvadormontiel.openai.input;

import com.salvadormontiel.openai.FunctionProperties;
import com.squareup.moshi.Json;

import java.util.List;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class Tool {
    public String type;
    public Function<? extends FunctionProperties> function;

    public Tool(String type, Function<? extends FunctionProperties> function) {
        this.type = type;
        this.function = function;
    }

    public static class Function<T extends FunctionProperties> {
        public String name;
        public String description;
        public Class<T> parameters;

        public Function(String name, String description, Class<T> parameters) {
            this.name = notNull(name);
            this.description = description;
            this.parameters = parameters;
        }
    }
}
