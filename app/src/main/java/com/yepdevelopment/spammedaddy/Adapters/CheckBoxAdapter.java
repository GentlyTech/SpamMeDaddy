package com.yepdevelopment.spammedaddy.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;

import com.yepdevelopment.spammedaddy.ViewHolders.GenericViewHolder;
import com.yepdevelopment.spammedaddy.databinding.ComponentCheckboxEntryBinding;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class CheckBoxAdapter extends GenericAdapter<ComponentCheckboxEntryBinding> {
    List<String> labels;

    public CheckBoxAdapter(Context context, List<String> labels) {
        super(context, ComponentCheckboxEntryBinding.class);
        this.labels = labels != null ? labels : new LinkedList<>();
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder<ComponentCheckboxEntryBinding> holder, int position) {
        ComponentCheckboxEntryBinding binding = holder.getBinding();

        String label = labels.get(position);

        binding.checkBox.setText(label);
    }

    @Override
    public int getItemCount() {
        return labels.size();
    }
}
