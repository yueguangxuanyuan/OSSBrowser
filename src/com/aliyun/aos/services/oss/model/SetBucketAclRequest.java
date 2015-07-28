// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SetBucketAclRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            CannedAccessControlList

public class SetBucketAclRequest extends AOSWebServiceRequest
{

    public SetBucketAclRequest(String bucketName, CannedAccessControlList acl)
    {
        this.bucketName = bucketName;
        cannedAcl = acl;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public CannedAccessControlList getCannedAcl()
    {
        return cannedAcl;
    }

    private String bucketName;
    private CannedAccessControlList cannedAcl;
}
