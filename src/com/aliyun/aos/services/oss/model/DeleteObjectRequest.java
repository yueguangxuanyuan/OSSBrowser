// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteObjectRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;

public class DeleteObjectRequest extends AOSWebServiceRequest
{

    public DeleteObjectRequest(String bucketName, String key)
    {
        setBucketName(bucketName);
        setKey(key);
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public DeleteObjectRequest withBucketName(String bucketName)
    {
        setBucketName(bucketName);
        return this;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public DeleteObjectRequest withKey(String key)
    {
        setKey(key);
        return this;
    }

    private String bucketName;
    private String key;
}
