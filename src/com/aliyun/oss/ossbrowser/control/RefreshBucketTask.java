// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RefreshBucketTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.aos.services.oss.model.Bucket;
import com.aliyun.aos.services.oss.model.ListBucketResult;
import com.aliyun.oss.ossbrowser.model.BucketIconListItem;
import com.aliyun.oss.ossbrowser.model.LogLevel;
import com.aliyun.oss.ossbrowser.utils.*;
import com.aliyun.oss.ossbrowser.view.ConsolePanel;
import com.aliyun.oss.ossbrowser.view.LogPanel;
import java.util.*;
import javax.swing.*;

public class RefreshBucketTask
    implements Runnable
{

    public RefreshBucketTask(ResourceManager manager)
    {
        resourceManager = manager;
    }

    public void run()
    {
label0:
        {
            String log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Getting bucket list").append("...").toString();
            resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
            DefaultListModel listModel;
            List buckets;
            int index;
            Bucket bucket;
            Iterator iterator;
            synchronized(resourceManager.getBucketListModel())
            {
                listModel = resourceManager.getBucketListModel();
                resourceManager.getBucketList().clearSelection();
                listModel.clear();
                try
                {
                    buckets = resourceManager.getOssClient().listBuckets().getBuckets();
                    log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Buckets has been listed. ").append("Total number of buckets in your account is ").append(buckets.size()).toString();
                    resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
                    break label0;
                }
                catch(Exception e)
                {
                    log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" List bucket failed").toString();
                    resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.ERROR, log);
                }
            }
            return;
        }
        resourceManager.getBucketIndexMap().clear();
        index = 0;
        for(iterator = buckets.iterator(); iterator.hasNext(); resourceManager.getBucketIndexMap().put(bucket.getName(), Integer.valueOf(index++)))
        {
            bucket = (Bucket)iterator.next();
            listModel.addElement(createBucketItem(bucket));
        }

        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                resourceManager.getBucketList().updateUI();
            }

            final RefreshBucketTask this$0;

            
            {
                this$0 = RefreshBucketTask.this;
                super();
            }
        }
);
        defaultlistmodel;
        JVM INSTR monitorexit ;
    }

    private BucketIconListItem createBucketItem(Bucket bucket)
    {
        return new BucketIconListItem(ImageResource.bucketIcon, bucket);
    }

    private ResourceManager resourceManager;

}
