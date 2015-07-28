// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileMenuListener.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.oss.ossbrowser.utils.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileMenuListener
    implements ActionListener
{

    public FileMenuListener(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    public void actionPerformed(ActionEvent e)
    {
        String bucket = Utils.getCurrentBucketName(resourceManager);
        String key = Utils.getCurrentObject(resourceManager);
        String prefix = Utils.getCurrentPrefix(resourceManager);
        if(e.getActionCommand().equals("\u4E0A\u4F20\u6587\u4EF6"))
            FileAccess.uploadFiles(bucket, prefix, resourceManager);
        else
        if(e.getActionCommand().equals("\u4E0A\u4F20\u6587\u4EF6\u5939"))
            FileAccess.uploadFolder(bucket, prefix, resourceManager);
        else
        if(e.getActionCommand().equals("\u5220\u9664"))
            FileAccess.delete(bucket, key, resourceManager);
        else
        if(e.getActionCommand().equals("\u4E0B\u8F7D"))
            FileAccess.download(bucket, key, resourceManager);
        else
        if(e.getActionCommand().equals("\u65B0\u5EFA\u6587\u4EF6\u5939"))
            FileAccess.createFolder(bucket, prefix, resourceManager);
        else
        if(e.getActionCommand().equals("\u5237\u65B0"))
            FileAccess.refresh(bucket, prefix, resourceManager, false);
    }

    private ResourceManager resourceManager;
}
