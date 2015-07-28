// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HeaderHandler.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.http.HttpResponse;

public interface HeaderHandler
{

    public abstract void handle(Object obj, HttpResponse httpresponse);
}
