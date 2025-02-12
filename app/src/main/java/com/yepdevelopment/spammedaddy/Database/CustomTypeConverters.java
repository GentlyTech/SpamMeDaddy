package com.yepdevelopment.spammedaddy.Database;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;

public class CustomTypeConverters {
    @TypeConverter
    public static LocalDateTime fromTimestamp(String timestamp) {
        return timestamp != null ? LocalDateTime.parse(timestamp) : null;
    }

    @TypeConverter
    public static String dateToTimestamp(LocalDateTime date) {
        return date != null ? date.toString() : null;
    }
}
