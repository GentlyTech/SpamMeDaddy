package com.yepdevelopment.spammedaddy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.yepdevelopment.spammedaddy.ViewHolders.GenericViewHolder;
import com.yepdevelopment.spammedaddy.databinding.ComponentContactCardBinding;

import java.lang.reflect.InvocationTargetException;

public abstract class GenericAdapter<T extends ViewBinding> extends RecyclerView.Adapter<GenericViewHolder<T>> {
    private final Class<T> viewBindingClass;

    protected final Context context;

    public GenericAdapter(Context context, Class<T> viewBindingClass) {
        this.viewBindingClass = viewBindingClass;
        this.context = context;
    }

    @NonNull
    @Override
    public GenericViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        try {
            T binding = (T) viewBindingClass.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class).invoke(inflater, parent, false);
            if (binding == null) throw new IllegalArgumentException();
            return new GenericViewHolder<>(binding);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
