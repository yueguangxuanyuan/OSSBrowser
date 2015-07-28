// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileMeta.java

package com.aliyun.oss.ossbrowser.model;

import java.io.Serializable;

public class FileMeta
    implements Serializable
{

    public FileMeta(Long lastModify, String eTag, boolean ignoreUpload)
    {
        this.lastModify = lastModify;
        this.eTag = eTag;
        this.ignoreUpload = ignoreUpload;
    }

    public Long getLastModify()
    {
        return lastModify;
    }

    public void setLastModify(Long lastModify)
    {
        this.lastModify = lastModify;
    }

    public String getETag()
    {
        return eTag;
    }

    public void setETag(String eTag)
    {
        this.eTag = eTag;
    }

    public boolean isIgnoreUpload()
    {
        return ignoreUpload;
    }

    public void setIgnoreUpload(boolean ignoreUpload)
    {
        this.ignoreUpload = ignoreUpload;
    }

    private Long lastModify;
    private String eTag;
    private boolean ignoreUpload;
}
