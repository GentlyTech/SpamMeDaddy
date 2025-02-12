package com.yepdevelopment.spammedaddy.Database.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Entities.Message;
import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;

import java.util.List;

@Dao
public abstract class MessageDao {
    @Insert
    public abstract void insertAll(Message... messages);

    @Insert
    public abstract void insertAll(List<Message> messages);

    @Delete
    public abstract void delete(Message message);

    @Query("SELECT * FROM message")
    public abstract List<Message> getAll();

    @Query("SELECT * FROM message WHERE contactId = :contactId")
    public abstract List<Message> getMessagesByContactId(String contactId);

    public List<Message> getMessagesForContact(Contact contact) {
        return getMessagesByContactId(contact.getContactId());
    }

    public List<Message> getMessagesForContact(ContactWithData contact) {
        return getMessagesByContactId(contact.getContact().getContactId());
    }
}
