package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.yepdevelopment.spammedaddy.Types.JSONSerializable;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

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
}
