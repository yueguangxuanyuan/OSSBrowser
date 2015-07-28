// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OSSObjectSummary.java

package com.aliyun.aos.services.oss.model;

import java.util.Date;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            Owner

public class OSSObjectSummary
{

    public OSSObjectSummary()
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

    public String getETag()
    {
        return eTag;
    }

    public void setETag(String eTag)
    {
        this.eTag = eTag;
    }

    public long getSize()
    {
        return size;
    }

    public void setSize(long size)
    {
        this.size = size;
    }

    public Date getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(Date lastModified)
    {
        this.lastModified = lastModified;
    }

    public Owner getOwner()
    {
        return owner;
    }

    public void setOwner(Owner owner)
    {
        this.owner = owner;
    }

    public String getStorageClass()
    {
        return storageClass;
    }

    public void setStorageClass(String storageClass)
    {
        this.storageClass = storageClass;
    }

    public String toString()
    {
        return (new StringBuilder("BucketName: ")).append(bucketName).append(", Key: ").append(key).append(", ETag: ").append(eTag).append(", Size: ").append(size).append(", Type: ").append(type).append(", LastModified: ").append(lastModified).append(", StorageClass: ").append(storageClass).append(", Owner: ").append(owner).toString();
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + (bucketName != null ? bucketName.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        return result;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        OSSObjectSummary other = (OSSObjectSummary)obj;
        if(bucketName == null)
        {
            if(other.bucketName != null)
                return false;
        } else
        if(!bucketName.equals(other.bucketName))
            return false;
        if(key == null)
        {
            if(other.key != null)
                return false;
        } else
        if(!key.equals(other.key))
            return false;
        return true;
    }

    protected String bucketName;
    protected String key;
    protected String eTag;
    protected long size;
    protected Date lastModified;
    protected String storageClass;
    protected Owner owner;
    private String type;
}
