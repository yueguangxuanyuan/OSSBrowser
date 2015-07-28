// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResponseMetadata.java

package com.aliyun.aos;

import java.util.Map;

public class ResponseMetadata
{

    public ResponseMetadata(Map metaData)
    {
        this.metaData = metaData;
    }

    public String getRequestId()
    {
        return (String)metaData.get("AOS_REQUEST_ID");
    }

    public String toString()
    {
        return metaData.toString();
    }

    public static final String AOS_REQUEST_ID = "AOS_REQUEST_ID";
    protected final Map metaData;
}
