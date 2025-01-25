package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.room.PrimaryKey;

public class Contact {
    @PrimaryKey
    String contactId;

    String contactName;
}
