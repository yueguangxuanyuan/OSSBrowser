// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenericBucketRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;

public class GenericBucketRequest extends AOSWebServiceRequest
{

    public GenericBucketRequest(String bucket)
    {
        this.bucket = bucket;
    }

    public String getBucket()
    {
        return bucket;
    }

    private final String bucket;
}
