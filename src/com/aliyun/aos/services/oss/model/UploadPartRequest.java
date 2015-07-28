// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UploadPartRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;
import java.io.File;
import java.io.InputStream;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            ProgressListener

public class UploadPartRequest extends AOSWebServiceRequest
{

    public UploadPartRequest()
    {
    }

    public void setInputStream(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    public InputStream getInputStream()
    {
        return inputStream;
    }

    public UploadPartRequest withInputStream(InputStream inputStream)
    {
        setInputStream(inputStream);
        return this;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public UploadPartRequest withBucketName(String bucketName)
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

    public UploadPartRequest withKey(String key)
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

    public UploadPartRequest withUploadId(String uploadId)
    {
        this.uploadId = uploadId;
        return this;
    }

    public int getPartNumber()
    {
        return partNumber;
    }

    public void setPartNumber(int partNumber)
    {
        this.partNumber = partNumber;
    }

    public UploadPartRequest withPartNumber(int partNumber)
    {
        this.partNumber = partNumber;
        return this;
    }

    public long getPartSize()
    {
        return partSize;
    }

    public void setPartSize(long partSize)
    {
        this.partSize = partSize;
    }

    public UploadPartRequest withPartSize(long partSize)
    {
        this.partSize = partSize;
        return this;
    }

    public String getMd5Digest()
    {
        return md5Digest;
    }

    public void setMd5Digest(String md5Digest)
    {
        this.md5Digest = md5Digest;
    }

    public UploadPartRequest withMD5Digest(String md5Digest)
    {
        this.md5Digest = md5Digest;
        return this;
    }

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    public UploadPartRequest withFile(File file)
    {
        setFile(file);
        return this;
    }

    public long getFileOffset()
    {
        return fileOffset;
    }

    public void setFileOffset(long fileOffset)
    {
        this.fileOffset = fileOffset;
    }

    public UploadPartRequest withFileOffset(long fileOffset)
    {
        setFileOffset(fileOffset);
        return this;
    }

    public void setProgressListener(ProgressListener progressListener)
    {
        this.progressListener = progressListener;
    }

    public ProgressListener getProgressListener()
    {
        return progressListener;
    }

    public UploadPartRequest withProgressListener(ProgressListener progressListener)
    {
        setProgressListener(progressListener);
        return this;
    }

    public boolean isLastPart()
    {
        return isLastPart;
    }

    public void setLastPart(boolean isLastPart)
    {
        this.isLastPart = isLastPart;
    }

    public UploadPartRequest withLastPart(boolean isLastPart)
    {
        setLastPart(isLastPart);
        return this;
    }

    private String bucketName;
    private String key;
    private String uploadId;
    private int partNumber;
    private long partSize;
    private String md5Digest;
    private InputStream inputStream;
    private File file;
    private long fileOffset;
    private ProgressListener progressListener;
    private boolean isLastPart;
}
