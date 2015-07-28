// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MD5DigestCalculatingInputStream.java

package com.aliyun.aos.services.oss.internal;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5DigestCalculatingInputStream extends FilterInputStream
{

    public MD5DigestCalculatingInputStream(InputStream in)
        throws NoSuchAlgorithmException
    {
        super(in);
        digest = MessageDigest.getInstance("MD5");
    }

    public byte[] getMd5Digest()
    {
        return digest.digest();
    }

    public synchronized void reset()
        throws IOException
    {
        try
        {
            digest = MessageDigest.getInstance("MD5");
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception) { }
        in.reset();
    }

    public int read()
        throws IOException
    {
        int ch = in.read();
        if(ch != -1)
            digest.update((byte)ch);
        return ch;
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        int result = in.read(b, off, len);
        if(result != -1)
            digest.update(b, off, result);
        return result;
    }

    private MessageDigest digest;
}
