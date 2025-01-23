package com.yepdevelopment.spammedaddy.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddRecipientPageViewModel extends ViewModel {
    private final MutableLiveData<String> _RecipientFieldText = new MutableLiveData<>("");

    public LiveData<String> getRecipientFieldText() {
        return _RecipientFieldText;
    }

    public void setRecipientFieldText(String newValue) {
        _RecipientFieldText.setValue(newValue);
    }
}
