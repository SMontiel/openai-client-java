package com.salvadormontiel.openai.utils;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

public class Jsonable {


    public static <T> String toJson(T object) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<T> jsonAdapter = (JsonAdapter<T>) moshi.adapter(object.getClass());

        return jsonAdapter.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        Moshi moshi = new Moshi.Builder().build();

        JsonAdapter<T> jsonAdapter = moshi.adapter(clazz);

        try {
            return (T) jsonAdapter.fromJson(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
