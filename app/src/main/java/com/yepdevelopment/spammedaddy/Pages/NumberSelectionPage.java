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
import com.yepdevelopment.spammedaddy.R;
import com.yepdevelopment.spammedaddy.Types.Page;
import com.yepdevelopment.spammedaddy.databinding.PageNumberSelectionBinding;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;

public class NumberSelectionPage extends Page<PageNumberSelectionBinding> {
    ContactWithData contactWithData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        List<String> phoneNumbers = new LinkedList<>();

        for (PhoneNumber phoneNumber : contactWithData.getPhoneNumbers()) {
            phoneNumbers.add(phoneNumber.getPhoneNumber());
        }

        binding.phoneNumberSelectionList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.phoneNumberSelectionList.setAdapter(new CheckBoxAdapter(requireContext(), phoneNumbers));

        binding.finishPhoneNumberSelectionButton.setOnClickListener((ignored) -> {

        });
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
