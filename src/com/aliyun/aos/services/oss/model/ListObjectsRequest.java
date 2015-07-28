// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ListObjectsRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;

public class ListObjectsRequest extends AOSWebServiceRequest
{

    public ListObjectsRequest()
    {
    }

    public ListObjectsRequest(String bucketName, String prefix, String marker, String delimiter, Integer maxKeys)
    {
        setBucketName(bucketName);
        setPrefix(prefix);
        setMarker(marker);
        setDelimiter(delimiter);
        setMaxKeys(maxKeys);
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public ListObjectsRequest withBucketName(String bucketName)
    {
        setBucketName(bucketName);
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

    public ListObjectsRequest withPrefix(String prefix)
    {
        setPrefix(prefix);
        return this;
    }

    public String getMarker()
    {
        return marker;
    }

    public void setMarker(String marker)
    {
        this.marker = marker;
    }

    public ListObjectsRequest withMarker(String marker)
    {
        setMarker(marker);
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

    public ListObjectsRequest withDelimiter(String delimiter)
    {
        setDelimiter(delimiter);
        return this;
    }

    public Integer getMaxKeys()
    {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys)
    {
        this.maxKeys = maxKeys;
    }

    public ListObjectsRequest withMaxKeys(Integer maxKeys)
    {
        setMaxKeys(maxKeys);
        return this;
    }

    private String bucketName;
    private String prefix;
    private String marker;
    private String delimiter;
    private Integer maxKeys;
}
