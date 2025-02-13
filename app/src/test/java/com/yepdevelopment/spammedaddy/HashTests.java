package com.yepdevelopment.spammedaddy;

import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Helpers.General.MD5;

import org.junit.Assert;
import org.junit.Test;

public class HashTests {

    @Test
    public void testMD5Hash1() {
        Assert.assertEquals(MD5.hash("apple"), MD5.hash("apple"));
    }

    @Test
    public void testMD5Hash2() {
        Assert.assertNotEquals(MD5.hash("apple"), MD5.hash("banana"));
    }

}