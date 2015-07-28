// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ListBucketResult.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.http.AOSResponseResult;
import java.util.List;

public class ListBucketResult extends AOSResponseResult
{

    public ListBucketResult()
    {
    }

    public List getBuckets()
    {
        return buckets;
    }

    public void setBuckets(List buckets)
    {
        this.buckets = buckets;
    }

    private List buckets;
}
