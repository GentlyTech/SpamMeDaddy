package com.yepdevelopment.spammedaddy.Database.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;
import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Entities.Message;
import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;

import java.util.List;

@Dao
public abstract class MessageDao {
    @Insert
    public abstract ListenableFuture<Void> insertMessages(Message... messages);

    @Insert
    public abstract ListenableFuture<Void> insertMessages(List<Message> messages);

    @Delete
    public abstract ListenableFuture<Void> deleteMessage(Message message);

    @Query("SELECT * FROM message")
    public abstract ListenableFuture<List<Message>> getAllMessages();

    @Query("SELECT * FROM message WHERE contactId = :contactId")
    public abstract ListenableFuture<List<Message>> getMessagesByContactId(String contactId);

    public ListenableFuture<List<Message>> getMessagesForContact(Contact contact) {
        return getMessagesByContactId(contact.getContactId());
    }

    public ListenableFuture<List<Message>> getMessagesForContact(ContactWithData contact) {
        return getMessagesByContactId(contact.getContact().getContactId());
    }
}
