package com.yepdevelopment.spammedaddy.Utils;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.yepdevelopment.spammedaddy.Types.Contact;

import java.util.LinkedList;
import java.util.List;

public class ContactUtils {
    public static final String[] ADD_RECIPIENTS_CONTACTS_PROJECTION = {
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };

    public static List<Contact> getContacts(Context context) {
        LinkedList<Contact> contacts = new LinkedList<>();
        if (context == null) return contacts;
        ContentResolver contentResolver = context.getContentResolver();

        try (Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, ADD_RECIPIENTS_CONTACTS_PROJECTION, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC")) {
            if (cursor == null) return contacts;

            final int nameIndex = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
            final int phoneNumberIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);

            while (cursor.moveToNext()) {
                Contact contact = new Contact(cursor.getString(nameIndex), cursor.getString(phoneNumberIndex));
                contacts.add(contact);
            }
        }

        return contacts;
    }

    public static boolean hasContactsPermission(Context context) {
        if (context == null) return false;
        return context.checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }
}
