// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InitiateMultipartUploadRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            ObjectMetadata

public class InitiateMultipartUploadRequest extends AOSWebServiceRequest
{

    public InitiateMultipartUploadRequest(String bucketName, String key)
    {
        this.bucketName = bucketName;
        this.key = key;
    }

    public InitiateMultipartUploadRequest(String bucketName, String key, ObjectMetadata objectMetadata)
    {
        this.bucketName = bucketName;
        this.key = key;
        this.objectMetadata = objectMetadata;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public InitiateMultipartUploadRequest withBucketName(String bucketName)
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

    public InitiateMultipartUploadRequest withKey(String key)
    {
        this.key = key;
        return this;
    }

    public ObjectMetadata getObjectMetadata()
    {
        return objectMetadata;
    }

    public void setObjectMetadata(ObjectMetadata objectMetadata)
    {
        this.objectMetadata = objectMetadata;
    }

    public InitiateMultipartUploadRequest withObjectMetadata(ObjectMetadata objectMetadata)
    {
        setObjectMetadata(objectMetadata);
        return this;
    }

    private String bucketName;
    private String key;
    public ObjectMetadata objectMetadata;
}
