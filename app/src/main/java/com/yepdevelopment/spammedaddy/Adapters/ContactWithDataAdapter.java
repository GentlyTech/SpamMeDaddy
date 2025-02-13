package com.yepdevelopment.spammedaddy.Adapters;

import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.yepdevelopment.spammedaddy.Database.Entities.PhoneNumber;
import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;
import com.yepdevelopment.spammedaddy.R;
import com.yepdevelopment.spammedaddy.ViewHolders.GenericViewHolder;
import com.yepdevelopment.spammedaddy.databinding.ComponentFullContactCardBinding;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class ContactWithDataAdapter extends GenericAdapter<ComponentFullContactCardBinding> {
    private final List<ContactWithData> contactsWithData;
    private final Consumer<ContactWithData> onClickHandler;

    public ContactWithDataAdapter(Context context, List<ContactWithData> contactsWithData, Consumer<ContactWithData> onClickHandler) {
        super(context, ComponentFullContactCardBinding.class);
        this.contactsWithData = contactsWithData != null ? contactsWithData : new LinkedList<>();
        this.onClickHandler = onClickHandler;
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder<ComponentFullContactCardBinding> holder, int position) {
        ContactWithData contact = contactsWithData.get(position);
        if (contact == null) return;

        List<PhoneNumber> phoneNumbers = contact.getPhoneNumbers();
        if (phoneNumbers == null || phoneNumbers.isEmpty()) return;

        PhoneNumber firstPhoneNumber = contact.getPhoneNumbers().get(0);
        if (firstPhoneNumber == null) return;

        String formattedPhoneNumber = PhoneNumberUtils.formatNumber(firstPhoneNumber.getPhoneNumber(), "CA");
        if (formattedPhoneNumber == null) formattedPhoneNumber = firstPhoneNumber.getPhoneNumber();

        holder.getBinding().fullContactCardDisplayName.setText(contact.getContact().getContactName());
        holder.getBinding().fullContactCardPhoneNumber.setText(formattedPhoneNumber);

        if (phoneNumbers.size() > 1) {
            holder.getBinding().fullContactCardOverflowText.setText(context.getString(R.string.contactCardPhoneNumberOverflowTextView_Text, phoneNumbers.size() - 1));
            holder.getBinding().fullContactCardOverflowText.setVisibility(ViewGroup.VISIBLE);
        }

        if (onClickHandler != null) {
            holder.getBinding().fullContactCardLayoutRoot.setOnClickListener((ignored) -> onClickHandler.accept(contact));
        }
    }

    @Override
    public int getItemCount() {
        return contactsWithData.size();
    }
}
