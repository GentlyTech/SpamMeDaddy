package com.yepdevelopment.spammedaddy.Types;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Contact {
    private final String name;
    private final List<String> phoneNumbers;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumbers = new LinkedList<>();
        if (phoneNumber != null) this.phoneNumbers.add(phoneNumber);
    }

    public Contact(String name, List<String> phoneNumbers) {
        this.name = name;
        this.phoneNumbers = phoneNumbers != null ? phoneNumbers : new LinkedList<>();
    }

    public Contact(String name, String[] phoneNumbers) {
        this.name = name;
        this.phoneNumbers = phoneNumbers != null ? Arrays.asList(phoneNumbers) : new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getPhoneNumbers() {
        return Collections.unmodifiableList(phoneNumbers);
    }

    public void addPhoneNumber(String phoneNumber) {
        phoneNumbers.add(phoneNumber);
    }
}
