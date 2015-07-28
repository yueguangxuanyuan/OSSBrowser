// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GetObjectRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;
import java.util.*;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            ResponseHeaderOverrides

public class GetObjectRequest extends AOSWebServiceRequest
{

    public GetObjectRequest(String bucketName, String key)
    {
        matchingETagConstraints = new ArrayList();
        nonmatchingEtagConstraints = new ArrayList();
        this.bucketName = bucketName;
        this.key = key;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public GetObjectRequest withBucketName(String bucketName)
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

    public GetObjectRequest withKey(String key)
    {
        setKey(key);
        return this;
    }

    public long[] getRange()
    {
        return range;
    }

    public void setRange(long start, long end)
    {
        range = (new long[] {
            start, end
        });
    }

    public GetObjectRequest withRange(long start, long end)
    {
        setRange(start, end);
        return this;
    }

    public List getMatchingETagConstraints()
    {
        return matchingETagConstraints;
    }

    public void setMatchingETagConstraints(List eTagList)
    {
        matchingETagConstraints = eTagList;
    }

    public GetObjectRequest withMatchingETagConstraint(String eTag)
    {
        matchingETagConstraints.add(eTag);
        return this;
    }

    public List getNonmatchingETagConstraints()
    {
        return nonmatchingEtagConstraints;
    }

    public void setNonmatchingETagConstraints(List eTagList)
    {
        nonmatchingEtagConstraints = eTagList;
    }

    public GetObjectRequest withNonmatchingETagConstraint(String eTag)
    {
        nonmatchingEtagConstraints.add(eTag);
        return this;
    }

    public Date getUnmodifiedSinceConstraint()
    {
        return unmodifiedSinceConstraint;
    }

    public void setUnmodifiedSinceConstraint(Date date)
    {
        unmodifiedSinceConstraint = date;
    }

    public GetObjectRequest withUnmodifiedSinceConstraint(Date date)
    {
        setUnmodifiedSinceConstraint(date);
        return this;
    }

    public Date getModifiedSinceConstraint()
    {
        return modifiedSinceConstraint;
    }

    public void setModifiedSinceConstraint(Date date)
    {
        modifiedSinceConstraint = date;
    }

    public GetObjectRequest withModifiedSinceConstraint(Date date)
    {
        setModifiedSinceConstraint(date);
        return this;
    }

    public ResponseHeaderOverrides getResponseHeaders()
    {
        return responseHeaders;
    }

    public void setResponseHeaders(ResponseHeaderOverrides responseHeaders)
    {
        this.responseHeaders = responseHeaders;
    }

    private String bucketName;
    private String key;
    private long range[];
    private List matchingETagConstraints;
    private List nonmatchingEtagConstraints;
    private Date unmodifiedSinceConstraint;
    private Date modifiedSinceConstraint;
    private ResponseHeaderOverrides responseHeaders;
}
