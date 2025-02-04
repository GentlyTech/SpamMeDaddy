package com.yepdevelopment.spammedaddy.Pages;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yepdevelopment.spammedaddy.MenuProviders.GeneralMenuProvider;
import com.yepdevelopment.spammedaddy.R;
import com.yepdevelopment.spammedaddy.Types.Page;
import com.yepdevelopment.spammedaddy.databinding.PageHomeBinding;

public class HomePage extends Page<PageHomeBinding> {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        requireActivity().addMenuProvider(new GeneralMenuProvider((menuItem -> {
            int menuItemId = menuItem.getItemId();
            if (menuItemId == R.id.menuItemAbout) {
                navController.navigate(HomePageDirections.actionHomePageToAboutPage());
                return true;
            } else if (menuItemId == R.id.menuItemDebug) {
                navController.navigate(HomePageDirections.actionHomePageToRecipientMessagesPage());
                return true;
            }
            return false;
        })), getViewLifecycleOwner());

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
