package com.yepdevelopment.spammedaddy.Helpers.General;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5 {
    private static MessageDigest getMD5MessageDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ignored) {
            throw new IllegalStateException("The MessageDigest does not recognize the supplied algorithm (this should never happen).");
        }
    }

    public static String hash(String input) {
        return Base64.getEncoder().encodeToString(getMD5MessageDigest().digest(input.getBytes(StandardCharsets.UTF_8)));
    }
}
