// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OSSNoSuchBucketException.java

package com.aliyun.aos.services.oss.model;


// Referenced classes of package com.aliyun.aos.services.oss.model:
//            AOSOSSException

public class OSSNoSuchBucketException extends AOSOSSException
{

    public OSSNoSuchBucketException(String bucket, String message)
    {
        super(message);
        bucketName = bucket;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public String toString()
    {
        return (new StringBuilder(String.valueOf(super.toString()))).append("\n OSSNoSuchBucketException [bucketName=").append(bucketName).append("]").toString();
    }

    private static final long serialVersionUID = 1L;
    private String bucketName;
}
