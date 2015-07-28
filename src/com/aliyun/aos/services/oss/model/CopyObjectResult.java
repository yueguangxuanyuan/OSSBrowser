// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CopyObjectResult.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.http.AOSResponseResult;
import java.util.Date;

public class CopyObjectResult extends AOSResponseResult
{

    public CopyObjectResult()
    {
    }

    public String getETag()
    {
        return etag;
    }

    public void setETag(String etag)
    {
        this.etag = etag;
    }

    public Date getLastModifiedDate()
    {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate)
    {
        this.lastModifiedDate = lastModifiedDate;
    }

    private String etag;
    private Date lastModifiedDate;
}
