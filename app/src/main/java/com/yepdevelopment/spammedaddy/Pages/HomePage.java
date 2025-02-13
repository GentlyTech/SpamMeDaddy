package com.yepdevelopment.spammedaddy.Pages;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.yepdevelopment.spammedaddy.Adapters.ContactAdapter;
import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
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
            } else if (menuItemId == R.id.menuItemWipeDatabase) {
                database.wipeDatabase();
                Snackbar.make(view, "Database wiped", Snackbar.LENGTH_SHORT).show();
                return true;
            } else if (menuItemId == R.id.menuItemDebug) {
                Snackbar.make(view, "No function assigned to debug button", Snackbar.LENGTH_SHORT).show();
            }
            return false;
        })), getViewLifecycleOwner());

        binding.homeRecipientsList.setLayoutManager(new LinearLayoutManager(requireContext()));

        database.contactDao().getAllContactsObservable().observe(getViewLifecycleOwner(), (contacts) -> {
            if (contacts.isEmpty()) {
                hideRecipientsList();
                return;
            }

            binding.homeRecipientsList.setAdapter(new ContactAdapter(requireContext(), contacts, HomePage.this::onContactClicked));

            showRecipientsList();
        });

        binding.addRecipientFloatingActionButton.setOnClickListener((v) -> {
            navController.navigate(HomePageDirections.actionHomeFragmentToAddRecipientPage());
        });
    }

    private void onContactClicked(Contact contact) {
        Snackbar.make(binding.getRoot(), String.format("Contact Clicked: %s", contact.getContactName()), Snackbar.LENGTH_SHORT).show();
    }

    public void showRecipientsList() {
        binding.homeRecipientsList.setVisibility(View.VISIBLE);
        binding.homeRecipientsListStatusGroup.setVisibility(View.GONE);
    }

    public void hideRecipientsList() {
        binding.homeRecipientsList.setVisibility(View.GONE);
        binding.homeRecipientsListStatusGroup.setVisibility(View.VISIBLE);
    }
}
