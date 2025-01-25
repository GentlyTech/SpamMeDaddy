package com.yepdevelopment.spammedaddy.Database.Relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Entities.PhoneNumber;

import java.util.List;

public class ContactWithPhoneNumbers {
    @Embedded
    public Contact contact;
    @Relation(parentColumn = "contactId", entityColumn = "contactId")
    public List<PhoneNumber> phoneNumbers;
}
