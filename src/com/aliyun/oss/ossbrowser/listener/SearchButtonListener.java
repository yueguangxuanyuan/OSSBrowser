// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SearchButtonListener.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.aos.services.oss.model.Bucket;
import com.aliyun.oss.ossbrowser.control.SearchTask;
import com.aliyun.oss.ossbrowser.model.BucketIconListItem;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.view.FileListTablePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class SearchButtonListener
    implements ActionListener
{

    public SearchButtonListener(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    public void actionPerformed(ActionEvent e)
    {
        String bucket = null;
        String prefix = "";
        int index = resourceManager.getBucketList().getSelectedIndex();
        if(index != -1)
        {
            BucketIconListItem item = (BucketIconListItem)resourceManager.getBucketListModel().get(index);
            bucket = item.getBucket().getName();
            prefix = resourceManager.getFileListTablePanel().getPath();
        } else
        {
            return;
        }
        String content = resourceManager.getFileListTablePanel().getSearchInput();
        SearchTask task = new SearchTask(resourceManager, bucket, prefix, content);
        resourceManager.getService().submit(task);
    }

    ResourceManager resourceManager;
}
