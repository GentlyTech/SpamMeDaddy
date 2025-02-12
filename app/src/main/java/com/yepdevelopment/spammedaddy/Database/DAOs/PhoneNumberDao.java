package com.yepdevelopment.spammedaddy.Database.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;
import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Entities.PhoneNumber;

import java.util.List;

@Dao
public abstract class PhoneNumberDao {
    @Insert
    public abstract ListenableFuture<Void> insertPhoneNumbers(PhoneNumber... phoneNumbers);

    @Insert
    public abstract ListenableFuture<Void> insertPhoneNumbers(List<PhoneNumber> phoneNumbers);

    @Delete
    public abstract ListenableFuture<Void> deletePhoneNumber(PhoneNumber contact);

    @Query("SELECT * FROM phonenumber")
    public abstract ListenableFuture<List<PhoneNumber>> getAllPhoneNumbers();

    @Query("SELECT * FROM phonenumber WHERE contactId = :contactId")
    public abstract ListenableFuture<List<PhoneNumber>> getPhoneNumbersByContactId(String contactId);


    public ListenableFuture<List<PhoneNumber>> getPhoneNumbersForContact(Contact contact) {
        return getPhoneNumbersByContactId(contact.getContactId());
    }
}
