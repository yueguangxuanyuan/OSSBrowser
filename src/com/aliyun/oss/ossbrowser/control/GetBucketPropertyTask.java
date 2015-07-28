// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GetBucketPropertyTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.aos.services.oss.model.Bucket;
import com.aliyun.oss.ossbrowser.model.LogLevel;
import com.aliyun.oss.ossbrowser.model.PropertyTableModel;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.utils.Utils;
import com.aliyun.oss.ossbrowser.view.*;
import java.util.Date;

public class GetBucketPropertyTask
    implements Runnable
{

    public GetBucketPropertyTask(ResourceManager resourceManager, Bucket bucket)
    {
        this(resourceManager, bucket, 3);
    }

    public GetBucketPropertyTask(ResourceManager resourceManager, Bucket bucket, int retry)
    {
        this.resourceManager = resourceManager;
        this.bucket = bucket;
        this.retry = retry;
    }

    public void run()
    {
        String log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Getting property for bucket ").append(bucket.getName()).append("...").toString();
        resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
        if(getBucketProperty())
        {
            log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Property has been recevied for bucket ").append(bucket.getName()).append("...").toString();
            resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
        } else
        {
            log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" get property for bucket ").append(bucket.getName()).append(" failed").toString();
            resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.ERROR, log);
        }
    }

    private boolean getBucketProperty()
    {
        int r = 0;
          goto _L1
_L3:
        PropertyTableModel model = resourceManager.getConsolePanel().getPropertiesPanel().getTableModel();
        int requestCount = model.requestCountInc();
        model.initBucketProperty();
        model.setBucketBaseProperties(bucket, requestCount);
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
    private final Bucket bucket;
    private int retry;
}
