// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompleteMultipartUploadRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;
import java.util.*;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            PartETag, UploadPartResult

public class CompleteMultipartUploadRequest extends AOSWebServiceRequest
{

    public CompleteMultipartUploadRequest(String bucketName, String key, String uploadId, List partETags)
    {
        this.partETags = new ArrayList();
        this.bucketName = bucketName;
        this.key = key;
        this.uploadId = uploadId;
        this.partETags = partETags;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public CompleteMultipartUploadRequest withBucketName(String bucketName)
    {
        this.bucketName = bucketName;
        return this;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public CompleteMultipartUploadRequest withKey(String key)
    {
        this.key = key;
        return this;
    }

    public String getUploadId()
    {
        return uploadId;
    }

    public void setUploadId(String uploadId)
    {
        this.uploadId = uploadId;
    }

    public CompleteMultipartUploadRequest withUploadId(String uploadId)
    {
        this.uploadId = uploadId;
        return this;
    }

    public List getPartETags()
    {
        return partETags;
    }

    public void setPartETags(List partETags)
    {
        this.partETags = partETags;
    }

    public CompleteMultipartUploadRequest withPartETags(List partETags)
    {
        setPartETags(partETags);
        return this;
    }

    public transient CompleteMultipartUploadRequest withPartETags(UploadPartResult uploadPartResults[])
    {
        UploadPartResult auploadpartresult[];
        int j = (auploadpartresult = uploadPartResults).length;
        for(int i = 0; i < j; i++)
        {
            UploadPartResult result = auploadpartresult[i];
            partETags.add(new PartETag(result.getPartNumber(), result.getETag()));
        }

        return this;
    }

    public CompleteMultipartUploadRequest withPartETags(Collection uploadPartResultsCollection)
    {
        UploadPartResult result;
        for(Iterator iterator = uploadPartResultsCollection.iterator(); iterator.hasNext(); partETags.add(new PartETag(result.getPartNumber(), result.getETag())))
            result = (UploadPartResult)iterator.next();

        return this;
    }

    private String bucketName;
    private String key;
    private String uploadId;
    private List partETags;
}
