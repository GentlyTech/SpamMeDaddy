package com.yepdevelopment.spammedaddy.Adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yepdevelopment.spammedaddy.ViewHolders.GenericViewHolder;
import com.yepdevelopment.spammedaddy.databinding.ComponentCheckboxEntryBinding;

public class CheckBoxAdapter extends GenericAdapter<ComponentCheckboxEntryBinding> {
    public CheckBoxAdapter(Context context) {
        super(context, ComponentCheckboxEntryBinding.class);
    }

    @NonNull
    @Override
    public GenericViewHolder<ComponentCheckboxEntryBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder<ComponentCheckboxEntryBinding> holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
