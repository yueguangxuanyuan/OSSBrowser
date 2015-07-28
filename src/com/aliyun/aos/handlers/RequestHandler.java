// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequestHandler.java

package com.aliyun.aos.handlers;

import com.aliyun.aos.Request;
import com.aliyun.aos.util.TimingInfo;

public abstract class RequestHandler
{

    public RequestHandler()
    {
    }

    public void beforeRequest(Request request1)
    {
    }

    public void afterResponse(Request request1, Object obj, TimingInfo timinginfo)
    {
    }

    public void afterError(Request request1, Exception exception)
    {
    }
}
