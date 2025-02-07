package com.yepdevelopment.spammedaddy.Database.Relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Entities.Message;
import com.yepdevelopment.spammedaddy.Database.Entities.PhoneNumber;

import java.util.List;

import lombok.Getter;

@Getter
public class ContactWithData {
    @Embedded
    private Contact contact;

    @Relation(parentColumn = "contactId", entityColumn = "contactId")
    private List<PhoneNumber> phoneNumbers;

    @Relation(parentColumn = "contactId", entityColumn = "contactId")
    private List<Message> messages;
}
