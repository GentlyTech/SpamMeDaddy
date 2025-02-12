package com.yepdevelopment.spammedaddy.Helpers.Android;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.yepdevelopment.spammedaddy.Database.AppDatabase;
import com.yepdevelopment.spammedaddy.Database.Relationships.ContactWithData;

public class DatabaseUtils {

    private static AppDatabase appDatabaseInst = null;

    public static AppDatabase getAppDatabase(Context context) {
        if (context == null) throw new IllegalArgumentException("The context cannot be null when getting an instance of the AppDatabase.");
        if (appDatabaseInst == null) {
            appDatabaseInst = Room.databaseBuilder(context, AppDatabase.class, "spammedaddy_db").build();
        }
        return appDatabaseInst;
    }

    public static void insertContactWithData(Context context, ContactWithData data) {
        if (data == null) {
            Log.w(DatabaseUtils.class.getName(), "insertContactWithData: data argument was null. Abort.");
            return;
        }

        AppDatabase database = getAppDatabase(context);

        database.contactDao().insertContacts(data.getContact());
        database.phoneNumberDao().insertPhoneNumbers(data.getPhoneNumbers());
        database.messageDao().insertMessages(data.getMessages());
    }
}
