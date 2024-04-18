package com.salvadormontiel.openai.response;

import com.squareup.moshi.Json;

import java.util.List;

public class ChatCompletion {
    public String id;
    public List<Choice> choices;
    public int created;
    public String model;
    @Json(name = "system_fingerprint") public String systemFingerprint;
    public String object;
    public Usage usage;

    public ChatCompletion(String id, List<Choice> choices, int created, String model, String systemFingerprint, String object, Usage usage) {
        this.id = id;
        this.choices = choices;
        this.created = created;
        this.model = model;
        this.systemFingerprint = systemFingerprint;
        this.object = object;
        this.usage = usage;
    }

    @Override
    public String toString() {
        return "ChatCompletion{" +
                "id='" + id + '\'' +
                ", choices=" + choices +
                ", created=" + created +
                ", model='" + model + '\'' +
                ", systemFingerprint='" + systemFingerprint + '\'' +
                ", object='" + object + '\'' +
                ", usage=" + usage +
                '}';
    }
}
