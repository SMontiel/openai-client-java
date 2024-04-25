package com.salvadormontiel.openai.utils;

import com.salvadormontiel.openai.FunctionProperties;
import com.salvadormontiel.openai.PropertyDetails;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ParamsAdapter {

    @ToJson
    public Map<String, Object> toJson(Class<? extends FunctionProperties> params) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "object");

        Map<String, Object> properties = new HashMap<>();
        List<String> requiredFields = new ArrayList<>();

        Field[] fields = params.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            String name = field.getName();
            String description = null;
            PropertyDetails ann = field.getAnnotation(PropertyDetails.class);
            if (ann != null) {
                description = ann.description();
                if (ann.required()) {
                    requiredFields.add(name);
                }
            }
            Map<String, Object> prop = new HashMap<>();
            prop.put("type", "string");
            if (field.getType().isEnum()) {
                List<String> enums = Arrays.stream(field.getType().getEnumConstants())
                        .map(Object::toString)
                        .collect(Collectors.toList());
                prop.put("enum", enums);
            }
            if (description != null) {
                prop.put("description", description);
            }

            properties.put(name, prop);
        });
        map.put("properties", properties);
        map.put("required", requiredFields);

        return map;
    }

    @FromJson
    public Class<? extends FunctionProperties> fromJson(Map<String, Object> map) {
        // No op
        return null;
    }
}
