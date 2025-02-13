package com.yepdevelopment.spammedaddy.Helpers.Android;

import android.content.Context;

import androidx.room.Room;

import com.yepdevelopment.spammedaddy.Database.AppDatabase;

public class DatabaseUtils {

    private static AppDatabase appDatabaseInst = null;

    public static AppDatabase getAppDatabase(Context context) {
        if (context == null)
            throw new IllegalArgumentException("The context cannot be null when getting an instance of the AppDatabase.");
        if (appDatabaseInst == null) {
            appDatabaseInst = Room.databaseBuilder(context, AppDatabase.class, "spammedaddy_db").build();
        }
        return appDatabaseInst;
    }
}
