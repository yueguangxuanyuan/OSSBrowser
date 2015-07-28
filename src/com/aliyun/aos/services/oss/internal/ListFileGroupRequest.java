// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ListFileGroupRequest.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.AOSWebServiceRequest;

public class ListFileGroupRequest extends AOSWebServiceRequest
{

    public ListFileGroupRequest()
    {
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    private String key;
    private String bucketName;
}
