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

    public static ErrorPage newInstance() {
        return new ErrorPage();
    }

    public static ErrorPage newInstance(String headingMessage, String bodyMessage) {
        Bundle args = new Bundle();

        args.putString("headingMessage", headingMessage);
        args.putString("bodyMessage", bodyMessage);

        ErrorPage inst = new ErrorPage();
        inst.setArguments(args);
        return inst;
    }

    public static ErrorPage newInstance(int headingMessageResId, int bodyMessageResId) {
        ErrorPage inst = new ErrorPage();
        Resources resources = inst.getResources();
        Bundle args = new Bundle();

        try {
            String headingMessage = resources.getString(headingMessageResId);
            args.putString("headingMessage", headingMessage);
        }
        catch (Resources.NotFoundException ex) {
            Log.w(ErrorPage.class.getName(), "newInstance(): Invalid headingMessageResId (resource does not exist)");
        }

        try {
            String bodyMessage = resources.getString(bodyMessageResId);
            args.putString("bodyMessage", bodyMessage);
        }
        catch (Resources.NotFoundException ex) {
            Log.w(ErrorPage.class.getName(), "newInstance(): Invalid bodyMessageResId (resource does not exist)");
        }

        inst.setArguments(args);
        return inst;
    }

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
