// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PartListing.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.http.AOSResponseResult;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            Owner

public class PartListing extends AOSResponseResult
{

    public PartListing()
    {
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getUploadId()
    {
        return uploadId;
    }

    public void setUploadId(String uploadId)
    {
        this.uploadId = uploadId;
    }

    public Owner getOwner()
    {
        return owner;
    }

    public void setOwner(Owner owner)
    {
        this.owner = owner;
    }

    public Owner getInitiator()
    {
        return initiator;
    }

    public void setInitiator(Owner initiator)
    {
        this.initiator = initiator;
    }

    public String getStorageClass()
    {
        return storageClass;
    }

    public void setStorageClass(String storageClass)
    {
        this.storageClass = storageClass;
    }

    public Integer getPartNumberMarker()
    {
        return partNumberMarker;
    }

    public void setPartNumberMarker(int partNumberMarker)
    {
        this.partNumberMarker = Integer.valueOf(partNumberMarker);
    }

    public Integer getNextPartNumberMarker()
    {
        return nextPartNumberMarker;
    }

    public void setNextPartNumberMarker(int nextPartNumberMarker)
    {
        this.nextPartNumberMarker = Integer.valueOf(nextPartNumberMarker);
    }

    public Integer getMaxParts()
    {
        return maxParts;
    }

    public void setMaxParts(int maxParts)
    {
        this.maxParts = Integer.valueOf(maxParts);
    }

    public boolean isTruncated()
    {
        return isTruncated;
    }

    public void setTruncated(boolean isTruncated)
    {
        this.isTruncated = isTruncated;
    }

    public List getParts()
    {
        if(parts == null)
            parts = new ArrayList();
        return parts;
    }

    public void setParts(List parts)
    {
        this.parts = parts;
    }

    private String bucketName;
    private String key;
    private String uploadId;
    private Integer maxParts;
    private Integer partNumberMarker;
    private Owner owner;
    private Owner initiator;
    private String storageClass;
    private boolean isTruncated;
    private Integer nextPartNumberMarker;
    private List parts;
}
