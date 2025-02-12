package com.yepdevelopment.spammedaddy;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;
import com.yepdevelopment.spammedaddy.Utils.Data.SerializationUtils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SerializationTests {
    @Test
    public void sampleContactToJsonTest1() {
        System.out.println(Contact.generateSample().toJson());
    }

    @Test
    public void sampleJsonToContactTest1() {
        Contact sample = Contact.generateSample();
        System.out.printf("Original: %s%n", sample.toJson());
        Contact deserializedSample = SerializationUtils.fromJson(sample.toJson(), Contact.class);
        if (deserializedSample == null) Assert.fail();
        System.out.printf("After Deserialization: %s%n", deserializedSample.toJson());
    }

    @Test
    public void sampleContactWithDataToJsonTest1() {
        System.out.println(ContactWithData.generateSample().toJson());
    }

    @Test
    public void sampleJsonToContactWithDataTest1() {
        ContactWithData sample = ContactWithData.generateSample();
        System.out.printf("Original: %s%n", sample.toJson());
        ContactWithData deserializedSample = SerializationUtils.fromJson(sample.toJson(), ContactWithData.class);
        if (deserializedSample == null) Assert.fail();
        System.out.printf("After Deserialization: %s%n", deserializedSample.toJson());
    }
}