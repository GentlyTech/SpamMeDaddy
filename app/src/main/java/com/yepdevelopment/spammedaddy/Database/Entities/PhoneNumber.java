package com.yepdevelopment.spammedaddy.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.yepdevelopment.spammedaddy.Types.JSONSerializable;

import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PhoneNumber implements JSONSerializable {
    @PrimaryKey
    private String phoneNumberId;

    private String contactId;

    private String phoneNumber;

    public static PhoneNumber fromJson(JSONObject jsonObject) {
        return null; // TODO implement method
    }

    public static PhoneNumber fromJson(String jsonString) {
        return null; // TODO implement method
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();

        // TODO implement method

        return jsonObject;
    }
}
