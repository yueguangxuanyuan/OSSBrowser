// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StaticCredentialsProvider.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.auth.AOSCredentials;
import com.aliyun.aos.auth.AOSCredentialsProvider;

public class StaticCredentialsProvider
    implements AOSCredentialsProvider
{

    public StaticCredentialsProvider(AOSCredentials credentials)
    {
        this.credentials = credentials;
    }

    public AOSCredentials getCredentials()
    {
        return credentials;
    }

    public void refresh()
    {
    }

    private final AOSCredentials credentials;
}
