package com.yepdevelopment.spammedaddy.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yepdevelopment.spammedaddy.Types.Contact;

import java.util.LinkedList;
import java.util.List;

public class AddRecipientPageViewModel extends ViewModel {
    private final MutableLiveData<String> _RecipientFieldText = new MutableLiveData<>("");
    private final MutableLiveData<List<Contact>> _ContactsList = new MutableLiveData<>();

    public LiveData<String> getRecipientFieldText() {
        return _RecipientFieldText;
    }

    public void setRecipientFieldText(String newValue) {
        _RecipientFieldText.setValue(newValue);
    }

    public LiveData<List<Contact>> getContactsList() {
        return _ContactsList;
    }

    public void setContactsList(List<Contact> newValue) {
        _ContactsList.setValue(newValue);
    }
}
