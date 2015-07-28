// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CopyObjectRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;
import java.util.*;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            ObjectMetadata

public class CopyObjectRequest extends AOSWebServiceRequest
{

    public CopyObjectRequest(String sourceBucketName, String sourceKey, String destinationBucketName, String destinationKey)
    {
        matchingETagConstraints = new ArrayList();
        nonmatchingEtagConstraints = new ArrayList();
        this.sourceBucketName = sourceBucketName;
        this.sourceKey = sourceKey;
        this.destinationBucketName = destinationBucketName;
        this.destinationKey = destinationKey;
    }

    public String getSourceBucketName()
    {
        return sourceBucketName;
    }

    public void setSourceBucketName(String sourceBucketName)
    {
        this.sourceBucketName = sourceBucketName;
    }

    public CopyObjectRequest withSourceBucketName(String sourceBucketName)
    {
        setSourceBucketName(sourceBucketName);
        return this;
    }

    public String getSourceKey()
    {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey)
    {
        this.sourceKey = sourceKey;
    }

    public CopyObjectRequest withSourceKey(String sourceKey)
    {
        setSourceKey(sourceKey);
        return this;
    }

    public String getDestinationBucketName()
    {
        return destinationBucketName;
    }

    public void setDestinationBucketName(String destinationBucketName)
    {
        this.destinationBucketName = destinationBucketName;
    }

    public CopyObjectRequest withDestinationBucketName(String destinationBucketName)
    {
        setDestinationBucketName(destinationBucketName);
        return this;
    }

    public String getDestinationKey()
    {
        return destinationKey;
    }

    public void setDestinationKey(String destinationKey)
    {
        this.destinationKey = destinationKey;
    }

    public CopyObjectRequest withDestinationKey(String destinationKey)
    {
        setDestinationKey(destinationKey);
        return this;
    }

    public ObjectMetadata getNewObjectMetadata()
    {
        return newObjectMetadata;
    }

    public void setNewObjectMetadata(ObjectMetadata newObjectMetadata)
    {
        this.newObjectMetadata = newObjectMetadata;
    }

    public CopyObjectRequest withNewObjectMetadata(ObjectMetadata newObjectMetadata)
    {
        setNewObjectMetadata(newObjectMetadata);
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

    public CopyObjectRequest withMatchingETagConstraint(String eTag)
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

    public CopyObjectRequest withNonmatchingETagConstraint(String eTag)
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

    public CopyObjectRequest withUnmodifiedSinceConstraint(Date date)
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

    public CopyObjectRequest withModifiedSinceConstraint(Date date)
    {
        setModifiedSinceConstraint(date);
        return this;
    }

    private String sourceBucketName;
    private String sourceKey;
    private String destinationBucketName;
    private String destinationKey;
    private ObjectMetadata newObjectMetadata;
    private List matchingETagConstraints;
    private List nonmatchingEtagConstraints;
    private Date unmodifiedSinceConstraint;
    private Date modifiedSinceConstraint;
}
