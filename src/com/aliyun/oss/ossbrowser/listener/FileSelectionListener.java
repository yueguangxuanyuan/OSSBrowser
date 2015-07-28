// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileSelectionListener.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.aos.services.oss.model.Bucket;
import com.aliyun.aos.services.oss.model.OSSObjectSummary;
import com.aliyun.oss.ossbrowser.control.GetObjectHeaderTask;
import com.aliyun.oss.ossbrowser.control.GetObjectPropertyTask;
import com.aliyun.oss.ossbrowser.model.BucketIconListItem;
import com.aliyun.oss.ossbrowser.model.IconAndObjectSummary;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.view.ConsolePanel;
import com.aliyun.oss.ossbrowser.view.HttpHeadersPanel;
import java.util.concurrent.ExecutorService;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FileSelectionListener
    implements ListSelectionListener
{

    public FileSelectionListener(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    public void valueChanged(ListSelectionEvent e)
    {
        int row = resourceManager.getFileListTable().getSelectedRow();
        int col = resourceManager.getFileListTable().convertColumnIndexToView(0);
        if(row != -1)
        {
            IconAndObjectSummary o = (IconAndObjectSummary)resourceManager.getFileListTable().getValueAt(row, col);
            String key = o.getSummary().getKey();
            BucketIconListItem item = (BucketIconListItem)resourceManager.getBucketList().getSelectedValue();
            String bucketName = item.getBucket().getName();
            if(key.equals(".."))
                return;
            if(resourceManager.getConsolePanel().getSelectedIndex() == 2)
                resourceManager.getService().submit(new GetObjectPropertyTask(resourceManager, bucketName, key));
            else
            if(resourceManager.getConsolePanel().getSelectedIndex() == 1)
            {
                resourceManager.getConsolePanel().getHttpHeadPanel().setUrl(bucketName, key);
                resourceManager.getService().submit(new GetObjectHeaderTask(resourceManager, bucketName, key));
            }
        }
    }

    private ResourceManager resourceManager;
}
