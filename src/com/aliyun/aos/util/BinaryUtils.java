// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BinaryUtils.java

package com.aliyun.aos.util;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BinaryUtils
{

    public BinaryUtils()
    {
    }

    public static String toHex(byte data[])
    {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for(int i = 0; i < data.length; i++)
        {
            String hex = Integer.toHexString(data[i]);
            if(hex.length() == 1)
                sb.append("0");
            else
            if(hex.length() == 8)
                hex = hex.substring(6);
            sb.append(hex);
        }

        return sb.toString().toLowerCase(Locale.getDefault());
    }

    public static byte[] fromHex(String hexData)
    {
        byte result[] = new byte[(hexData.length() + 1) / 2];
        String hexNumber = null;
        int stringOffset = 0;
        int byteOffset = 0;
        while(stringOffset < hexData.length()) 
        {
            hexNumber = hexData.substring(stringOffset, stringOffset + 2);
            stringOffset += 2;
            result[byteOffset++] = (byte)Integer.parseInt(hexNumber, 16);
        }
        return result;
    }

    public static String toBase64(byte data[])
    {
        byte b64[] = Base64.encodeBase64(data);
        return new String(b64);
    }

    public static byte[] fromBase64(String b64Data)
    {
        byte decoded[];
        try
        {
            decoded = Base64.decodeBase64(b64Data.getBytes("UTF-8"));
        }
        catch(UnsupportedEncodingException uee)
        {
            log.warn("Tried to Base64-decode a String with the wrong encoding: ", uee);
            decoded = Base64.decodeBase64(b64Data.getBytes());
        }
        return decoded;
    }

    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final Log log = LogFactory.getLog(com/aliyun/aos/util/BinaryUtils);

}
