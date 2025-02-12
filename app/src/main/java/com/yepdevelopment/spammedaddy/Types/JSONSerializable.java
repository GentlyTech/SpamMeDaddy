package com.yepdevelopment.spammedaddy.Types;

import android.util.Log;

import com.yepdevelopment.spammedaddy.Annotations.DoNotSerialize;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Allows a class to be serialized into a JSONObject.
 */
public abstract class JSONSerializable {
    /**
     * <p>Reflectively maps all fields (public, private, and protected) in this instance to a {@link JSONObject} and returns it to allow for easy data transfer between mediums.</p>
     *
     * <p>Fields annotated with {@link DoNotSerialize} will be excluded from the {@link JSONObject}.</p>
     *
     * @return a {@link JSONObject} representing this instance
     */
    @Nullable
    public JSONObject toJson() {
        try {
            JSONObject result = new JSONObject();

            Field[] fields = this.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                Annotation doNotSerialize = field.getAnnotation(DoNotSerialize.class);
                if (doNotSerialize != null) continue;

                String key = field.getName();
                Object value = field.get(this);

                Class<?> type = field.getType();
                if (value != null && type.getSuperclass() == JSONSerializable.class) {
                    try {
                        value = type.getSuperclass().getDeclaredMethod("toJson").invoke(value);
                    }
                    catch (NoSuchMethodException | InvocationTargetException ex) {
                        Log.w(getClass().getName(), String.format("Field %s (of type %s) does not implement toJson() or invocation failed. Setting to null... (%s)", key, type.getName(), ex));
                        value = null;
                    }
                }

                result.put(key, value);
            }

            return result;
        } catch (JSONException | IllegalAccessException ex) {
            Log.e(getClass().getName(), String.format("%s: %s", ex.getClass().getName(), ex.getMessage()));
            return null;
        }
    }
}
