package com.yepdevelopment.spammedaddy.MenuProviders;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.yepdevelopment.spammedaddy.R;

import java.util.function.Consumer;
import java.util.function.Function;

public class GeneralMenuProvider implements MenuProvider {
    Function<MenuItem, Boolean> onMenuItemSelectedCallback;

    public GeneralMenuProvider(Function<MenuItem, Boolean> onMenuItemSelectedCallback) {
        this.onMenuItemSelectedCallback = onMenuItemSelectedCallback;
    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.general, menu);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        if (onMenuItemSelectedCallback == null) {
            return false;
        }
        return onMenuItemSelectedCallback.apply(menuItem);
    }
}
