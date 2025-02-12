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
    public abstract void insertAll(PhoneNumber... phoneNumbers);

    @Insert
    public abstract void insertAll(List<PhoneNumber> phoneNumbers);

    @Delete
    public abstract void delete(PhoneNumber contact);

    @Query("SELECT * FROM phonenumber")
    public abstract List<PhoneNumber> getAll();

    @Query("SELECT * FROM phonenumber WHERE contactId = :contactId")
    public abstract List<PhoneNumber> getPhoneNumbersByContactId(String contactId);


    public List<PhoneNumber> getPhoneNumbersForContact(Contact contact) {
        return getPhoneNumbersByContactId(contact.getContactId());
    }
}
