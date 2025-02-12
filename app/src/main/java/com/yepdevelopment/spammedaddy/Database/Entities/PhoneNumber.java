package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.yepdevelopment.spammedaddy.Types.JSONSerializable;

import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PhoneNumber extends JSONSerializable {
    @PrimaryKey
    private String phoneNumberId;

    private String contactId;

    private String phoneNumber;
}
