package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Message {
    @PrimaryKey
    private String messageId;

    private String contactId;

    private LocalDateTime messageScheduledSendTime;

    private String messageContent;
}
