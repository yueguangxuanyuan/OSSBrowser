// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QueryQuotaRequest.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.AOSWebServiceRequest;

public class QueryQuotaRequest extends AOSWebServiceRequest
{

    public QueryQuotaRequest(String bucket, String queryRequestId)
    {
        this.bucket = bucket;
        this.queryRequestId = queryRequestId;
    }

    public String getBucket()
    {
        return bucket;
    }

    public void setBucket(String bucket)
    {
        this.bucket = bucket;
    }

    public String getQueryRequestId()
    {
        return queryRequestId;
    }

    public void setQueryRequestId(String queryRequestId)
    {
        this.queryRequestId = queryRequestId;
    }

    private String bucket;
    private String queryRequestId;
}
