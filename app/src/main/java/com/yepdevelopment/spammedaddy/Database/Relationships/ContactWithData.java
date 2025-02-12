package com.yepdevelopment.spammedaddy.Database.Relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.yepdevelopment.spammedaddy.Annotations.DoNotSerialize;
import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Entities.Message;
import com.yepdevelopment.spammedaddy.Database.Entities.PhoneNumber;
import com.yepdevelopment.spammedaddy.Types.JSONSerializable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

@Getter
public class ContactWithData extends JSONSerializable {
    // TODO figure out how to serialize these fields
    @Embedded
    @DoNotSerialize
    private Contact contact;

    @DoNotSerialize
    @Relation(parentColumn = "contactId", entityColumn = "contactId")
    private List<PhoneNumber> phoneNumbers;

    @DoNotSerialize
    @Relation(parentColumn = "contactId", entityColumn = "contactId")
    private List<Message> messages;

    public static ContactWithData newInstance(String contactName) {
        ContactWithData contactWithData = new ContactWithData();

        contactWithData.contact = new Contact();
        contactWithData.contact.setContactName(contactName);
        contactWithData.contact.setContactId(UUID.randomUUID().toString());

        contactWithData.phoneNumbers = new LinkedList<>();
        contactWithData.messages = new LinkedList<>();

        return contactWithData;
    }

    public static ContactWithData generateSample() {
        ContactWithData inst = new ContactWithData();
        inst.contact = Contact.generateSample();
        inst.phoneNumbers = Arrays.asList(PhoneNumber.generateSample(), PhoneNumber.generateSample(), PhoneNumber.generateSample());
        inst.messages = Arrays.asList(Message.generateSample(), Message.generateSample(), Message.generateSample());
        return inst;
    }

    public void addPhoneNumber(String phoneNumber) {
        PhoneNumber phoneNumberObj = new PhoneNumber();
        phoneNumberObj.setPhoneNumber(phoneNumber);
        phoneNumberObj.setContactId(contact.getContactId());
        phoneNumberObj.setPhoneNumberId(UUID.randomUUID().toString());

        phoneNumbers.add(phoneNumberObj);
    }
}
