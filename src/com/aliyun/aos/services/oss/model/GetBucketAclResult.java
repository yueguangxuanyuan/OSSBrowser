// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GetBucketAclResult.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.http.AOSResponseResult;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            CannedAccessControlList

public class GetBucketAclResult extends AOSResponseResult
{

    public GetBucketAclResult()
    {
    }

    public CannedAccessControlList getCannedAccessControlList()
    {
        return cannedAccessControlList;
    }

    public void setCannedAccessControlList(CannedAccessControlList cannedAccessControlList)
    {
        this.cannedAccessControlList = cannedAccessControlList;
    }

    private CannedAccessControlList cannedAccessControlList;
}
