// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PostFileGroupRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;
import java.util.Set;
import java.util.TreeSet;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            ObjectMetadata, FilePart

public class PostFileGroupRequest extends AOSWebServiceRequest
{

    public PostFileGroupRequest()
    {
        parts = new TreeSet();
        metadata = new ObjectMetadata();
    }

    public Set getParts()
    {
        return parts;
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

    public ObjectMetadata getMetadata()
    {
        return metadata;
    }

    public void setMetadata(ObjectMetadata metadata)
    {
        this.metadata = metadata;
    }

    public void addFilePart(FilePart p)
    {
        parts.add(p);
    }

    private Set parts;
    private String bucketName;
    private String key;
    private ObjectMetadata metadata;
}
