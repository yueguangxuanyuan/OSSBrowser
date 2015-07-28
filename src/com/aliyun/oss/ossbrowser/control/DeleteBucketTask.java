// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteBucketTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.aos.services.oss.model.*;
import com.aliyun.oss.ossbrowser.model.BucketIconListItem;
import com.aliyun.oss.ossbrowser.model.LogLevel;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.utils.Utils;
import com.aliyun.oss.ossbrowser.view.ConsolePanel;
import com.aliyun.oss.ossbrowser.view.LogPanel;
import java.util.*;
import java.util.concurrent.ExecutorService;
import javax.swing.JOptionPane;

// Referenced classes of package com.aliyun.oss.ossbrowser.control:
//            RefreshBucketTask

public class DeleteBucketTask
    implements Runnable
{

    public DeleteBucketTask(ResourceManager manager, BucketIconListItem item)
    {
        this(manager, item, 1);
    }

    public DeleteBucketTask(ResourceManager manager, BucketIconListItem item, int retry)
    {
        resourceManager = manager;
        this.item = item;
        this.retry = retry;
    }

    public void run()
    {
        String log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Deleting bucket ").append(item.getBucket().getName()).append("...").toString();
        resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
        if(deleteBucket())
        {
            log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Bucket ").append(item.getBucket().getName()).append(" has been successfully deleted").toString();
            resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
            resourceManager.getService().submit(new RefreshBucketTask(resourceManager));
        } else
        {
            log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Bucket ").append(item.getBucket().getName()).append(" delete failed").toString();
            resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.ERROR, log);
        }
    }

    private boolean deleteBucket()
    {
        int r;
        ObjectListing objects;
        try
        {
            objects = resourceManager.getOssClient().listAllObjects(item.getBucket().getName(), null, null);
        }
        catch(Exception e)
        {
            return false;
        }
        for(Iterator iterator = objects.getObjectSummaries().iterator(); iterator.hasNext();)
        {
            OSSObjectSummary object = (OSSObjectSummary)iterator.next();
            for(int r = 0; r < retry;)
                try
                {
                    resourceManager.getOssClient().deleteObject(item.getBucket().getName(), object.getKey());
                    break;
                }
                catch(Exception e)
                {
                    if(r + 1 == retry)
                    {
                        JOptionPane.showMessageDialog(resourceManager.getMainFrame(), (new StringBuilder("\u5220\u9664bucket:")).append(object.getBucketName()).append("\u65F6\u51FA\u9519,").append("\u6587\u4EF6:").append(object.getKey()).append("\u65E0\u6CD5\u5220\u9664!").toString(), "\u9519\u8BEF", 0);
                        return false;
                    }
                    r++;
                }

        }

        r = 0;
          goto _L1
_L3:
        resourceManager.getOssClient().deleteBucket(item.getBucket().getName());
        return true;
        Exception e;
        e;
        if(r + 1 == retry)
        {
            JOptionPane.showMessageDialog(resourceManager.getMainFrame(), (new StringBuilder("\u5220\u9664bucket:")).append(item.getBucket().getName()).append("\u65F6\u51FA\u9519!").toString(), "\u9519\u8BEF", 0);
            return false;
        }
        r++;
_L1:
        if(r < retry) goto _L3; else goto _L2
_L2:
        return false;
    }

    private ResourceManager resourceManager;
    private BucketIconListItem item;
    private int retry;
}
