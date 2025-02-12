package com.yepdevelopment.spammedaddy.Pages;

import static com.yepdevelopment.spammedaddy.Helpers.General.ContactUtils.getContacts;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yepdevelopment.spammedaddy.Adapters.ContactListAdapter;
import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;
import com.yepdevelopment.spammedaddy.Gson.Serializer;
import com.yepdevelopment.spammedaddy.R;
import com.yepdevelopment.spammedaddy.Types.Page;
import com.yepdevelopment.spammedaddy.Helpers.Android.TextChangedListeners.OnTextChangedListener;
import com.yepdevelopment.spammedaddy.Helpers.General.ContactUtils;
import com.yepdevelopment.spammedaddy.ViewModels.AddRecipientPageViewModel;
import com.yepdevelopment.spammedaddy.databinding.PageAddRecipientBinding;

public class AddRecipientPage extends Page<PageAddRecipientBinding> {
    AddRecipientPageViewModel addRecipientPageViewModel;

    ActivityResultLauncher<String> requestContactsPermissionActivityLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), (result) -> {
        if (result) {
            addRecipientPageViewModel.setContactsList(getContacts(getContext()));
        } else {
            hideContactsList(ContactsListHideReason.CONTACTS_DISALLOWED);
        }
    });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addRecipientPageViewModel = new ViewModelProvider(requireActivity()).get(AddRecipientPageViewModel.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        addRecipientPageViewModel.setRecipientFieldText("");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.addRecipientsContactsList.setLayoutManager(new LinearLayoutManager(getContext()));

        addRecipientPageViewModel.getRecipientFieldText().observe(getViewLifecycleOwner(), (updatedValue) -> {
            if (ContactUtils.hasContactsPermission(getContext())) {
                if (updatedValue.isEmpty()) {
                    addRecipientPageViewModel.setContactsList(getContacts(getContext()));
                } else {
                    addRecipientPageViewModel.setContactsList(getContacts(getContext(), updatedValue));
                }
            } else {
                requestContactsPermission();
            }
        });

        addRecipientPageViewModel.getContactsList().observe(getViewLifecycleOwner(), (updatedList) -> {
            String recipientFieldTextValue = addRecipientPageViewModel.getRecipientFieldText().getValue();
            if (updatedList.isEmpty() && recipientFieldTextValue != null && !recipientFieldTextValue.isEmpty()) {
                hideContactsList(ContactsListHideReason.NO_MATCH);
                return;
            } else if (updatedList.isEmpty()) {
                hideContactsList(ContactsListHideReason.NO_CONTACTS);
                return;
            }
            showContactsList();
            binding.addRecipientsContactsList.setAdapter(new ContactListAdapter(getContext(), updatedList, this::onContactCardClicked));
        });

        binding.recipientEditText.addTextChangedListener(new OnTextChangedListener((updatedValue) -> addRecipientPageViewModel.setRecipientFieldText(updatedValue.toString())));
    }

    private void onContactCardClicked(ContactWithData contact) {
        Bundle args = new Bundle();
        String serializedContact = Serializer.getSerializer().toJson(contact);

        args.putString(NumberSelectionPage.ARGS.CONTACT.getValue(), serializedContact);

        navController.navigate(R.id.numberSelectionPage, args);
    }

    private void requestContactsPermission() {
        requestContactsPermissionActivityLauncher.launch(Manifest.permission.READ_CONTACTS);
    }

    public void showContactsList() {
        binding.addRecipientsContactsList.setVisibility(View.VISIBLE);
        binding.addRecipientsContactListStatusGroup.setVisibility(View.GONE);
    }

    public void hideContactsList(ContactsListHideReason reason) {
        binding.addRecipientsContactsList.setVisibility(View.GONE);

        switch (reason) {
            case NO_CONTACTS:
                binding.addRecipientsContactsListStatusHeading.setText(R.string.addRecipientPage_ContactsListStatusHeading_NoContacts);
                binding.addRecipientsContactsListStatusBody.setText(R.string.addRecipientPage_ContactsListStatusBody_NoContacts);
                binding.addRecipientsContactListStatusGroup.setVisibility(View.VISIBLE);
                break;
            case NO_MATCH:
                binding.addRecipientsContactsListStatusHeading.setText(R.string.addRecipientPage_ContactsListStatusHeading_NoMatch);
                binding.addRecipientsContactsListStatusBody.setText(R.string.addRecipientPage_ContactsListStatusBody_NoMatch);
                binding.addRecipientsContactListStatusGroup.setVisibility(View.VISIBLE);
                break;
            case CONTACTS_DISALLOWED:
                binding.addRecipientsContactsListStatusHeading.setText(R.string.addRecipientPage_ContactsListStatusHeading_ContactsDisallowed);
                binding.addRecipientsContactsListStatusBody.setText(R.string.addRecipientPage_ContactsListStatusBody_ContactsDisallowed);
                binding.addRecipientsContactListStatusGroup.setVisibility(View.VISIBLE);
                break;
        }
    }

    public enum ContactsListHideReason {
        NO_CONTACTS,
        NO_MATCH,
        CONTACTS_DISALLOWED
    }
}