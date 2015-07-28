// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FilePartSummary.java

package com.aliyun.aos.services.oss.model;


public class FilePartSummary
{

    public FilePartSummary()
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

    public String getPartName()
    {
        return partName;
    }

    public void setPartName(String partName)
    {
        this.partName = partName;
    }

    public long getPartSize()
    {
        return partSize;
    }

    public void setPartSize(long partSize)
    {
        this.partSize = partSize;
    }

    public String getETag()
    {
        return eTag;
    }

    public void setETag(String eTag)
    {
        this.eTag = eTag;
    }

    public String toString()
    {
        return (new StringBuilder("FilePartSummary [partNumber=")).append(partNumber).append(", partName=").append(partName).append(", partSize=").append(partSize).append(", eTag=").append(eTag).append("]").toString();
    }

    private int partNumber;
    private String partName;
    private long partSize;
    private String eTag;
}
