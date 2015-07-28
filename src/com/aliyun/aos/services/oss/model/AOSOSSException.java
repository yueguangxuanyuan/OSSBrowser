// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AOSOSSException.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSServiceException;

public class AOSOSSException extends AOSServiceException
{

    public AOSOSSException(String message)
    {
        super(message);
    }

    public AOSOSSException(String message, Exception cause)
    {
        super(message, cause);
    }

    private static final long serialVersionUID = 0x93bbdf3000180d6L;
}
