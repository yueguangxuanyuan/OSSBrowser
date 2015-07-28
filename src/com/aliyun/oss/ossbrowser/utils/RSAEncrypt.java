// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSAEncrypt.java

package com.aliyun.oss.ossbrowser.utils;

import java.io.*;
import java.security.*;
import java.util.prefs.Preferences;
import javax.crypto.Cipher;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSAEncrypt
{

    public RSAEncrypt()
    {
    }

    private static void generateKeyPair()
        throws Exception
    {
        File publicKeyFile = new File(PUBLIC_KEY_FILE);
        File privateKeyFile = new File(PRIVATE_KEY_FILE);
        if(publicKeyFile.exists() && privateKeyFile.exists())
        {
            return;
        } else
        {
            SecureRandom sr = new SecureRandom();
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
            kpg.initialize(KEYSIZE, sr);
            KeyPair kp = kpg.generateKeyPair();
            Key publicKey = kp.getPublic();
            Key privateKey = kp.getPrivate();
            ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
            ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
            oos1.writeObject(publicKey);
            oos2.writeObject(privateKey);
            oos1.close();
            oos2.close();
            return;
        }
    }

    public static String encrypt(String source)
        throws Exception
    {
        generateKeyPair();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
        Key key = (Key)ois.readObject();
        ois.close();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(1, key);
        byte b[] = source.getBytes();
        byte b1[] = cipher.doFinal(b);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b1);
    }

    public static String decrypt(String cryptograph)
        throws Exception
    {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
        Key key = (Key)ois.readObject();
        ois.close();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(2, key);
        BASE64Decoder decoder = new BASE64Decoder();
        byte b1[] = decoder.decodeBuffer(cryptograph);
        byte b[] = cipher.doFinal(b1);
        return new String(b);
    }

    public static void main(String args[])
        throws Exception
    {
        Preferences prefs = Preferences.userRoot().node("ossbrowser/login");
        String source = "gnrak6vpv77si0t3cjkzzb58";
        String cryptograph = encrypt(source);
        System.out.println(cryptograph);
        prefs.put("LOGIN_ACCESS_ID", cryptograph);
        System.out.println(prefs.get("LOGIN_ACCESS_ID", ""));
        String target = decrypt(cryptograph);
        System.out.println(target);
        target = decrypt(prefs.get("LOGIN_ACCESS_ID", ""));
        System.out.println(target);
    }

    private static String ALGORITHM = "RSA";
    private static int KEYSIZE = 1024;
    private static String PUBLIC_KEY_FILE = "PublicKey";
    private static String PRIVATE_KEY_FILE = "PrivateKey";

}
