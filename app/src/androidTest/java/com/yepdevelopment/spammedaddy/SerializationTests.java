package com.yepdevelopment.spammedaddy;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;
import com.yepdevelopment.spammedaddy.Utils.Data.SerializationUtils;

@RunWith(AndroidJUnit4.class)
public class SerializationTests {
    @Test
    public void sampleContactToJsonTest1() {
        System.out.println(Contact.generateSample().toJson());
    }

    @Test
    public void sampleJsonToContactTest1() {
        System.out.println(SerializationUtils.fromJson(Contact.generateSample().toJson(), Contact.class).toJson());
    }

    @Test
    public void sampleContactWithDataToJsonTest1() {
        System.out.println(ContactWithData.generateSample().toJson());
    }
}