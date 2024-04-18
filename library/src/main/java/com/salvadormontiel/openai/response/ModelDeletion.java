package com.salvadormontiel.openai.response;

public class ModelDeletion {
    public String id;
    public String object;
    public boolean deleted;

    public ModelDeletion(String id, String object, boolean deleted) {
        this.id = id;
        this.object = object;
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "ModelDeletion{" +
                "id='" + id + '\'' +
                ", object='" + object + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
