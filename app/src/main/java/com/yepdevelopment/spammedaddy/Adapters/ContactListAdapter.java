package com.yepdevelopment.spammedaddy.Adapters;

import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yepdevelopment.spammedaddy.Types.Contact;
import com.yepdevelopment.spammedaddy.ViewHolders.GenericViewHolder;
import com.yepdevelopment.spammedaddy.databinding.ComponentContactCardBinding;

import java.util.LinkedList;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<GenericViewHolder<ComponentContactCardBinding>> {
    Context context;
    List<Contact> contacts;

    public ContactListAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts != null ? contacts : new LinkedList<>();
    }

    @NonNull
    @Override
    public GenericViewHolder<ComponentContactCardBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ComponentContactCardBinding binding = ComponentContactCardBinding.inflate(inflater, parent, false);
        return new GenericViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder<ComponentContactCardBinding> holder, int position) {
        Contact contact = contacts.get(position);
        if (contact == null) return;

        String formattedPhoneNumber = PhoneNumberUtils.formatNumber(contact.getPhoneNumber(), "CA");
        if (formattedPhoneNumber == null) formattedPhoneNumber = contact.getPhoneNumber();

        holder.getBinding().contactCardDisplayName.setText(contact.getName());
        holder.getBinding().contactCardPhoneNumber.setText(formattedPhoneNumber);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
