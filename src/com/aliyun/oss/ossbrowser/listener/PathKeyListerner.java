// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PathKeyListerner.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.oss.ossbrowser.control.RefreshFileListTask;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.utils.Utils;
import com.aliyun.oss.ossbrowser.view.FileListTablePanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ExecutorService;

public class PathKeyListerner extends KeyAdapter
{

    public PathKeyListerner(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == 10)
        {
            String bucket = Utils.getCurrentBucketName(resourceManager);
            String prefix = resourceManager.getFileListTablePanel().getPath();
            if(bucket == null || bucket.equals(""))
                return;
            if(!prefix.endsWith("/"))
                prefix = (new StringBuilder(String.valueOf(prefix))).append("/").toString();
            RefreshFileListTask task = new RefreshFileListTask(resourceManager, bucket, prefix, true, true);
            resourceManager.getService().submit(task);
        } else
        {
            super.keyPressed(e);
        }
    }

    private ResourceManager resourceManager;
}
