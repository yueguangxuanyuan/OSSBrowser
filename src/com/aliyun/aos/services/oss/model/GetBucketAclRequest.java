// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GetBucketAclRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;

public class GetBucketAclRequest extends AOSWebServiceRequest
{

    public GetBucketAclRequest(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    private String bucketName;
}
