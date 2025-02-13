package com.yepdevelopment.spammedaddy.Pages;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;
import com.yepdevelopment.spammedaddy.Types.Page;
import com.yepdevelopment.spammedaddy.databinding.PageRecipientMessagesBinding;

import java.util.List;
import java.util.concurrent.Executors;

import lombok.Getter;

public class RecipientMessagesPage extends Page<PageRecipientMessagesBinding> {

    ContactWithData contactWithData;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        if (args == null) {
            navigateToErrorPage();
            return;
        }

        String contactId = args.getString(ARGS.CONTACT_ID.getValue());

        if (contactId == null) {
            navigateToErrorPage();
            return;
        }

        Futures.addCallback(database.contactDao().getContactWithDataFromContactId(contactId), new FutureCallback<>() {
            @Override
            public void onSuccess(ContactWithData contactsWithData) {
                RecipientMessagesPage.this.contactWithData = contactsWithData;

                binding.recipientCard.simpleContactCardDisplayName.setText(contactsWithData.getContact().getContactName());
            }

            @Override
            public void onFailure(@NonNull Throwable err) {
                Log.e(RecipientMessagesPage.this.getClass().getName(), String.format("Couldn't load RecipientMessagesPage for contact with id %s (%s)", contactId, err));
                navigateToErrorPage();
            }
        }, Executors.newSingleThreadExecutor());
    }

    private void navigateToErrorPage() {

    }

    @Getter
    public enum ARGS {
        CONTACT_ID("contactId");

        private final String value;

        ARGS(String value) {
            this.value = value;
        }

    }
}
