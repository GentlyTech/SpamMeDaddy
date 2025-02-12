package com.yepdevelopment.spammedaddy.Helpers.Data;

import java.util.HashSet;
import java.util.Set;

public class TypeUtils {
    public static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    private static Set<Class<?>> getWrapperTypes() {
        Set<Class<?>> types = new HashSet<>();
        types.add(Boolean.class);
        types.add(Character.class);
        types.add(Byte.class);
        types.add(Short.class);
        types.add(Integer.class);
        types.add(Long.class);
        types.add(Float.class);
        types.add(Double.class);
        return types;
    }

    public static boolean isWrapperClass(Object obj) {
        return WRAPPER_TYPES.contains(obj.getClass());
    }
}
