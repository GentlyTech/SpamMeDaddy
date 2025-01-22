package com.yepdevelopment.spammedaddy.Pages;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yepdevelopment.spammedaddy.R;
import com.yepdevelopment.spammedaddy.databinding.PageAddRecipientBinding;

public class AddRecipientPage extends Fragment {
    PageAddRecipientBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PageAddRecipientBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}