package com.yepdevelopment.spammedaddy.Pages;

import com.yepdevelopment.spammedaddy.Types.Page;
import com.yepdevelopment.spammedaddy.databinding.PageRecipientMessagesBinding;

import lombok.Getter;

public class RecipientMessagesPage extends Page<PageRecipientMessagesBinding> {
    @Getter
    public enum ARGS {
        CONTACT_ID("contactId");

        private final String value;

        ARGS(String value) {
            this.value = value;
        }

    }
}
