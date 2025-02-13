package com.yepdevelopment.spammedaddy.Adapters;

import android.content.Context;
import android.widget.CheckBox;

import androidx.annotation.NonNull;

import com.yepdevelopment.spammedaddy.ViewHolders.GenericViewHolder;
import com.yepdevelopment.spammedaddy.databinding.ComponentCheckboxEntryBinding;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

public class CheckBoxAdapter<T> extends GenericAdapter<ComponentCheckboxEntryBinding> {
    private final List<T> items;

    private final BiConsumer<T, Boolean> onClickHandler;


    public CheckBoxAdapter(Context context, List<T> items, BiConsumer<T, Boolean> onClickHandler) {
        super(context, ComponentCheckboxEntryBinding.class);
        this.items = items != null ? items : new LinkedList<>();
        this.onClickHandler = onClickHandler;
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder<ComponentCheckboxEntryBinding> holder, int position) {
        ComponentCheckboxEntryBinding binding = holder.getBinding();

        String label = items.get(position).toString();

        binding.checkBox.setText(label);

        if (this.onClickHandler != null) {
            binding.checkBox.setOnClickListener((view) -> {
                CheckBox checkBox = (CheckBox) view;
                onClickHandler.accept(items.get(position), checkBox.isChecked());
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
