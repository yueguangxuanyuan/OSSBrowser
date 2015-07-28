// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StringInputStream.java

package com.aliyun.aos.services.oss.internal;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

public class StringInputStream extends ByteArrayInputStream
{

    public StringInputStream(String s)
        throws UnsupportedEncodingException
    {
        super(s.getBytes("UTF-8"));
        string = s;
    }

    public String getString()
    {
        return string;
    }

    private final String string;
}
