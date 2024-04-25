package com.salvadormontiel.openai;

import com.salvadormontiel.openai.input.Tool;
import com.salvadormontiel.openai.utils.Triplet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionExecutor {
    private final Map<String, Triplet<String, Class<? extends FunctionProperties>, Function<? extends FunctionProperties, String>>> functions = new HashMap<>();

    public FunctionExecutor() {}

    public <T extends FunctionProperties> FunctionExecutor addFunction(String name, String description, Class<T> clazz, Function<T, String> function) {
        functions.put(name, Triplet.of(description, clazz, function));

        return this;
    }

    public <T extends FunctionProperties> Function<T, String> getFunction(String name) {
        return (Function<T, String>) functions.get(name).third;
    }

    public <T extends FunctionProperties> Class<T> getClass(String name) {
        return (Class<T>) functions.get(name).second;
    }

    public List<Tool> getTools() {
        return functions.entrySet()
                .stream()
                .map(entry -> {
                    String name = entry.getKey();
                    String description = entry.getValue().first;
                    Class<? extends FunctionProperties> clazz = entry.getValue().second;
                    Tool.Function<? extends FunctionProperties> function = new Tool.Function<>(name, description, clazz);

                    return new Tool("function", function);
                }).collect(Collectors.toList());
    }
}
