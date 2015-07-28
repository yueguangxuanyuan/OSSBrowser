// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteFileTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.aos.services.oss.model.OSSObjectSummary;
import com.aliyun.aos.services.oss.model.ObjectListing;
import com.aliyun.oss.ossbrowser.model.LogLevel;
import com.aliyun.oss.ossbrowser.utils.*;
import com.aliyun.oss.ossbrowser.view.ConsolePanel;
import com.aliyun.oss.ossbrowser.view.LogPanel;
import java.util.*;

// Referenced classes of package com.aliyun.oss.ossbrowser.control:
//            RefreshFileListTask

public class DeleteFileTask
    implements Runnable
{

    public DeleteFileTask(ResourceManager resourceManager, String bucketName, String key)
    {
        this(resourceManager, bucketName, key, true);
    }

    public DeleteFileTask(ResourceManager resourceManager, String bucketName, String key, boolean fresh)
    {
        this(resourceManager, bucketName, key, fresh, 3);
    }

    public DeleteFileTask(ResourceManager resourceManager, String bucketName, String key, boolean fresh, int retry)
    {
        this.resourceManager = resourceManager;
        this.bucketName = bucketName;
        this.key = key;
        this.fresh = fresh;
        this.retry = retry;
    }

    public Boolean call()
    {
        boolean result = true;
        if(FileAccess.isFolder(key))
        {
            ObjectListing objects = resourceManager.getOssClient().listAllObjects(bucketName, key, null);
            for(Iterator iterator = objects.getObjectSummaries().iterator(); iterator.hasNext();)
            {
                OSSObjectSummary object = (OSSObjectSummary)iterator.next();
                if(!deleteFile(object.getKey()))
                    result = false;
            }

            if(fresh)
                (new RefreshFileListTask(resourceManager, bucketName, Utils.getFilePrefix(key), true, false)).run();
        } else
        {
            for(int i = 0; i < retry; i++)
                if(!deleteFile(key))
                    result = false;

            if(fresh)
                (new RefreshFileListTask(resourceManager, bucketName, Utils.getFilePrefix(key), true, false)).run();
        }
        return Boolean.valueOf(result);
    }

    public void run()
    {
        call();
    }

    private boolean deleteFile(String object)
    {
        int r = 0;
          goto _L1
_L3:
        String log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Deleting file ").append(object).append("...").toString();
        resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
        resourceManager.getOssClient().deleteObject(bucketName, object);
        log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" file ").append(object).append(" has been successfully deleted").toString();
        resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
        return true;
        Exception e;
        e;
        String log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" file ").append(object).append(" delete failed").toString();
        resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.ERROR, log);
        r++;
_L1:
        if(r < retry) goto _L3; else goto _L2
_L2:
        return false;
    }

    private ResourceManager resourceManager;
    private final String bucketName;
    private String key;
    private boolean fresh;
    private int retry;
}
