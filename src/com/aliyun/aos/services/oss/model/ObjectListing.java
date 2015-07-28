// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ObjectListing.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.http.AOSResponseResult;
import java.util.ArrayList;
import java.util.List;

public class ObjectListing extends AOSResponseResult
{

    public ObjectListing()
    {
        objectSummaries = new ArrayList();
        commonPrefixes = new ArrayList();
    }

    public List getObjectSummaries()
    {
        return objectSummaries;
    }

    public List getCommonPrefixes()
    {
        return commonPrefixes;
    }

    public void setCommonPrefixes(List commonPrefixes)
    {
        this.commonPrefixes = commonPrefixes;
    }

    public void setObjectSummaries(List objectSummaries)
    {
        this.objectSummaries = objectSummaries;
    }

    public String getNextMarker()
    {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker)
    {
        this.nextMarker = nextMarker;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    public String getMarker()
    {
        return marker;
    }

    public void setMarker(String marker)
    {
        this.marker = marker;
    }

    public int getMaxKeys()
    {
        return maxKeys;
    }

    public void setMaxKeys(int maxKeys)
    {
        this.maxKeys = maxKeys;
    }

    public String getDelimiter()
    {
        return delimiter;
    }

    public void setDelimiter(String delimiter)
    {
        this.delimiter = delimiter;
    }

    public boolean isTruncated()
    {
        return isTruncated;
    }

    public void setTruncated(boolean isTruncated)
    {
        this.isTruncated = isTruncated;
    }

    private List objectSummaries;
    private List commonPrefixes;
    private String bucketName;
    private String nextMarker;
    private boolean isTruncated;
    private String prefix;
    private String marker;
    private int maxKeys;
    private String delimiter;
}
