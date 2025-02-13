package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(foreignKeys = {
        @ForeignKey(entity = Contact.class, parentColumns = "contactId", childColumns = "contactId", onDelete = ForeignKey.CASCADE)
})
public class Message {
    @PrimaryKey
    @NonNull
    private String messageId = "";

    private String contactId = "";

    private LocalDateTime messageScheduledSendTime = null;

    private String messageContent = "";

    public static Message generateSample() {
        Message inst = new Message();
        inst.messageId = UUID.randomUUID().toString();
        inst.contactId = UUID.randomUUID().toString();
        inst.messageScheduledSendTime = LocalDateTime.now();
        inst.messageContent = UUID.randomUUID().toString();
        return inst;
    }
}
