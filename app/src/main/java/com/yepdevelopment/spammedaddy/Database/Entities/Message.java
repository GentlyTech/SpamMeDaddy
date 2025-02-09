package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.yepdevelopment.spammedaddy.Types.JSONSerializable;

import org.json.JSONObject;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Message implements JSONSerializable {
    @PrimaryKey
    private String messageId;

    private String contactId;

    private LocalDateTime messageScheduledSendTime;

    private String messageContent;

    @Override
    public JSONObject toJson() {
        return null;
    }
}
