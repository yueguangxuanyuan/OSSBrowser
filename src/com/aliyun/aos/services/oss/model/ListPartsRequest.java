// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ListPartsRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;

public class ListPartsRequest extends AOSWebServiceRequest
{

    public ListPartsRequest(String bucketName, String key, String uploadId)
    {
        this.bucketName = bucketName;
        this.key = key;
        this.uploadId = uploadId;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public ListPartsRequest withBucketName(String bucketName)
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

    public ListPartsRequest withKey(String key)
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

    public ListPartsRequest withUploadId(String uploadId)
    {
        this.uploadId = uploadId;
        return this;
    }

    public Integer getMaxParts()
    {
        return maxParts;
    }

    public void setMaxParts(int maxParts)
    {
        this.maxParts = Integer.valueOf(maxParts);
    }

    public ListPartsRequest withMaxParts(int maxParts)
    {
        this.maxParts = Integer.valueOf(maxParts);
        return this;
    }

    public Integer getPartNumberMarker()
    {
        return partNumberMarker;
    }

    public void setPartNumberMarker(Integer partNumberMarker)
    {
        this.partNumberMarker = partNumberMarker;
    }

    public ListPartsRequest withPartNumberMarker(Integer partNumberMarker)
    {
        this.partNumberMarker = partNumberMarker;
        return this;
    }

    private String bucketName;
    private String key;
    private String uploadId;
    private Integer maxParts;
    private Integer partNumberMarker;
}
