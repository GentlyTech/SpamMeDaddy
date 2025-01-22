package com.yepdevelopment.spammedaddy.Pages;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yepdevelopment.spammedaddy.R;
import com.yepdevelopment.spammedaddy.databinding.PageAddRecipientBinding;

public class AddRecipientPage extends Fragment {
    public enum ContactsListHideReason {
        NO_CONTACTS,
        CONTACTS_DISALLOWED
    }

    PageAddRecipientBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PageAddRecipientBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        hideContactsList(ContactsListHideReason.NO_CONTACTS);
    }

    public void showContactsList() {
        binding.addRecipientsContactsList.setVisibility(View.VISIBLE);
        binding.addRecipientsContactsListStatusLayout.setVisibility(View.GONE);
    }

    public void hideContactsList(ContactsListHideReason reason) {
        binding.addRecipientsContactsList.setVisibility(View.GONE);

        switch (reason) {
            case NO_CONTACTS:
                binding.addRecipientsContactsListStatusHeading.setText(R.string.addRecipientPage_ContactsListStatusHeading_NoContacts);
                binding.addRecipientsContactsListStatusBody.setText(R.string.addRecipientPage_ContactsListStatusBody_NoContacts);
                binding.addRecipientsContactsListStatusLayout.setVisibility(View.VISIBLE);
                break;
            case CONTACTS_DISALLOWED:
                binding.addRecipientsContactsListStatusHeading.setText(R.string.addRecipientPage_ContactsListStatusHeading_ContactsDisallowed);
                binding.addRecipientsContactsListStatusBody.setText(R.string.addRecipientPage_ContactsListStatusBody_ContactsDisallowed);
                binding.addRecipientsContactsListStatusLayout.setVisibility(View.VISIBLE);
                break;
        }
    }
}