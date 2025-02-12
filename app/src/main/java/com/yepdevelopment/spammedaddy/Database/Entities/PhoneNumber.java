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
public class PhoneNumber extends JSONSerializable {
    @PrimaryKey
    private String phoneNumberId = null;

    private String contactId = null;

    private String phoneNumber = null;

    public static PhoneNumber generateSample() {
        PhoneNumber inst = new PhoneNumber();
        inst.phoneNumberId = UUID.randomUUID().toString();
        inst.contactId = UUID.randomUUID().toString();
        inst.phoneNumber = UUID.randomUUID().toString();
        return inst;
    }
}
