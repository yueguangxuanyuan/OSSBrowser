// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MultipartUploadListing.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.http.AOSResponseResult;
import java.util.ArrayList;
import java.util.List;

public class MultipartUploadListing extends AOSResponseResult
{

    public MultipartUploadListing()
    {
        commonPrefixes = new ArrayList();
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public String getKeyMarker()
    {
        return keyMarker;
    }

    public void setKeyMarker(String keyMarker)
    {
        this.keyMarker = keyMarker;
    }

    public String getUploadIdMarker()
    {
        return uploadIdMarker;
    }

    public void setUploadIdMarker(String uploadIdMarker)
    {
        this.uploadIdMarker = uploadIdMarker;
    }

    public String getNextKeyMarker()
    {
        return nextKeyMarker;
    }

    public void setNextKeyMarker(String nextKeyMarker)
    {
        this.nextKeyMarker = nextKeyMarker;
    }

    public String getNextUploadIdMarker()
    {
        return nextUploadIdMarker;
    }

    public void setNextUploadIdMarker(String nextUploadIdMarker)
    {
        this.nextUploadIdMarker = nextUploadIdMarker;
    }

    public int getMaxUploads()
    {
        return maxUploads;
    }

    public void setMaxUploads(int maxUploads)
    {
        this.maxUploads = maxUploads;
    }

    public boolean isTruncated()
    {
        return isTruncated;
    }

    public void setTruncated(boolean isTruncated)
    {
        this.isTruncated = isTruncated;
    }

    public List getMultipartUploads()
    {
        if(multipartUploads == null)
            multipartUploads = new ArrayList();
        return multipartUploads;
    }

    public void setMultipartUploads(List multipartUploads)
    {
        this.multipartUploads = multipartUploads;
    }

    public List getCommonPrefixes()
    {
        return commonPrefixes;
    }

    public void setCommonPrefixes(List commonPrefixes)
    {
        this.commonPrefixes = commonPrefixes;
    }

    public String getDelimiter()
    {
        return delimiter;
    }

    public void setDelimiter(String delimiter)
    {
        this.delimiter = delimiter;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    private String bucketName;
    private String keyMarker;
    private String delimiter;
    private String prefix;
    private String uploadIdMarker;
    private int maxUploads;
    private boolean isTruncated;
    private String nextKeyMarker;
    private String nextUploadIdMarker;
    private List multipartUploads;
    private List commonPrefixes;
}
