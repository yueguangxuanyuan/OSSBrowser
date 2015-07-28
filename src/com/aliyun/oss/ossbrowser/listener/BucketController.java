// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BucketController.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.aos.services.oss.model.Bucket;
import com.aliyun.oss.ossbrowser.control.*;
import com.aliyun.oss.ossbrowser.model.BucketIconListItem;
import com.aliyun.oss.ossbrowser.model.FileTableModel;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.view.CreateBucketDialog;
import com.aliyun.oss.ossbrowser.view.FileListTablePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import javax.swing.*;

public class BucketController
    implements ActionListener
{

    public BucketController(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    public void actionPerformed(ActionEvent e)
    {
        ExecutorService service = resourceManager.getService();
        if(e.getActionCommand().equals("\u5237\u65B0"))
            service.submit(new RefreshBucketTask(resourceManager));
        else
        if(e.getActionCommand().equals("\u65B0\u5EFA"))
            resourceManager.getCreateBucketDialog().setVisible(true);
        else
        if(e.getActionCommand().equals("\u5220\u9664"))
        {
            BucketIconListItem item = (BucketIconListItem)resourceManager.getBucketList().getSelectedValue();
            if(item != null)
            {
                int ret = JOptionPane.showConfirmDialog(resourceManager.getMainFrame(), (new StringBuilder("\u786E\u5B9E\u8981\u5220\u9664\"")).append(item.getBucket().getName()).append("\"\u5417?").toString(), "\u786E\u8BA4bucket\u5220\u9664", 0);
                if(ret == 0)
                {
                    resourceManager.getFileListTable().clearSelection();
                    resourceManager.getFileTableModel().removeAllRows(resourceManager.getFileListTable());
                    resourceManager.getFileListTablePanel().setPath("");
                    service.submit(new DeleteBucketTask(resourceManager, item));
                }
            }
        } else
        if(e.getActionCommand().equals("OK"))
        {
            String bucketName = resourceManager.getBucketNameText().getText().trim();
            String bucketAcl = resourceManager.getBucketAclComboBox().getSelectedItem().toString();
            service.submit(new AddBucketTask(resourceManager, bucketName, bucketAcl));
        }
    }

    private ResourceManager resourceManager;
}
