package com.example.garagetestproject;

import android.app.Application;

import com.example.garagetestproject.database.AppDatabase;
import com.example.garagetestproject.database.ContactDao;
import com.example.garagetestproject.model.Contact;

import androidx.room.Room;

public class App extends Application {
    private AppDatabase mDatabase;
    private ContactDao mContactDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        mDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "contact.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        mContactDao = mDatabase.mContactDao();

        if (App.getInstance().getContactDao().getAll().isEmpty()) {
            createList();
        } else {
            App.getInstance().getContactDao().getAll();
        }
    }

    public void createList() {
        for (int i = 1; i <= 15; i++) {
            Contact contact = new Contact();
            contact.setFirstName("UserName #" + i);
            contact.setLastName("UserSurname #" + i);
            contact.setEmail("user" + i + "@gmail.com");
            mContactDao.insert(contact);
        }
    }

    public AppDatabase getDatabase() {
        return mDatabase;
    }

    public void setDatabase(AppDatabase database) {
        mDatabase = database;
    }

    public ContactDao getContactDao() {
        return mContactDao;
    }

    public void setContactDao(ContactDao contactDao) {
        mContactDao = contactDao;
    }

}
