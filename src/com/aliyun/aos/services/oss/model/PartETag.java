// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PartETag.java

package com.aliyun.aos.services.oss.model;

import java.io.Serializable;

public class PartETag
    implements Serializable
{

    public PartETag(int partNumber, String eTag)
    {
        this.partNumber = partNumber;
        this.eTag = eTag;
    }

    public int getPartNumber()
    {
        return partNumber;
    }

    public void setPartNumber(int partNumber)
    {
        this.partNumber = partNumber;
    }

    public PartETag withPartNumber(int partNumber)
    {
        this.partNumber = partNumber;
        return this;
    }

    public String getETag()
    {
        return eTag;
    }

    public void setETag(String eTag)
    {
        this.eTag = eTag;
    }

    public PartETag withETag(String eTag)
    {
        this.eTag = eTag;
        return this;
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + (eTag != null ? eTag.hashCode() : 0);
        result = 31 * result + partNumber;
        return result;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        PartETag other = (PartETag)obj;
        if(eTag == null)
        {
            if(other.eTag != null)
                return false;
        } else
        if(!eTag.equals(other.eTag))
            return false;
        return partNumber == other.partNumber;
    }

    private int partNumber;
    private String eTag;
}
