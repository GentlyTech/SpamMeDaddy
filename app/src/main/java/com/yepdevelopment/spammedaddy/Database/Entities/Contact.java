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
public class Contact implements Comparable<Contact>, JSONSerializable {
    @PrimaryKey
    private String contactId = null;

    private String contactName = null;

    @Nullable
    public static Contact fromJson(JSONObject jsonObject) {
        if (jsonObject == null) return null;

        try {
            Contact inst = new Contact();

            if (jsonObject.has("contactId")) {
                inst.contactId = jsonObject.getString("contactId");
            }

            if (jsonObject.has("contactName")) {
                inst.contactName = jsonObject.getString("contactName");
            }

            return inst;
        }
        catch (JSONException ignored) {

        }

        return null;
    }

    @Nullable
    public static Contact fromJson(String jsonString) {
        try {
            return fromJson(new JSONObject(jsonString));
        }
        catch (JSONException ignored) {

        }
        return null;
    }

    @Override
    public int compareTo(Contact other) {
        return contactName.compareTo(other.contactName);
    }

    @Override
    @Nullable
    public JSONObject toJson() {
        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("contactId", contactId);
            jsonObject.put("contactName", contactName);

            return jsonObject;
        } catch (JSONException ignored) {

        }

        return null;
    }
}
