package com.yepdevelopment.spammedaddy.Adapters;

import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yepdevelopment.spammedaddy.Database.Entities.PhoneNumber;
import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;
import com.yepdevelopment.spammedaddy.R;
import com.yepdevelopment.spammedaddy.ViewHolders.GenericViewHolder;
import com.yepdevelopment.spammedaddy.databinding.ComponentContactCardBinding;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class ContactListAdapter extends RecyclerView.Adapter<GenericViewHolder<ComponentContactCardBinding>> {
    Context context;
    List<ContactWithData> contacts;
    Consumer<ContactWithData> onClickHandler;

    public ContactListAdapter(Context context, List<ContactWithData> contacts, Consumer<ContactWithData> onClickHandler) {
        this.context = context;
        this.contacts = contacts != null ? contacts : new LinkedList<>();
        this.onClickHandler = onClickHandler;
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
        ContactWithData contact = contacts.get(position);
        if (contact == null) return;

        List<PhoneNumber> phoneNumbers = contact.getPhoneNumbers();
        if (phoneNumbers == null || phoneNumbers.isEmpty()) return;

        PhoneNumber firstPhoneNumber = contact.getPhoneNumbers().get(0);
        if (firstPhoneNumber == null) return;

        String formattedPhoneNumber = PhoneNumberUtils.formatNumber(firstPhoneNumber.getPhoneNumber(), "CA");
        if (formattedPhoneNumber == null) formattedPhoneNumber = firstPhoneNumber.getPhoneNumber();

        holder.getBinding().contactCardDisplayName.setText(contact.getContact().getContactName()); // FIXME if you encounter weird issues with Contact try investigating here
        holder.getBinding().contactCardPhoneNumber.setText(formattedPhoneNumber);

        if (phoneNumbers.size() > 1) {
            holder.getBinding().contactCardOverflowText.setText(context.getString(R.string.contactCardPhoneNumberOverflowTextView_Text, phoneNumbers.size() - 1));
            holder.getBinding().contactCardOverflowText.setVisibility(ViewGroup.VISIBLE);
        }

        if (onClickHandler != null) {
            holder.getBinding().contactCardLayoutRoot.setOnClickListener((ignored) -> onClickHandler.accept(contact));
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
