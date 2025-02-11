package com.yepdevelopment.spammedaddy.Pages;

import android.os.Bundle;

import com.yepdevelopment.spammedaddy.R;
import com.yepdevelopment.spammedaddy.Types.Page;
import com.yepdevelopment.spammedaddy.databinding.PageNumberSelectionBinding;

import lombok.Getter;

public class NumberSelectionPage extends Page<PageNumberSelectionBinding> {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

//        if (args == null) { // TODO implement checking whether a valid ContactWithData was actually passed rather than always showing error page
            Bundle errorPageArgs = new Bundle();
            errorPageArgs.putString(ErrorPage.ARGS.BODY.getValue(), getString(R.string.numberSelectionPage_ErrorPageBody_NoContact));
            navController.navigate(R.id.action_numberSelectionPage_to_errorPage, errorPageArgs);
//        }
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
