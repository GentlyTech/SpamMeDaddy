package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Contact implements Comparable<Contact> {
    @PrimaryKey
    @NonNull
    private String contactId = "";

    private String contactName = "";

    public static Contact generateSample() {
        Contact inst = new Contact();
        inst.contactId = UUID.randomUUID().toString();
        inst.contactName = UUID.randomUUID().toString();
        return inst;
    }

    @Override
    public int compareTo(Contact other) {
        return contactName.compareTo(other.contactName);
    }
}
