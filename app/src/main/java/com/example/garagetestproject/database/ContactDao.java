package com.example.garagetestproject.database;

import com.example.garagetestproject.model.Contact;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM Contact")
    List<Contact> getAll();

    @Query("SELECT * FROM Contact")
    LiveData<List<Contact>> getAllLiveData();

    @Query("DELETE FROM Contact")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact... contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);
}
