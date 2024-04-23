package com.salvadormontiel.openai.response;

import java.util.List;

public class ModelsOutput {
    public String object;
    public List<Model> data;

    public ModelsOutput(String object, List<Model> data) {
        this.object = object;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ModelsOutput{" +
                "object='" + object + '\'' +
                ", data=" + data +
                '}';
    }
}
