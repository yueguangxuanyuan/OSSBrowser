// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AOSResponseResult.java

package com.aliyun.aos.http;


public abstract class AOSResponseResult
{

    public AOSResponseResult()
    {
    }

    public String GetRequsetId()
    {
        return requestId;
    }

    public void SetRequsetId(String requestId)
    {
        this.requestId = requestId;
    }

    protected String requestId;
}
