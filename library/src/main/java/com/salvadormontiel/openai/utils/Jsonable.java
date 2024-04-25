package com.salvadormontiel.openai.utils;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.Map;

public class Jsonable {
    private static Moshi moshi;

    private static Moshi getMoshi() {
        if (moshi == null) {
            moshi = new Moshi.Builder()
                    .add(new ParamsAdapter())
                    .build();
        }
        return moshi;
    }

    public static <T> String toJson(T object) {
        JsonAdapter<T> jsonAdapter = (JsonAdapter<T>) getMoshi().adapter(object.getClass());

        return jsonAdapter.toJson(object);
    }

    public static <T, K, V> String mapToJson(T json, Class<K> key, Class<V> value) {
        return getMoshi().adapter(Types.newParameterizedType(Map.class, key, value))
                .toJson(json);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        JsonAdapter<T> jsonAdapter = getMoshi().adapter(clazz);

        try {
            return (T) jsonAdapter.fromJson(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
