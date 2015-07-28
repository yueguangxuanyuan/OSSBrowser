// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PostFileGroupResult.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.http.AOSResponseResult;

public class PostFileGroupResult extends AOSResponseResult
{

    public PostFileGroupResult()
    {
    }

    public String getBucket()
    {
        return bucket;
    }

    public void setBucket(String bucket)
    {
        this.bucket = bucket;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String geteTag()
    {
        return eTag;
    }

    public void seteTag(String eTag)
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

    private String bucket;
    private String eTag;
    private String key;
    private long size;
}
