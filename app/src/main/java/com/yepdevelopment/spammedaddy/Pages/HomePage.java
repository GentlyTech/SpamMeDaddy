package com.yepdevelopment.spammedaddy.Pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.yepdevelopment.spammedaddy.MenuProviders.GeneralMenuProvider;
import com.yepdevelopment.spammedaddy.databinding.PageHomeBinding;

public class HomePage extends Fragment {
    PageHomeBinding binding;
    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PageHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        requireActivity().addMenuProvider(new GeneralMenuProvider(), getViewLifecycleOwner());

        hideRecipientsList(); // TODO remove and implement checking recipients list length and displaying accordingly

        binding.addRecipientFloatingActionButton.setOnClickListener((v) -> {
            navController.navigate(HomePageDirections.actionHomeFragmentToAddRecipientPage());
        });
    }

    public void showRecipientsList() {
        binding.homeRecipientsList.setVisibility(View.VISIBLE);
        binding.homeRecipientsListStatusLayout.setVisibility(View.GONE);
    }

    public void hideRecipientsList() {
        binding.homeRecipientsList.setVisibility(View.GONE);
        binding.homeRecipientsListStatusLayout.setVisibility(View.VISIBLE);
    }
}
