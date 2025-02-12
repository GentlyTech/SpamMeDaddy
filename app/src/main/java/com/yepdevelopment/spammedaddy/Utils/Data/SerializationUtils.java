package com.yepdevelopment.spammedaddy.Utils.Data;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class SerializationUtils {

    @Nullable
    public static <T> T fromJson(JSONObject jsonObject, Class<T> type) {
        return null;
    }

    @Nullable
    public static <T> T fromJson(String jsonString, Class<T> type) {
        try {
            return fromJson(new JSONObject(jsonString), type);
        }
        catch (JSONException ignored) {

        }

        return null;
    }
}
