// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GetObjectPropertyTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.aos.services.oss.model.OSSObjectSummary;
import com.aliyun.aos.services.oss.model.ObjectListing;
import com.aliyun.oss.ossbrowser.model.PropertyTableModel;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.view.ConsolePanel;
import com.aliyun.oss.ossbrowser.view.PropertyPanel;
import java.util.List;

public class GetObjectPropertyTask
    implements Runnable
{

    public GetObjectPropertyTask(ResourceManager resourceManager, String bucketName, String key)
    {
        this.resourceManager = resourceManager;
        this.bucketName = bucketName;
        this.key = key;
    }

    public void run()
    {
        PropertyTableModel model = resourceManager.getConsolePanel().getPropertiesPanel().getTableModel();
        int count = model.requestCountInc();
        model.initObjectProperty();
        ObjectListing objectListing = resourceManager.getOssClient().listObjects(bucketName, key);
        List list = objectListing.getObjectSummaries();
        if(((OSSObjectSummary)list.get(0)).getKey().equals(key))
        {
            model.setObjectProperty((OSSObjectSummary)list.get(0), count);
            return;
        } else
        {
            model.clearProperty();
            return;
        }
    }

    private ResourceManager resourceManager;
    private String bucketName;
    private String key;
}
