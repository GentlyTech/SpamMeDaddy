package com.yepdevelopment.spammedaddy.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.yepdevelopment.spammedaddy.Database.DAOs.ContactDao;
import com.yepdevelopment.spammedaddy.Database.DAOs.MessageDao;
import com.yepdevelopment.spammedaddy.Database.DAOs.PhoneNumberDao;
import com.yepdevelopment.spammedaddy.Database.Entities.Contact;
import com.yepdevelopment.spammedaddy.Database.Entities.Message;
import com.yepdevelopment.spammedaddy.Database.Entities.PhoneNumber;

@Database(entities = {Contact.class, Message.class, PhoneNumber.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public ContactDao contactDAO;

    public MessageDao messageDao;

    public PhoneNumberDao phoneNumberDao;
}
