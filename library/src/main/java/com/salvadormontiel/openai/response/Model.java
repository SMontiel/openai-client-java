package com.salvadormontiel.openai.response;

import com.squareup.moshi.Json;

public class Model {
    public String id;
    public int created;
    public String object;
    @Json(name = "owned_by") public String ownedBy;

    public Model(String id, int created, String object, String ownedBy) {
        this.id = id;
        this.created = created;
        this.object = object;
        this.ownedBy = ownedBy;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id='" + id + '\'' +
                ", created=" + created +
                ", object='" + object + '\'' +
                ", ownedBy='" + ownedBy + '\'' +
                '}';
    }
}
