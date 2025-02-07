package com.yepdevelopment.spammedaddy.Database.Relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Entities.Message;
import com.yepdevelopment.spammedaddy.Database.Entities.PhoneNumber;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

@Getter
public class ContactWithData {
    @Embedded
    private Contact contact;

    @Relation(parentColumn = "contactId", entityColumn = "contactId")
    private List<PhoneNumber> phoneNumbers;

    @Relation(parentColumn = "contactId", entityColumn = "contactId")
    private List<Message> messages;

    public void addPhoneNumber(String phoneNumber) {
        PhoneNumber phoneNumberObj = new PhoneNumber();
        phoneNumberObj.setPhoneNumber(phoneNumber);
        phoneNumberObj.setContactId(contact.getContactId());
        phoneNumberObj.setPhoneNumberId(UUID.randomUUID().toString());

        phoneNumbers.add(phoneNumberObj);
    }

    public static ContactWithData newInstance(String contactName) {
        ContactWithData contactWithData = new ContactWithData();

        contactWithData.contact = new Contact();
        contactWithData.contact.setContactName(contactName);
        contactWithData.contact.setContactId(UUID.randomUUID().toString());

        contactWithData.phoneNumbers = new LinkedList<>();
        contactWithData.messages = new LinkedList<>();

        return contactWithData;
    }
}
