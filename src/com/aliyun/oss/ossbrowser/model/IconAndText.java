// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IconAndText.java

package com.aliyun.oss.ossbrowser.model;

import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import javax.swing.Icon;

// Referenced classes of package com.aliyun.oss.ossbrowser.model:
//            IconType

public class IconAndText
{

    public IconAndText(Icon icon, String text, String bucket, String object, String path, int status, long size, ResourceManager resourceManager)
    {
        this.icon = icon;
        this.text = text;
        type = IconType.ANIMATED;
        this.bucket = bucket;
        this.object = object;
        this.path = path;
        this.status = status;
        this.resourceManager = resourceManager;
        this.size = size;
    }

    public Icon getIcon()
    {
        return icon;
    }

    public void setIcon(Icon icon)
    {
        this.icon = icon;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public IconType getType()
    {
        return type;
    }

    public void setType(IconType type)
    {
        this.type = type;
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

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public ResourceManager getResourceManager()
    {
        return resourceManager;
    }

    public void setResourceManager(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    public long getSize()
    {
        return size;
    }

    public void setSize(long size)
    {
        this.size = size;
    }

    private ResourceManager resourceManager;
    private Icon icon;
    private String text;
    private IconType type;
    private String bucket;
    private String object;
    private String path;
    private int status;
    private long size;
}
