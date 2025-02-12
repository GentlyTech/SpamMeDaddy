package com.yepdevelopment.spammedaddy;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.gson.Gson;
import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;
import com.yepdevelopment.spammedaddy.Gson.CustomSerializer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SerializationTests {
    Gson serializer = CustomSerializer.getCustomSerializer();

    @Test
    public void sampleContactToJsonTest1() {
        System.out.println(serializer.toJson(Contact.generateSample()));
    }

    @Test
    public void sampleJsonToContactTest1() {
        Contact sample = Contact.generateSample();
        String jsonSample = serializer.toJson(sample);
        System.out.printf("Original: %s%n", jsonSample);
        Contact deserializedSample = serializer.fromJson(jsonSample, Contact.class);
        if (deserializedSample == null) Assert.fail();
        System.out.printf("After Deserialization: %s%n", serializer.toJson(deserializedSample));
    }

    @Test
    public void sampleContactWithDataToJsonTest1() {
        System.out.println(serializer.toJson(ContactWithData.generateSample()));
    }

    @Test
    public void sampleJsonToContactWithDataTest1() {
        ContactWithData sample = ContactWithData.generateSample();
        String jsonSample = serializer.toJson(sample);
        System.out.printf("Original: %s%n", jsonSample);
        ContactWithData deserializedSample = serializer.fromJson(jsonSample, ContactWithData.class);
        if (deserializedSample == null) Assert.fail();
        System.out.printf("After Deserialization: %s%n", serializer.toJson(deserializedSample));
    }
}