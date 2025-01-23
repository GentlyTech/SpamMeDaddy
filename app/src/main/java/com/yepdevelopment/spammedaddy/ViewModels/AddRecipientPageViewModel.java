package com.yepdevelopment.spammedaddy.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddRecipientPageViewModel extends ViewModel {
    private final MutableLiveData<String> _RecipientFieldText = new MutableLiveData<>("");

    public String getRecipientFieldText() {
        return _RecipientFieldText.getValue();
    }

    public void setRecipientFieldText(String newValue) {
        _RecipientFieldText.setValue(newValue);
    }
}
