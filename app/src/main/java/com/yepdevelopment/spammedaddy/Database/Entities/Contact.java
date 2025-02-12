package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.yepdevelopment.spammedaddy.Types.JSONSerializable;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Contact extends JSONSerializable implements Comparable<Contact> {
    @PrimaryKey
    private String contactId = null;

    private String contactName = null;

    @Override
    public int compareTo(Contact other) {
        return contactName.compareTo(other.contactName);
    }
}
