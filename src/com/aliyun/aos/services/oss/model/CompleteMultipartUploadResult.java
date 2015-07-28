// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompleteMultipartUploadResult.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.http.AOSResponseResult;

public class CompleteMultipartUploadResult extends AOSResponseResult
{

    public CompleteMultipartUploadResult()
    {
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getETag()
    {
        return eTag;
    }

    public void setETag(String etag)
    {
        eTag = etag;
    }

    private String bucketName;
    private String key;
    private String location;
    private String eTag;
}
