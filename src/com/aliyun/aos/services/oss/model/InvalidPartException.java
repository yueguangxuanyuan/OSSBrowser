// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InvalidPartException.java

package com.aliyun.aos.services.oss.model;


// Referenced classes of package com.aliyun.aos.services.oss.model:
//            AOSOSSException

public class InvalidPartException extends AOSOSSException
{

    public String getEtag()
    {
        return etag;
    }

    public String getUploadId()
    {
        return uploadId;
    }

    public int getPartNumber()
    {
        return partNumber;
    }

    public InvalidPartException(String etag, String uploadId, int partNumber, String message)
    {
        super(message);
        this.etag = etag;
        this.uploadId = uploadId;
        this.partNumber = partNumber;
    }

    private static final long serialVersionUID = 0xbb1ab1eb7e4aaed2L;
    private String etag;
    private String uploadId;
    private int partNumber;
}
