// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FilePart.java

package com.aliyun.aos.services.oss.model;


public class FilePart
    implements Comparable
{

    public FilePart()
    {
    }

    public int getPartID()
    {
        return partID;
    }

    public void setPartID(int partID)
    {
        this.partID = partID;
    }

    public String getPartName()
    {
        return partName;
    }

    public void setPartName(String partName)
    {
        this.partName = partName;
    }

    public String getETag()
    {
        return eTag;
    }

    public void setETag(String eTag)
    {
        this.eTag = eTag;
    }

    public int compareTo(FilePart o)
    {
        return partID - o.partID;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        FilePart other = (FilePart)obj;
        return partID == other.partID;
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + partID;
        return result;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((FilePart)obj);
    }

    private int partID;
    private String partName;
    private String eTag;
}
