package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity
public class Message {
    @PrimaryKey
    public String messageId;

    public String contactId;

    public LocalDateTime messageScheduledSendTime;

    public String messageContent;
}
