// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ListMultipartUploadsRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;

public class ListMultipartUploadsRequest extends AOSWebServiceRequest
{

    public ListMultipartUploadsRequest(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public ListMultipartUploadsRequest withBucketName(String bucketName)
    {
        this.bucketName = bucketName;
        return this;
    }

    public Integer getMaxUploads()
    {
        return maxUploads;
    }

    public void setMaxUploads(Integer maxUploads)
    {
        this.maxUploads = maxUploads;
    }

    public ListMultipartUploadsRequest withMaxUploads(int maxUploadsInt)
    {
        maxUploads = Integer.valueOf(maxUploadsInt);
        return this;
    }

    public String getKeyMarker()
    {
        return keyMarker;
    }

    public void setKeyMarker(String keyMarker)
    {
        this.keyMarker = keyMarker;
    }

    public ListMultipartUploadsRequest withKeyMarker(String keyMarker)
    {
        this.keyMarker = keyMarker;
        return this;
    }

    public String getUploadIdMarker()
    {
        return uploadIdMarker;
    }

    public void setUploadIdMarker(String uploadIdMarker)
    {
        this.uploadIdMarker = uploadIdMarker;
    }

    public ListMultipartUploadsRequest withUploadIdMarker(String uploadIdMarker)
    {
        this.uploadIdMarker = uploadIdMarker;
        return this;
    }

    public String getDelimiter()
    {
        return delimiter;
    }

    public void setDelimiter(String delimiter)
    {
        this.delimiter = delimiter;
    }

    public ListMultipartUploadsRequest withDelimiter(String delimiter)
    {
        setDelimiter(delimiter);
        return this;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    public ListMultipartUploadsRequest withPrefix(String prefix)
    {
        setPrefix(prefix);
        return this;
    }

    private String bucketName;
    private String delimiter;
    private String prefix;
    private Integer maxUploads;
    private String keyMarker;
    private String uploadIdMarker;
}
