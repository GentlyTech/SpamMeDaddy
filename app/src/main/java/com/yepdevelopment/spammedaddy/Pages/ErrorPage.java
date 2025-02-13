package com.yepdevelopment.spammedaddy.Pages;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yepdevelopment.spammedaddy.Types.Page;
import com.yepdevelopment.spammedaddy.databinding.PageErrorBinding;

import lombok.Getter;

public class ErrorPage extends Page<PageErrorBinding> {
    private String headingMessage = null;
    private String bodyMessage = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        if (args != null) {
            headingMessage = args.getString(ARGS.HEADING.getValue());
            bodyMessage = args.getString(ARGS.BODY.getValue());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (headingMessage != null) {
            binding.errorTextHeading.setText(headingMessage);
        }

        if (bodyMessage != null) {
            binding.errorTextBody.setText(bodyMessage);
        }

        binding.errorBackButton.setOnClickListener(ignored -> navController.popBackStack());
    }

    @Getter
    public enum ARGS {
        HEADING("headingMessage"),
        BODY("bodyMessage");

        private final String value;

        ARGS(String value) {
            this.value = value;
        }
    }
}
