package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PhoneNumber {
    @PrimaryKey
    public String phoneNumberId;

    public String contactId;

    public String phoneNumber;
}
