// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CreateBucketRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            CannedAccessControlList

public class CreateBucketRequest extends AOSWebServiceRequest
{

    public CreateBucketRequest(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public CannedAccessControlList getCannedAcl()
    {
        return cannedAcl;
    }

    public void setCannedAcl(CannedAccessControlList cannedAcl)
    {
        this.cannedAcl = cannedAcl;
    }

    public CreateBucketRequest withCannedAcl(CannedAccessControlList cannedAcl)
    {
        setCannedAcl(cannedAcl);
        return this;
    }

    private String bucketName;
    private CannedAccessControlList cannedAcl;
}
