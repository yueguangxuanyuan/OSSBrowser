// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BucketIconListItem.java

package com.aliyun.oss.ossbrowser.model;

import com.aliyun.aos.services.oss.model.Bucket;
import javax.swing.Icon;

public class BucketIconListItem
{

    public BucketIconListItem(Icon icon, Bucket bucket)
    {
        this.icon = icon;
        this.bucket = bucket;
    }

    public Icon getIcon()
    {
        return icon;
    }

    public void setIcon(Icon icon)
    {
        this.icon = icon;
    }

    public Bucket getBucket()
    {
        return bucket;
    }

    public void setBucket(Bucket bucket)
    {
        this.bucket = bucket;
    }

    private Icon icon;
    private Bucket bucket;
}
