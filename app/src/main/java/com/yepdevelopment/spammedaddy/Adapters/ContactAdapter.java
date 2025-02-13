package com.yepdevelopment.spammedaddy.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.ViewHolders.GenericViewHolder;
import com.yepdevelopment.spammedaddy.databinding.ComponentSimpleContactCardBinding;

import java.util.LinkedList;
import java.util.List;

public class ContactAdapter extends GenericAdapter<ComponentSimpleContactCardBinding> {
    private final List<Contact> contacts;

    private final Consumer<Contact> onClickHandler;

    public ContactAdapter(Context context, List<Contact> contacts, Consumer<Contact> onClickHandler) {
        super(context, ComponentSimpleContactCardBinding.class);
        this.contacts = contacts != null ? contacts : new LinkedList<>();
        this.onClickHandler = onClickHandler;
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder<ComponentSimpleContactCardBinding> holder, int position) {
        Contact contact = contacts.get(position);

        ComponentSimpleContactCardBinding binding = holder.getBinding();

        binding.simpleContactCardDisplayName.setText(contact.getContactName());

        if (onClickHandler != null) {
            binding.simpleContactCardLayoutRoot.setOnClickListener((ignored) -> onClickHandler.accept(contact));
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
