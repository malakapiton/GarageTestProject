package com.example.garagetestproject.database;

import com.example.garagetestproject.model.Contact;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao mContactDao();
}
