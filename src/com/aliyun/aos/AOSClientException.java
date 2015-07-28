// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AOSClientException.java

package com.aliyun.aos;


public class AOSClientException extends RuntimeException
{

    public AOSClientException(String msg, Throwable t)
    {
        super(msg, t);
    }

    public AOSClientException(String msg)
    {
        super(msg);
    }

    private static final long serialVersionUID = 0x2f2802764f68fb95L;
}
