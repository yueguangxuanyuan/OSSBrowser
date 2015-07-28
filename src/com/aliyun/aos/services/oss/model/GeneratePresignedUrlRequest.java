// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneratePresignedUrlRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;
import com.aliyun.aos.HttpMethod;
import java.util.*;

public class GeneratePresignedUrlRequest extends AOSWebServiceRequest
{

    public GeneratePresignedUrlRequest(String bucketName, String key)
    {
        this(bucketName, key, HttpMethod.GET);
    }

    public GeneratePresignedUrlRequest(String bucketName, String key, HttpMethod method)
    {
        requestParameters = new HashMap();
        this.bucketName = bucketName;
        this.key = key;
        this.method = method;
    }

    public HttpMethod getMethod()
    {
        return method;
    }

    public void setMethod(HttpMethod method)
    {
        this.method = method;
    }

    public GeneratePresignedUrlRequest withMethod(HttpMethod method)
    {
        setMethod(method);
        return this;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public GeneratePresignedUrlRequest withBucketName(String bucketName)
    {
        setBucketName(bucketName);
        return this;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public GeneratePresignedUrlRequest withKey(String key)
    {
        setKey(key);
        return this;
    }

    public Date getExpiration()
    {
        return expiration;
    }

    public void setExpiration(Date expiration)
    {
        this.expiration = expiration;
    }

    public GeneratePresignedUrlRequest withExpiration(Date expiration)
    {
        setExpiration(expiration);
        return this;
    }

    public void addRequestParameter(String key, String value)
    {
        requestParameters.put(key, value);
    }

    public Map getRequestParameters()
    {
        return requestParameters;
    }

    private HttpMethod method;
    private String bucketName;
    private String key;
    private Date expiration;
    private Map requestParameters;
}
