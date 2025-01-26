package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey
    public String contactId;

    public String contactName;
}
