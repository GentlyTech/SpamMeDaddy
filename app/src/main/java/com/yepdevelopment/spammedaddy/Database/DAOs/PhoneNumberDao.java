package com.yepdevelopment.spammedaddy.Database.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Entities.PhoneNumber;

import java.util.List;

@Dao
public abstract class PhoneNumberDao {
    @Insert
    abstract void insertAll(PhoneNumber... phoneNumbers);

    @Delete
    abstract void delete(PhoneNumber contact);

    @Query("SELECT * FROM phonenumber")
    abstract List<PhoneNumber> getAll();

    @Query("SELECT * FROM phonenumber WHERE contactId = :contactId")
    abstract List<PhoneNumber> getPhoneNumbersByContactId(String contactId);


    List<PhoneNumber> getPhoneNumberssForContact(Contact contact) {
        return getPhoneNumbersByContactId(contact.getContactId());
    }
}
