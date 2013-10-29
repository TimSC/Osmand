package com.kinatomicWsus.plus;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;

public class ExtensionDownloaderService extends DownloaderService {
    // You must use the public key belonging to your publisher account
    public static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjBhEHq8QE5LN+qqn8UPYQnKzT9BoBtvzOmHxVOUddrrzZEH73orqz2e79PqIFkhMhCzFQp5BRTZDbLDEho0mb8cX4aBHDrChZP3oDVQAuvSWPw9Yf7nxW4WRZ7zkFO8r4AsGGLtWHnw0Nj5KS2LeteRA82M4T6Pv6p46ZRMsVOhmJTDkc/NjB1Pjw8pZleUQoUfiUs8ddnyIoCidQ/pRNVn0K0so5djWeDEGlnLkembVoRFAxsVyyilxZSFeNKLyLUkIElxzlJT3tBT8R1G+ZLadvjn+Fk7iO5NvQkCQ2QKkBBS3MxZjVdPHBpmdPYjMbBfzSgbsL1jerBuwmMpeDQIDAQAB";
    // You should also modify this salt

    public static final byte[] SALT = new byte[] { 95, 1, 51, -56, 1, -9,
            35, 100, -99, 63, 53, -36, -5, 51, -23, 35, 35, 98, -43, 43
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

