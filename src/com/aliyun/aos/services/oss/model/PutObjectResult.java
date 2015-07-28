// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PutObjectResult.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.http.AOSResponseResult;

public class PutObjectResult extends AOSResponseResult
{

    public PutObjectResult()
    {
    }

    public String getETag()
    {
        return eTag;
    }

    public void setETag(String eTag)
    {
        this.eTag = eTag;
    }

    private String eTag;
}
