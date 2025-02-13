package com.yepdevelopment.spammedaddy.Pages;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yepdevelopment.spammedaddy.Adapters.ContributorAdapter;
import com.yepdevelopment.spammedaddy.Helpers.Android.ResourceManipulator;
import com.yepdevelopment.spammedaddy.Types.Page;
import com.yepdevelopment.spammedaddy.databinding.PageAboutBinding;

public class AboutPage extends Page<PageAboutBinding> {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.contributorsList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.contributorsList.setAdapter(new ContributorAdapter(requireContext(), ResourceManipulator.getContributors(requireContext())));
    }
}