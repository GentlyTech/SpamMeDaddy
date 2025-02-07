package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Contact implements Comparable<Contact> {
    @PrimaryKey
    private String contactId;

    private String contactName;

    @Override
    public int compareTo(Contact other) {
        return contactName.compareTo(other.contactName);
    }
}
