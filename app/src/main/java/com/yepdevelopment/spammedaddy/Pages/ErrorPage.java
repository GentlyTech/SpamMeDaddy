package com.yepdevelopment.spammedaddy.Pages;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yepdevelopment.spammedaddy.Types.Page;
import com.yepdevelopment.spammedaddy.databinding.PageErrorBinding;

public class ErrorPage extends Page<PageErrorBinding> {
    private String headingMessage = null;
    private String bodyMessage = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        if (args != null) {
            headingMessage = args.getString("headingMessage");
            bodyMessage = args.getString("bodyMessage");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (headingMessage != null) {
            binding.errorTextHeader.setText(headingMessage);
        }

        if (bodyMessage != null) {
            binding.errorTextBody.setText(bodyMessage);
        }

        binding.errorBackButton.setOnClickListener(ignored -> navController.popBackStack());
    }
}
