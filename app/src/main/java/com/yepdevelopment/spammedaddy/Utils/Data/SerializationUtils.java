package com.yepdevelopment.spammedaddy.Utils.Data;

import android.util.Log;

import com.yepdevelopment.spammedaddy.Annotations.DoNotSerialize;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class SerializationUtils {

    /**
     * <p>Attempts to map a {@link JSONObject} to the given type.</p>
     *
     * <p>The given type <strong>must</strong> have a default constructor declared.</p>
     *
     * <p>If the {@link JSONObject} is missing a key found in the given type, then that field will be initialized to the default value (if primitive) or {@code null} otherwise.</p>
     *
     * <p>Conversely, if the {@link JSONObject} contains a key that is not found in the given type, then that key will be omitted from the instance.</p>
     *
     * @param jsonObject the {@link JSONObject} to map values from
     * @param type       the {@link Class} of the type to map the JSON string to
     * @param <T>        the type that the returned instance represents
     * @return an instance of the given type with fields populated from the {@link JSONObject} or {@code null} if an error occurred in parsing
     */
    @Nullable
    public static <T> T fromJson(JSONObject jsonObject, Class<T> type) {
        if (jsonObject == null) {
            Log.e(SerializationUtils.class.getName(), "fromJson(): the JSONObject was null.");
            return null;
        }

        if (type == null) {
            Log.e(SerializationUtils.class.getName(), "fromJson(): the type was null (I don't know what to cast this JSONObject into).");
            return null;
        }

        try {
            Constructor<T> defaultCtor = type.getDeclaredConstructor();
            try {
                T inst = defaultCtor.newInstance();
                Field[] fields = type.getDeclaredFields();

                for (Field field : fields) {
                    field.setAccessible(true);

                    Annotation doNotSerialize = field.getAnnotation(DoNotSerialize.class);

                    if (doNotSerialize != null) continue;

                    String key = field.getName();

                    if (!jsonObject.has(key)) continue;

                    try {
                        field.set(inst, jsonObject.get(key));
                    } catch (JSONException ex) {
                        Log.w(SerializationUtils.class.getName(), String.format("Encountered JSON error while parsing '%s', skipping...", key));
                    }
                }

                return inst;
            } catch (InvocationTargetException | IllegalAccessException |
                     InstantiationException ex) {
                Log.e(SerializationUtils.class.getName(), String.format("%s: %s", ex.getClass().getName(), ex.getMessage()));
                return null;
            }
        } catch (NoSuchMethodException ex) {
            Log.e(SerializationUtils.class.getName(), String.format("fromJson(): %s has no declared default constructor. Unable to create instance.", type.getName()));
            return null;
        }
    }

    /**
     * <p>Attempts to parse a JSON string into a {@link JSONObject} before attempting to map it to the given type.</p>
     *
     * @param jsonString the JSON string to map values from
     * @param type       the {@link Class} of the type to map the JSON string to
     * @param <T>        the type that the returned instance represents
     * @return an instance of the given type with fields populated from the JSON string or {@code null} if an error occurred during parsing
     * @see SerializationUtils#fromJson(JSONObject, Class)
     */
    @Nullable
    public static <T> T fromJson(String jsonString, Class<T> type) {
        try {
            return fromJson(new JSONObject(jsonString), type);
        } catch (JSONException ignored) {

        }

        return null;
    }
}
