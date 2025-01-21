package com.yepdevelopment.spammedaddy.Pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.yepdevelopment.spammedaddy.databinding.PageHomeBinding;

public class HomePage extends Fragment {
    PageHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return PageHomeBinding.inflate(inflater, container, false).getRoot();
    }
}
