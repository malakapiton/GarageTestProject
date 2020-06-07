package com.example.garagetestproject.activity;

import com.example.garagetestproject.App;
import com.example.garagetestproject.model.Contact;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ContactListViewModel extends ViewModel {
    private LiveData<List<Contact>> contactLiveData = App.getInstance().getContactDao().getAllLiveData();

    public LiveData<List<Contact>> getContactLiveData() {
        return contactLiveData;
    }
}
