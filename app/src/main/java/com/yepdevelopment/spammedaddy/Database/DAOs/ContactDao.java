package com.yepdevelopment.spammedaddy.Database.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yepdevelopment.spammedaddy.Database.Entities.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert
    void insertAll(Contact... contacts);

    @Delete
    void delete(Contact contact);

    @Query("SELECT * FROM contact")
    List<Contact> getAll();

}
