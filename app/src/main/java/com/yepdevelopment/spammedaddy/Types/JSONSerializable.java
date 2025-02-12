package com.yepdevelopment.spammedaddy.Types;

import android.util.Log;

import com.yepdevelopment.spammedaddy.Annotations.DoNotSerialize;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Allows a class to be serialized into a JSONObject.
 */
public abstract class JSONSerializable {
    /**
     * <p>Reflectively maps all fields (public, private, and protected) in this instance to a JSONObject and returns it to allow for easy data transfer between mediums.</p>
     *
     * <p>Fields annotated with {@link DoNotSerialize} will be excluded from the JSONObject.</p>
     *
     * @return a JSONObject representing this instance
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

                result.put(key, value);
            }

            return result;
        } catch (JSONException | IllegalAccessException ex) {
            Log.e(getClass().getName(), String.format("%s: %s", ex.getClass().getName(), ex.getMessage()));
            return null;
        }
    }
}
