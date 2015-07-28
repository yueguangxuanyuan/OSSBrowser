// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GetObjectHeaderTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.oss.ossbrowser.model.HeaderTableModel;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.view.ConsolePanel;
import com.aliyun.oss.ossbrowser.view.HttpHeadersPanel;

public class GetObjectHeaderTask
    implements Runnable
{

    public GetObjectHeaderTask(ResourceManager resourceManager, String bucketName, String key)
    {
        this(resourceManager, bucketName, key, 3);
    }

    public GetObjectHeaderTask(ResourceManager resourceManager, String bucketName, String key, int retry)
    {
        this.resourceManager = resourceManager;
        this.bucketName = bucketName;
        this.key = key;
        this.retry = retry;
    }

    public void run()
    {
        getObjectHeader();
    }

    private boolean getObjectHeader()
    {
        HeaderTableModel model;
        int count;
        int r;
        model = resourceManager.getConsolePanel().getHttpHeadPanel().getTableModel();
        count = model.requestCountInc();
        r = 0;
          goto _L1
_L3:
        model.initProperty();
        com.aliyun.aos.services.oss.model.ObjectMetadata meta = resourceManager.getOssClient().getObjectMetadata(bucketName, key);
        model.setObjectProperty(meta, count);
        return true;
        Exception exception;
        exception;
        r++;
_L1:
        if(r < retry) goto _L3; else goto _L2
_L2:
        return false;
    }

    private ResourceManager resourceManager;
    private String bucketName;
    private String key;
    private int retry;
}
