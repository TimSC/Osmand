package com.kinatomicWsus.plus;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;

public class ExtensionDownloaderService extends DownloaderService {
    // You must use the public key belonging to your publisher account
    public static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk0BASiqccaDOSy8CIvsn/sJh7vcWObSOrgDUshrok55naYV0mAC7YJfKnHA9BChrZmX8k25bYL0bwtmza/T0wTfGtKSA9YCDPeFl1aH6QMRpOBtPJMQ/pI/6YAuwwHLDLQQphLcHEss0hw/6q8pVAOAxiJhWLkJWnKC0Lm33WeiX97oQZocuSJ2bFoFlm+curBd5Oj/1udkVinCCEHwrMmQeajnzoc6X/LcsN8P8RJFxC+mJNbdfcT4ItN2YHE8EfWuNVLAWzsjdJxSM1O5Hcn/5q9g0zV52F80Cdlws2XY1PBNcR5aVPca/rwq+hJy7IANgqEcz9DRQxOjYy3yEvQIDAQAB";
    // You should also modify this salt
    public static final byte[] SALT = new byte[] { 5, 3, -10, 2, 52, -3,
            44, -22, 35, 21, -30, -60, 57, 35, -120, -55, -32, 34, -5, 45
    };

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

