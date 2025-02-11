package com.yepdevelopment.spammedaddy.Database.Relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Entities.Message;
import com.yepdevelopment.spammedaddy.Database.Entities.PhoneNumber;
import com.yepdevelopment.spammedaddy.Types.JSONSerializable;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

@Getter
public class ContactWithData implements JSONSerializable {
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

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();

        // TODO implement method

        return jsonObject;
    }

    public static ContactWithData fromJson(JSONObject jsonObject) {
        return null; // TODO implement method
    }

    public static ContactWithData fromJson(String jsonString) {
        return null; // TODO implement method
    }
}
