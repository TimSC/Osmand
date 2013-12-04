package com.kinatomicSurrey.plus;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;

public class ExtensionDownloaderService extends DownloaderService {
    // You must use the public key belonging to your publisher account
    public static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkCWvkAB4pl8e7b19MhtS/kXRzYfYvMf9eT5/li+WKfBbjr/CN1LCyUk7aylvgu0XK6V9yCdUxAuEm5yQgb9jgGWVFwlmDDjRoq5TjzouXVZ+bZF/uoFYZCnKFMV4rpH8b2cHKbBRLjxIRZLfzOdY+H6VUCwWyEMFOkA1PIZxpmIWsifohI+hIs5RXh56xTzsBwK0DtwN9/aSpxQmQcXAbD9Bd7DZzJXdNe46tH1GMHO+hM5W3pIFlNkXukBSMEJ3cWEZRoAqaNu0jxs4LCuwyo6uWtwyXPGDfldk9/7O19vhodOojLhG5CuRJ7oRBkjW/7oJoPWOT5F+lMAJDxHmCQIDAQAB";
    // You should also modify this salt
    public static final byte[] SALT = new byte[] { 73, -35, -110, -44, 59, -125, 52, 6, -26, -83, -45, -5, 33, -86, -67, -124, 103, -116, -74, 67 };

    @Override
    public String getPublicKey() {
        return BASE64_PUBLIC_KEY;
    }

    @Override
    public byte[] getSALT() {
        return SALT;
    }

    @Override
    public String getAlarmReceiverClassName() {
        return ExtensionAlarmReceiver.class.getName();
    }
}

