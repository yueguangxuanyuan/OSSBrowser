// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InitiateMultipartUploadResult.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.http.AOSResponseResult;

public class InitiateMultipartUploadResult extends AOSResponseResult
{

    public InitiateMultipartUploadResult()
    {
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

    public String getUploadId()
    {
        return uploadId;
    }

    public void setUploadId(String uploadId)
    {
        this.uploadId = uploadId;
    }

    private String bucketName;
    private String key;
    private String uploadId;
}
