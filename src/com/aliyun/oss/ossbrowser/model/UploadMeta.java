// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UploadMeta.java

package com.aliyun.oss.ossbrowser.model;

import java.io.Serializable;
import java.util.ArrayList;

public class UploadMeta
    implements Serializable
{

    public UploadMeta()
    {
    }

    public void Copy(UploadMeta meta)
    {
        uploadId = meta.getUploadId();
        path = meta.getPath();
        length = meta.getLength();
        lastModify = meta.getLastModify();
        blockSize = meta.getBlockSize();
        currentPart = meta.getCurrentPart();
        bucket = meta.getBucket();
        object = meta.getObject();
        etags = meta.getEtags();
    }

    public String getUploadId()
    {
        return uploadId;
    }

    public void setUploadId(String uploadId)
    {
        this.uploadId = uploadId;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public long getLength()
    {
        return length;
    }

    public void setLength(long length)
    {
        this.length = length;
    }

    public long getLastModify()
    {
        return lastModify;
    }

    public void setLastModify(long lastModify)
    {
        this.lastModify = lastModify;
    }

    public int getCurrentPart()
    {
        return currentPart;
    }

    public void setCurrentPart(int currentPart)
    {
        this.currentPart = currentPart;
    }

    public long getBlockSize()
    {
        return blockSize;
    }

    public void setBlockSize(long blockSize)
    {
        this.blockSize = blockSize;
    }

    public String getBucket()
    {
        return bucket;
    }

    public void setBucket(String bucket)
    {
        this.bucket = bucket;
    }

    public String getObject()
    {
        return object;
    }

    public void setObject(String object)
    {
        this.object = object;
    }

    public ArrayList getEtags()
    {
        return etags;
    }

    public void setEtags(ArrayList etags)
    {
        this.etags = etags;
    }

    String uploadId;
    String path;
    long length;
    long lastModify;
    long blockSize;
    int currentPart;
    String bucket;
    String object;
    ArrayList etags;
}
