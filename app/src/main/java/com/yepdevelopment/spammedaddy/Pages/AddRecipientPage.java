package com.yepdevelopment.spammedaddy.Pages;

import static com.yepdevelopment.spammedaddy.Utils.ContactUtils.getContacts;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yepdevelopment.spammedaddy.Adapters.ContactListAdapter;
import com.yepdevelopment.spammedaddy.R;
import com.yepdevelopment.spammedaddy.Types.Contact;
import com.yepdevelopment.spammedaddy.databinding.PageAddRecipientBinding;

import java.util.List;

public class AddRecipientPage extends Fragment {
    PageAddRecipientBinding binding;
    List<Contact> contacts = null;

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
        binding.addRecipientsContactsList.setLayoutManager(new LinearLayoutManager(getContext()));

        registerForActivityResult(new ActivityResultContracts.RequestPermission(), (result) -> {
            if (result) {
                contacts = getContacts(getContext());
                if (contacts.isEmpty()) {
                    hideContactsList(ContactsListHideReason.NO_CONTACTS);
                    return;
                }
                showContactsList();
                binding.addRecipientsContactsList.setAdapter(new ContactListAdapter(getContext(), contacts, null));
            } else {
                hideContactsList(ContactsListHideReason.CONTACTS_DISALLOWED);
            }
        }).launch(Manifest.permission.READ_CONTACTS);
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
            case NO_MATCH:
                binding.addRecipientsContactsListStatusHeading.setText(R.string.addRecipientPage_ContactsListStatusHeading_NoMatch);
                binding.addRecipientsContactsListStatusBody.setText(R.string.addRecipientPage_ContactsListStatusBody_NoMatch);
                binding.addRecipientsContactsListStatusLayout.setVisibility(View.VISIBLE);
                break;
            case CONTACTS_DISALLOWED:
                binding.addRecipientsContactsListStatusHeading.setText(R.string.addRecipientPage_ContactsListStatusHeading_ContactsDisallowed);
                binding.addRecipientsContactsListStatusBody.setText(R.string.addRecipientPage_ContactsListStatusBody_ContactsDisallowed);
                binding.addRecipientsContactsListStatusLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    public enum ContactsListHideReason {
        NO_CONTACTS,
        CONTACTS_DISALLOWED
    }
}