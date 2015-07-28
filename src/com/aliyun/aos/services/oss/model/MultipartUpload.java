// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MultipartUpload.java

package com.aliyun.aos.services.oss.model;

import java.util.Date;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            Owner

public class MultipartUpload
{

    public MultipartUpload()
    {
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

    public Date getInitiated()
    {
        return initiated;
    }

    public void setInitiated(Date initiated)
    {
        this.initiated = initiated;
    }

    public String toString()
    {
        return key;
    }

    private String key;
    private String uploadId;
    private Owner owner;
    private Owner initiator;
    private String storageClass;
    private Date initiated;
}
