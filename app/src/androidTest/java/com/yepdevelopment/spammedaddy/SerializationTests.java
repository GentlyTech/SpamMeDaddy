package com.yepdevelopment.spammedaddy;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Utils.Data.SerializationUtils;

@RunWith(AndroidJUnit4.class)
public class SerializationTests {
    private static final Contact CONTACT_1 = new Contact() {

        String contactId = "0";
        String contactName = "Big Smoke";
    };

    @Test
    public void sampleContactToJsonTest1() {
        System.out.println(CONTACT_1.toJson());
    }

    @Test
    public void sampleJsonToContactTest1() {
        System.out.println(SerializationUtils.fromJson(CONTACT_1.toJson(), Contact.class).toJson());
    }
}