package com.yepdevelopment.spammedaddy.Database.Relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Entities.Message;

import java.util.List;

public class ContactWithMessages {
    @Embedded
    public Contact contact;
    @Relation(parentColumn = "contactId", entityColumn = "contactId")
    public List<Message> messages;
}
