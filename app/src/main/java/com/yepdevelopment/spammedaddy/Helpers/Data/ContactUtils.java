package com.yepdevelopment.spammedaddy.Helpers.Data;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;

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

    private static List<ContactWithData> processContactRowsFromCursor(Cursor cursor) {
        if (cursor == null) return new LinkedList<>();

        Map<String, ContactWithData> contactMap = new HashMap<>();

        final int nameIndex = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
        final int phoneNumberIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);

        while (cursor.moveToNext()) {
            ContactWithData contact;
            String displayName = cursor.getString(nameIndex);
            String phoneNumber = cursor.getString(phoneNumberIndex);

            if (contactMap.containsKey(displayName)) {
                contact = contactMap.get(displayName);
            }
            else {
                contact = ContactWithData.newInstance(displayName);
                contactMap.put(displayName, contact);
            }

            if (contact == null) continue;
            contact.addPhoneNumber(phoneNumber);
        }

        List<ContactWithData> contacts = new LinkedList<>(contactMap.values());
        contacts.sort(Comparator.comparing(ContactWithData::getContact));
        return contacts;
    }

    public static List<ContactWithData> getContacts(Context context) {
        if (context == null) return new LinkedList<>();
        ContentResolver contentResolver = context.getContentResolver();

        try (Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, ADD_RECIPIENTS_CONTACTS_PROJECTION, null, null, null)) {
            return processContactRowsFromCursor(cursor);
        }
    }

    public static List<ContactWithData> getContacts(Context context, String query) {
        if (context == null) return new LinkedList<>();
        ContentResolver contentResolver = context.getContentResolver();

        String wildcardQuery = String.format("%%%s%%", query);

        try (Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, ADD_RECIPIENTS_CONTACTS_PROJECTION, String.format("%s LIKE ? OR %s LIKE ? OR %s LIKE ?", ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER), new String[]{wildcardQuery, wildcardQuery, wildcardQuery}, null)) {
            return processContactRowsFromCursor(cursor);
        }
    }

    public static boolean hasContactsPermission(Context context) {
        if (context == null) return false;
        return context.checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }
}
