// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PutObjectRequest.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.AOSWebServiceRequest;
import java.io.File;
import java.io.InputStream;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            ObjectMetadata, ProgressListener

public class PutObjectRequest extends AOSWebServiceRequest
{

    public PutObjectRequest(String bucketName, String key, File file)
    {
        this.bucketName = bucketName;
        this.key = key;
        this.file = file;
    }

    public PutObjectRequest(String bucketName, String key, InputStream input, ObjectMetadata metadata)
    {
        this.bucketName = bucketName;
        this.key = key;
        inputStream = input;
        this.metadata = metadata;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public PutObjectRequest withBucketName(String bucketName)
    {
        setBucketName(bucketName);
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

    public PutObjectRequest withKey(String key)
    {
        setKey(key);
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

    public PutObjectRequest withFile(File file)
    {
        setFile(file);
        return this;
    }

    public ObjectMetadata getMetadata()
    {
        return metadata;
    }

    public void setMetadata(ObjectMetadata metadata)
    {
        this.metadata = metadata;
    }

    public PutObjectRequest withMetadata(ObjectMetadata metadata)
    {
        setMetadata(metadata);
        return this;
    }

    public InputStream getInputStream()
    {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    public PutObjectRequest withInputStream(InputStream inputStream)
    {
        setInputStream(inputStream);
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

    public PutObjectRequest withProgressListener(ProgressListener progressListener)
    {
        setProgressListener(progressListener);
        return this;
    }

    private String bucketName;
    private String key;
    private File file;
    private InputStream inputStream;
    private ObjectMetadata metadata;
    private ProgressListener progressListener;
}
