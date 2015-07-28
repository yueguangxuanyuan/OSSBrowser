// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConsolePanelChangeListener.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.aos.services.oss.model.Bucket;
import com.aliyun.oss.ossbrowser.control.*;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.utils.Utils;
import com.aliyun.oss.ossbrowser.view.ConsolePanel;
import com.aliyun.oss.ossbrowser.view.HttpHeadersPanel;
import java.util.concurrent.ExecutorService;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ConsolePanelChangeListener
    implements ChangeListener
{

    public ConsolePanelChangeListener(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    public void stateChanged(ChangeEvent e)
    {
        String key = null;
        String bucketName = null;
        Bucket bucket = null;
        bucket = Utils.GetCurrentBucket(resourceManager);
        key = Utils.getCurrentObject(resourceManager);
        if(bucket == null || bucket.getName() == null || bucket.getName().equals(""))
            return;
        bucketName = bucket.getName();
        int panelIndex = ((JTabbedPane)e.getSource()).getSelectedIndex();
        if(panelIndex == 1)
        {
            if(!key.equals("") && !key.equals(".."))
            {
                resourceManager.getConsolePanel().getHttpHeadPanel().setUrl(bucketName, key);
                resourceManager.getService().submit(new GetObjectHeaderTask(resourceManager, bucketName, key));
            }
        } else
        if(panelIndex == 2)
            if(!key.equals("") && !key.equals(".."))
                resourceManager.getService().submit(new GetObjectPropertyTask(resourceManager, bucketName, key));
            else
                resourceManager.getService().submit(new GetBucketPropertyTask(resourceManager, bucket));
    }

    ResourceManager resourceManager;
}
