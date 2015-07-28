// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbortMultipartUploadRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;

public class AbortMultipartUploadRequest extends AOSWebServiceRequest
{

    public AbortMultipartUploadRequest(String bucketName, String key, String uploadId)
    {
        this.bucketName = bucketName;
        this.key = key;
        this.uploadId = uploadId;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String value)
    {
        bucketName = value;
    }

    public AbortMultipartUploadRequest withBucketName(String bucketName)
    {
        this.bucketName = bucketName;
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

    public AbortMultipartUploadRequest withKey(String key)
    {
        this.key = key;
        return this;
    }

    public String getUploadId()
    {
        return uploadId;
    }

    public void setUploadId(String uploadId)
    {
        this.uploadId = uploadId;
    }

    public AbortMultipartUploadRequest withUploadId(String uploadId)
    {
        this.uploadId = uploadId;
        return this;
    }

    private String bucketName;
    private String key;
    private String uploadId;
}
