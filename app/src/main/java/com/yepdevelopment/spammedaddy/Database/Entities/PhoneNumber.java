package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PhoneNumber {
    @PrimaryKey
    private String phoneNumberId;

    private String contactId;

    private String phoneNumber;
}
