package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.yepdevelopment.spammedaddy.Types.JSONSerializable;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Message extends JSONSerializable {
    @PrimaryKey
    private String messageId = null;

    private String contactId = null;

    private LocalDateTime messageScheduledSendTime = null;

    private String messageContent = null;

    public static Message generateSample() {
        Message inst = new Message();
        inst.messageId = UUID.randomUUID().toString();
        inst.contactId = UUID.randomUUID().toString();
        inst.messageScheduledSendTime = LocalDateTime.now();
        inst.messageContent = UUID.randomUUID().toString();
        return inst;
    }
}
