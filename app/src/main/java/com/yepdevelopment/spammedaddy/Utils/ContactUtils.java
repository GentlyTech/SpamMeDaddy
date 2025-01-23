package com.yepdevelopment.spammedaddy.Utils;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.yepdevelopment.spammedaddy.Types.Contact;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ContactUtils {
    public static final String[] ADD_RECIPIENTS_CONTACTS_PROJECTION = {
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };

    private static List<Contact> processContactRowsFromCursor(Cursor cursor) {
        if (cursor == null) return new LinkedList<>();

        Map<String, Contact> contactMap = new HashMap<>();

        final int nameIndex = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
        final int phoneNumberIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);

        while (cursor.moveToNext()) {
            Contact contact;
            String displayName = cursor.getString(nameIndex);
            String phoneNumber = cursor.getString(phoneNumberIndex);

            if (contactMap.containsKey(displayName)) {
                contact = contactMap.get(displayName);
                if (contact == null) continue;
                contact.addPhoneNumber(phoneNumber);
                continue;
            }
            contact = new Contact(displayName, phoneNumber);
            contactMap.put(displayName, contact);
        }

        List<Contact> contacts = new LinkedList<>(contactMap.values());
        contacts.sort(Comparator.comparing(Contact::getName));
        return contacts;
    }

    public static List<Contact> getContacts(Context context) {
        if (context == null) return new LinkedList<>();
        ContentResolver contentResolver = context.getContentResolver();

        try (Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, ADD_RECIPIENTS_CONTACTS_PROJECTION, null, null, null)) {
            return processContactRowsFromCursor(cursor);
        }
    }

    public static List<Contact> getContacts(Context context, String query) {
        if (context == null) return new LinkedList<>();
        ContentResolver contentResolver = context.getContentResolver();

        try (Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, ADD_RECIPIENTS_CONTACTS_PROJECTION, String.format("%s LIKE ? OR %s LIKE ?", ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER), new String[]{String.format("%%%s%%", query), String.format("%%%s%%", query)}, null)) {
            return processContactRowsFromCursor(cursor);
        }
    }

    public static boolean hasContactsPermission(Context context) {
        if (context == null) return false;
        return context.checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }
}
