package com.yepdevelopment.spammedaddy.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yepdevelopment.spammedaddy.R;
import com.yepdevelopment.spammedaddy.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    FragmentHomeBinding fragmentHomeBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return FragmentHomeBinding.inflate(inflater, container, false).getRoot();
    }
}
