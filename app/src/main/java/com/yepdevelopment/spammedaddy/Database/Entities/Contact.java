package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.yepdevelopment.spammedaddy.Types.JSONSerializable;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Contact extends JSONSerializable implements Comparable<Contact> {
    @PrimaryKey
    private String contactId = null;

    private String contactName = null;

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
