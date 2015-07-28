// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ListFileGroupResult.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.http.AOSResponseResult;
import java.util.List;

public class ListFileGroupResult extends AOSResponseResult
{

    public ListFileGroupResult()
    {
    }

    public List getParts()
    {
        return parts;
    }

    public void setParts(List parts)
    {
        this.parts = parts;
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

    public void setETag(String eTag)
    {
        this.eTag = eTag;
    }

    public long getSize()
    {
        return size;
    }

    public void setSize(long size)
    {
        this.size = size;
    }

    public String toString()
    {
        return (new StringBuilder("ListFileGroupResult [parts=")).append(parts).append(", bucketName=").append(bucketName).append(", key=").append(key).append(", eTag=").append(eTag).append(", size=").append(size).append("]").toString();
    }

    private List parts;
    private String bucketName;
    private String key;
    private String eTag;
    private long size;
}
