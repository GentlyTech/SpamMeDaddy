package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
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
