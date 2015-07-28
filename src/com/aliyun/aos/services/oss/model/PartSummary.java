// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PartSummary.java

package com.aliyun.aos.services.oss.model;

import java.util.Date;

public class PartSummary
{

    public PartSummary()
    {
    }

    public int getPartNumber()
    {
        return partNumber;
    }

    public void setPartNumber(int partNumber)
    {
        this.partNumber = partNumber;
    }

    public Date getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(Date lastModified)
    {
        this.lastModified = lastModified;
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

    private int partNumber;
    private Date lastModified;
    private String eTag;
    private long size;
}
