package com.yepdevelopment.spammedaddy.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;

import java.util.List;

public class AddRecipientPageViewModel extends ViewModel {
    private final MutableLiveData<String> _RecipientFieldText = new MutableLiveData<>("");
    private final MutableLiveData<List<ContactWithData>> _ContactsList = new MutableLiveData<>();

    public LiveData<String> getRecipientFieldText() {
        return _RecipientFieldText;
    }

    public void setRecipientFieldText(String newValue) {
        _RecipientFieldText.setValue(newValue);
    }

    public LiveData<List<ContactWithData>> getContactsList() {
        return _ContactsList;
    }

    public void setContactsList(List<ContactWithData> newValue) {
        _ContactsList.setValue(newValue);
    }
}
