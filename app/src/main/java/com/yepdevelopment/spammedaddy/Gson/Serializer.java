package com.yepdevelopment.spammedaddy.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Serializer {
    private static Gson cachedInstance;

    public static Gson getSerializer() {
        if (cachedInstance == null) {
            cachedInstance = new GsonBuilder().addSerializationExclusionStrategy(new DoNotSerializeStrategy()).create();
        }
        return cachedInstance;
    }
}
