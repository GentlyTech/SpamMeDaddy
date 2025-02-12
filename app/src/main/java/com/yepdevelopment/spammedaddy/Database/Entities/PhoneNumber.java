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
public class PhoneNumber {
    @PrimaryKey
    @NonNull
    private String phoneNumberId = "";

    private String contactId = "";

    private String phoneNumber = "";

    public static PhoneNumber generateSample() {
        PhoneNumber inst = new PhoneNumber();
        inst.phoneNumberId = UUID.randomUUID().toString();
        inst.contactId = UUID.randomUUID().toString();
        inst.phoneNumber = UUID.randomUUID().toString();
        return inst;
    }
}
