// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BucketListSelectionListener.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.aos.services.oss.model.Bucket;
import com.aliyun.oss.ossbrowser.control.GetBucketPropertyTask;
import com.aliyun.oss.ossbrowser.control.RefreshFileListTask;
import com.aliyun.oss.ossbrowser.model.BucketIconListItem;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.view.ConsolePanel;
import java.util.concurrent.ExecutorService;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BucketListSelectionListener
    implements ListSelectionListener
{

    public BucketListSelectionListener(ResourceManager resource)
    {
        resourceManager = resource;
    }

    public void valueChanged(ListSelectionEvent e)
    {
        int i = resourceManager.getBucketList().getSelectedIndex();
        if(i != -1)
        {
            Bucket bucket = ((BucketIconListItem)resourceManager.getBucketListModel().get(i)).getBucket();
            RefreshFileListTask task = new RefreshFileListTask(resourceManager, bucket.getName(), "", true, true);
            resourceManager.getService().submit(task);
            if(resourceManager.getConsolePanel().getSelectedIndex() == 2)
            {
                GetBucketPropertyTask propertyTask = new GetBucketPropertyTask(resourceManager, bucket);
                resourceManager.getService().submit(propertyTask);
            }
        }
    }

    private ResourceManager resourceManager;
}
