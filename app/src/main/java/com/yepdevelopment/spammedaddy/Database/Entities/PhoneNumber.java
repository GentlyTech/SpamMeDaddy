package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(foreignKeys = {
        @ForeignKey(entity = Contact.class, parentColumns = "contactId", childColumns = "contactId", onDelete = ForeignKey.CASCADE)
})
public class PhoneNumber {
    @PrimaryKey
    @NonNull
    private String phoneNumberId = "";

    private String contactId = "";

    private String phoneNumber = "";

    @NonNull
    @Override
    public String toString() {
        return phoneNumber;
    }

    public static PhoneNumber generateSample() {
        PhoneNumber inst = new PhoneNumber();
        inst.phoneNumberId = UUID.randomUUID().toString();
        inst.contactId = UUID.randomUUID().toString();
        inst.phoneNumber = UUID.randomUUID().toString();
        return inst;
    }
}
