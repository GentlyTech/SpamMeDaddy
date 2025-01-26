package com.yepdevelopment.spammedaddy.Database.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Entities.Message;

import java.util.List;

@Dao
public abstract class MessageDao {
    @Insert
    abstract void insertAll(Message... messages);

    @Delete
    abstract void delete(Message message);

    @Query("SELECT * FROM message")
    abstract List<Message> getAll();


    @Query("SELECT * FROM message WHERE contactId = :contactId")
    abstract List<Message> getMessagesByContactId(String contactId);


    List<Message> getMessagesForContact(Contact contact) {
        return getMessagesByContactId(contact.contactId);
    }
}
