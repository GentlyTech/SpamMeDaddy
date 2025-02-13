package com.yepdevelopment.spammedaddy.Pages;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yepdevelopment.spammedaddy.Adapters.CheckBoxAdapter;
import com.yepdevelopment.spammedaddy.Database.Entities.PhoneNumber;
import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;
import com.yepdevelopment.spammedaddy.Gson.Serializer;
import com.yepdevelopment.spammedaddy.Helpers.Android.DatabaseUtils;
import com.yepdevelopment.spammedaddy.R;
import com.yepdevelopment.spammedaddy.Types.Page;
import com.yepdevelopment.spammedaddy.databinding.PageNumberSelectionBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.Getter;

public class NumberSelectionPage extends Page<PageNumberSelectionBinding> {
    private ContactWithData contactWithData;

    private Map<String, Boolean> phoneNumbers;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        if (args == null) {
            navigateToErrorPage();
            return;
        }

        String rawContactWithData = args.getString(ARGS.CONTACT.getValue());
        ContactWithData contact = Serializer.getSerializer().fromJson(rawContactWithData, ContactWithData.class);
        if (contact == null) {
            navigateToErrorPage();
            return;
        }

        this.contactWithData = contact;

        phoneNumbers = new HashMap<>();

        for (PhoneNumber phoneNumber : contactWithData.getPhoneNumbers()) {
            phoneNumbers.put(phoneNumber.getPhoneNumberId(), false);
        }

        binding.phoneNumberSelectionList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.phoneNumberSelectionList.setAdapter(new CheckBoxAdapter<>(requireContext(), contactWithData.getPhoneNumbers(), this::onPhoneNumberToggled));

        binding.finishPhoneNumberSelectionButton.setOnClickListener((ignored) -> {
            List<PhoneNumber> originalPhoneNumbers = contactWithData.getPhoneNumbers();
            List<PhoneNumber> selectedPhoneNumbers = new LinkedList<>();

            for (Map.Entry<String, Boolean> entry : phoneNumbers.entrySet()) {
                if (entry.getValue()) {
                    Optional<PhoneNumber> result = originalPhoneNumbers.stream().filter(x -> x.getPhoneNumberId().equals(entry.getKey())).findFirst();
                    if (result.isEmpty()) continue;
                    selectedPhoneNumbers.add(result.get());
                }
            }

            contactWithData.setPhoneNumbers(selectedPhoneNumbers);

            database.insertContactWithData(contactWithData);

            Bundle navArgs = new Bundle();
            navArgs.putString(RecipientMessagesPage.ARGS.CONTACT_ID.getValue(), contactWithData.getContact().getContactId());

            navController.navigate(NumberSelectionPageDirections.actionNumberSelectionPageToRecipientMessagesPage().getActionId(), navArgs);
        });
    }

    private void onPhoneNumberToggled(PhoneNumber phoneNumber, boolean value) {
        String id = phoneNumber.getPhoneNumberId();
        if (!phoneNumbers.containsKey(id)) return;
        phoneNumbers.put(id, value);
    }

    private void navigateToErrorPage() {
        Bundle errorPageArgs = new Bundle();
        errorPageArgs.putString(ErrorPage.ARGS.HEADING.getValue(), getString(R.string.numberSelectionPage_ErrorPageHeading_NoContact));
        errorPageArgs.putString(ErrorPage.ARGS.BODY.getValue(), getString(R.string.numberSelectionPage_ErrorPageBody_NoContact));
        navController.navigate(R.id.action_numberSelectionPage_to_errorPage, errorPageArgs);
    }

    @Getter
    public enum ARGS {
        CONTACT("contact");

        private final String value;

        ARGS(String value) {
            this.value = value;
        }

    }
}
