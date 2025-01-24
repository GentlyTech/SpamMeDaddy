package com.yepdevelopment.spammedaddy.Pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yepdevelopment.spammedaddy.Adapters.ContributorAdapter;
import com.yepdevelopment.spammedaddy.Utils.Android.ResourceManipulator;
import com.yepdevelopment.spammedaddy.databinding.FragmentAboutBinding;

public class AboutPage extends Fragment {
    private FragmentAboutBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.contributorsList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.contributorsList.setAdapter(new ContributorAdapter(requireContext(), ResourceManipulator.getContributors(requireContext())));
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}