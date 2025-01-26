package com.yepdevelopment.spammedaddy.Types;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public abstract class Page<T extends ViewBinding> extends Fragment {
    protected T binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            Type superClass = getClass().getGenericSuperclass();
            if (superClass == null) {
                Log.e(getClass().getName(), "No superclass");
                return null;
            }
            Type[] genericTypes = ((ParameterizedType) superClass).getActualTypeArguments();
            if (genericTypes.length == 0) {
                Log.e(getClass().getName(), "Binding type parameter not supplied");
                return null;
            }
            Class<T> bindingType = (Class<T>) genericTypes[0];
            Method inflateMethod = bindingType.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);

            binding = (T) inflateMethod.invoke(null, inflater, container, false);
            if (binding == null) {
                Log.e(getClass().getName(), "Binding inflate call returned null");
                return null;
            }
            return binding.getRoot();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            Log.e(getClass().getName(), String.format("%s: %s", ex.getClass().getName(), ex.getMessage()));
        }
        return null;
    }
}
