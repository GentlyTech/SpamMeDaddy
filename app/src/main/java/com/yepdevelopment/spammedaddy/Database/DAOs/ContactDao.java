package com.yepdevelopment.spammedaddy.Database.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.google.common.util.concurrent.ListenableFuture;
import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert
    ListenableFuture<Void> insertContacts(Contact... contacts);

    @Insert
    ListenableFuture<Void> insertContacts(List<Contact> contacts);

    @Delete
    ListenableFuture<Void> deleteContact(Contact contact);

    @Query("SELECT * FROM contact")
    ListenableFuture<List<Contact>> getAllContacts();

    @Transaction
    @Query("SELECT * FROM contact")
    ListenableFuture<List<ContactWithData>> getContactsWithData();

}
