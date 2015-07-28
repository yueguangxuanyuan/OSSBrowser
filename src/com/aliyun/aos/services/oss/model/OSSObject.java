// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OSSObject.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.http.AOSResponseResult;
import java.io.InputStream;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            ObjectMetadata

public class OSSObject extends AOSResponseResult
{

    public OSSObject()
    {
        key = null;
        bucketName = null;
        metadata = new ObjectMetadata();
    }

    public ObjectMetadata getObjectMetadata()
    {
        return metadata;
    }

    public InputStream getObjectContent()
    {
        return objectContent;
    }

    public void setObjectContent(InputStream objectContent)
    {
        this.objectContent = objectContent;
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

    public String toString()
    {
        return (new StringBuilder("OSSObject [key=")).append(getKey()).append(",bucket=").append(bucketName != null ? bucketName : "<Unknown>").append("]").toString();
    }

    private String key;
    private String bucketName;
    private ObjectMetadata metadata;
    private InputStream objectContent;
}
