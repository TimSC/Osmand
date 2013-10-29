package com.kinatomicEsus.plus;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;

public class ExtensionDownloaderService extends DownloaderService {
    // You must use the public key belonging to your publisher account
    public static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAloM6XAiNwK9f6HWplfN6J+5WpdlYeuVqzPJtWUKp/AkuYAnKV3LjOPuo2egTrrycFYBx6GiwuUaTmZb/GNztWx+FRT9GJpJNEXplWs5SvSxY/6MVUpQndyXAqiqmscBpz7NcGDcsdLwheZKlBpd5YR5YfSpkwgstHmKjKtix0m/ns2CLoKq/b1Rp4iqiENqh1pDohx3hFZ9DHxIyQaQNi5IJt/DbkhucG07PS8lOb6ZlSGcFDuuaFcDfBGRDq5osfNr/fW837OEatYTl0TztnqnYh/dAdKnXt5uZA+XD6vZV9RPwVIk82EVxDAKm1URuEB1ZQo60TSPI/zaUF4txiwIDAQAB";
    // You should also modify this salt
    public static final byte[] SALT = new byte[] { 73, -35, -110, -44, 59, -19, 52, 5, -26, -83, -45, -7, 32, -86, -67, -124, 103, -116, -74, 25 };

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

