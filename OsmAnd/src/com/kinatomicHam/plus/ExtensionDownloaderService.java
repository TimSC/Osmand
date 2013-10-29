package com.kinatomicHam.plus;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;

public class ExtensionDownloaderService extends DownloaderService {
    // You must use the public key belonging to your publisher account
    public static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0F/GnEq7TXYjQ0KJa6Kz0Q9DetRQSQE/+2Zbv6q6DKqxetSUGWh8HNnwdaXhTFMQHCzAcejksT7rRrwLAsYmufh0kzMk2PNTXuvBv6SkNRQ49YR7arL/FroGTdEe/Cmgu+sfxsqIAc00n8tbwF7CruQ+xClI/BkGXOVP8tNFGMGVj5V+3pNbRr5GUiW0KunwBBx9x3edhwThVJGfCrwcFxMCEqWBLl1NdwjRByqtq7XlFKwt3/tTZVbqfBIMXD3KRoQbkSg8LoZoKZ6zwMFK1m3B4Md2Lm8sERL80Iwy94NrjsxIKFV9XCAXFhB4fJ1CagRM/4VzncdNnWNwyc+jEQIDAQAB";

    // You should also modify this salt
    public static final byte[] SALT = new byte[] { -22, -108, 35, 119, -62, 110, 51, 114, -96, -99, -103, 5, -1, -8, 5, -112, -62, 79, 40, 45 };

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

