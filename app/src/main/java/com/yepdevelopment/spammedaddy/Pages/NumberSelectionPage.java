package com.yepdevelopment.spammedaddy.Pages;

import android.os.Bundle;

import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;
import com.yepdevelopment.spammedaddy.R;
import com.yepdevelopment.spammedaddy.Types.Page;
import com.yepdevelopment.spammedaddy.Utils.Data.SerializationUtils;
import com.yepdevelopment.spammedaddy.databinding.PageNumberSelectionBinding;

import lombok.Getter;

public class NumberSelectionPage extends Page<PageNumberSelectionBinding> {
    ContactWithData contact;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        if (args == null) {
            navigateToErrorPage();
            return;
        }

        String rawContactWithData = args.getString(ARGS.CONTACT.getValue());
        ContactWithData contact = SerializationUtils.fromJson(rawContactWithData, ContactWithData.class);
        if (contact == null) {
            navigateToErrorPage();
            return;
        }

        this.contact = contact;
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
