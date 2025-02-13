package com.yepdevelopment.spammedaddy.Database;

import android.util.Log;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.yepdevelopment.spammedaddy.Database.DAOs.ContactDao;
import com.yepdevelopment.spammedaddy.Database.DAOs.MessageDao;
import com.yepdevelopment.spammedaddy.Database.DAOs.PhoneNumberDao;
import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Entities.Message;
import com.yepdevelopment.spammedaddy.Database.Entities.PhoneNumber;
import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;
import com.yepdevelopment.spammedaddy.Helpers.Android.DatabaseUtils;

@Database(entities = {Contact.class, Message.class, PhoneNumber.class}, version = 1)
@TypeConverters({CustomTypeConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();

    public abstract MessageDao messageDao();

    public abstract PhoneNumberDao phoneNumberDao();

    public void insertContactWithData(ContactWithData data) {
        if (data == null) {
            Log.w(DatabaseUtils.class.getName(), "insertContactWithData: data argument was null. Abort.");
            return;
        }

        contactDao().insertContacts(data.getContact());
        phoneNumberDao().insertPhoneNumbers(data.getPhoneNumbers());
        messageDao().insertMessages(data.getMessages());
    }

    public void wipeDatabase() {
        contactDao().deleteAllContacts();
        phoneNumberDao().deleteAllPhoneNumbers();
        messageDao().deleteAllMessages();
    }
}
