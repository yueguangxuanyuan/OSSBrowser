// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteObjectsRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;
import java.util.ArrayList;
import java.util.List;

public class DeleteObjectsRequest extends AOSWebServiceRequest
{

    public DeleteObjectsRequest(String bucketName)
    {
        keys = new ArrayList();
        setBucketName(bucketName);
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public DeleteObjectsRequest withBucketName(String bucketName)
    {
        setBucketName(bucketName);
        return this;
    }

    public void setQuiet(boolean quiet)
    {
        this.quiet = quiet;
    }

    public boolean getQuiet()
    {
        return quiet;
    }

    public DeleteObjectsRequest withQuiet(boolean quiet)
    {
        setQuiet(quiet);
        return this;
    }

    public void setKeys(List keys)
    {
        this.keys.clear();
        this.keys.addAll(keys);
    }

    public DeleteObjectsRequest withKeys(List keys)
    {
        setKeys(keys);
        return this;
    }

    public List getKeys()
    {
        return keys;
    }

    public transient DeleteObjectsRequest withKeys(String keys[])
    {
        List keyList = new ArrayList();
        String as[];
        int j = (as = keys).length;
        for(int i = 0; i < j; i++)
        {
            String s = as[i];
            keyList.add(s);
        }

        setKeys(keyList);
        return this;
    }

    private String bucketName;
    private boolean quiet;
    private List keys;
}
