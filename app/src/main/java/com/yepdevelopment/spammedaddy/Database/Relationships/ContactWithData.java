package com.yepdevelopment.spammedaddy.Database.Relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Entities.Message;
import com.yepdevelopment.spammedaddy.Database.Entities.PhoneNumber;
import com.yepdevelopment.spammedaddy.Types.JSONSerializable;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

@Getter
public class ContactWithData extends JSONSerializable {
    @Embedded
    private Contact contact = null;

    @Relation(parentColumn = "contactId", entityColumn = "contactId")
    private List<PhoneNumber> phoneNumbers = null;

    @Relation(parentColumn = "contactId", entityColumn = "contactId")
    private List<Message> messages = null;

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

    @Override
    public @Nullable JSONObject toJson() {
        JSONObject baseObj = super.toJson();
        if (baseObj == null) return null;

        JSONArray jsonArray = new JSONArray();

        if (phoneNumbers != null) {
            for (PhoneNumber phoneNumber : phoneNumbers) {
                jsonArray.put(phoneNumber.toJson());
            }

            try {
                baseObj.put("phoneNumbers", jsonArray);
            } catch (JSONException ignored) {

            }
        }

        if (messages != null) {
            jsonArray = new JSONArray();
            for (Message message : messages) {
                jsonArray.put(message.toJson());
            }

            try {
                baseObj.put("messages", jsonArray);
            } catch (JSONException ignored) {

            }
        }

        return baseObj;
    }
}
