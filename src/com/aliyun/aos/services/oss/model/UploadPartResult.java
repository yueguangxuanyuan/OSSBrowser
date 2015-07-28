// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UploadPartResult.java

package com.aliyun.aos.services.oss.model;


// Referenced classes of package com.aliyun.aos.services.oss.model:
//            PartETag

public class UploadPartResult
{

    public UploadPartResult()
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

    public String getETag()
    {
        return eTag;
    }

    public void setETag(String eTag)
    {
        this.eTag = eTag;
    }

    public PartETag getPartETag()
    {
        return new PartETag(partNumber, eTag);
    }

    private int partNumber;
    private String eTag;
}
