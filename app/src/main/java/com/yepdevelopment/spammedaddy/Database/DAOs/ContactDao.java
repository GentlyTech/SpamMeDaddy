package com.yepdevelopment.spammedaddy.Database.DAOs;

import androidx.lifecycle.LiveData;
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
public abstract class ContactDao {
    @Insert
    public abstract ListenableFuture<Void> insertContacts(Contact... contacts);

    @Insert
    public abstract ListenableFuture<Void> insertContacts(List<Contact> contacts);

    @Delete
    public abstract ListenableFuture<Void> deleteContact(Contact contact);

    @Query("DELETE FROM contact")
    public abstract ListenableFuture<Void> deleteAllContacts();

    @Query("SELECT * FROM contact")
    public abstract ListenableFuture<List<Contact>> getAllContacts();

    @Query("SELECT * FROM contact")
    public abstract LiveData<List<Contact>> getAllContactsObservable();

    @Transaction
    @Query("SELECT * FROM contact")
    public abstract ListenableFuture<List<ContactWithData>> getContactsWithData();

    @Transaction
    @Query("SELECT * FROM contact WHERE contactId = :contactId")
    public abstract ListenableFuture<ContactWithData> getContactWithDataFromContactId(String contactId);

    ListenableFuture<ContactWithData> getContactWithDataFromContact(Contact contact) {
        return getContactWithDataFromContactId(contact.getContactId());
    }

}
