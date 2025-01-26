package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.room.PrimaryKey;

public class Contact {
    @PrimaryKey
    public String contactId;

    public String contactName;
}
