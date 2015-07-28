// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaskMouseListener.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.oss.ossbrowser.control.*;
import com.aliyun.oss.ossbrowser.model.IconAndText;
import com.aliyun.oss.ossbrowser.model.IconTableModel;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.utils.Utils;
import java.awt.event.*;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.prefs.Preferences;
import javax.swing.*;

public class TaskMouseListener extends MouseAdapter
{

    public TaskMouseListener(JTable table)
    {
        this.table = table;
    }

    public void mousePressed(MouseEvent evt)
    {
        int row = evt.getY() / table.getRowHeight();
        int column = table.convertColumnIndexToView(3);
        String status = (String)table.getValueAt(row, column);
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem retryAll = new JMenuItem("\u91CD\u8BD5\u6240\u6709\u5931\u8D25\u4EFB\u52A1");
        JMenuItem removeAll = new JMenuItem("\u6E05\u9664\u6240\u6709\u5931\u8D25\u4EFB\u52A1");
        removeAll.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                IconTableModel model = (IconTableModel)table.getModel();
                model.removeAllFailTask();
            }

            final TaskMouseListener this$0;

            
            {
                this$0 = TaskMouseListener.this;
                super();
            }
        }
);
        retryAll.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                IconTableModel model = (IconTableModel)table.getModel();
                model.retryAllFailTask();
            }

            final TaskMouseListener this$0;

            
            {
                this$0 = TaskMouseListener.this;
                super();
            }
        }
);
        popupMenu.add(removeAll);
        popupMenu.add(retryAll);
        if(status.equals("Failed") || status.equals("Waiting"))
        {
            int col = table.convertColumnIndexToView(0);
            IconAndText iconAndText = (IconAndText)table.getValueAt(row, col);
            final IconTableModel model = (IconTableModel)table.getModel();
            final ResourceManager resourceManager = iconAndText.getResourceManager();
            final Preferences uploadPrefs = resourceManager.getUploadPerfs();
            final String taskName = iconAndText.getText();
            final String bucket = iconAndText.getBucket();
            final String object = iconAndText.getObject();
            final String path = iconAndText.getPath();
            final int type = iconAndText.getStatus();
            final long size = iconAndText.getSize();
            JMenuItem retry = new JMenuItem("\u91CD\u8BD5");
            JMenuItem remove = new JMenuItem("\u6E05\u9664");
            remove.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    model.removeTask(taskName);
                    File file = new File(path);
                    Utils.RemoveUploadMeta(uploadPrefs, Utils.GetUploadMetaKey(bucket, (new StringBuilder(String.valueOf(object))).append(file.getName()).toString()));
                }

                final TaskMouseListener this$0;
                private final IconTableModel val$model;
                private final String val$taskName;
                private final String val$path;
                private final Preferences val$uploadPrefs;
                private final String val$bucket;
                private final String val$object;

            
            {
                this$0 = TaskMouseListener.this;
                model = icontablemodel;
                taskName = s;
                path = s1;
                uploadPrefs = preferences;
                bucket = s2;
                object = s3;
                super();
            }
            }
);
            retry.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    String newTaskName = Utils.generateTaskName(type, path, size, bucket, object, resourceManager);
                    model.removeTask(taskName);
                    switch(type)
                    {
                    case 2: // '\002'
                        DownloadFileTask downloadTask = new DownloadFileTask(resourceManager, bucket, object, new File(path), size, newTaskName);
                        resourceManager.getService().submit(downloadTask);
                        break;

                    case 1: // '\001'
                        UploadFolderTask uploadFolderTask = new UploadFolderTask(resourceManager, bucket, object, path, newTaskName);
                        resourceManager.getDataService().submit(uploadFolderTask);
                        // fall through

                    case 0: // '\0'
                        UploadFileTask uploadTask = new UploadFileTask(resourceManager, new File(path), newTaskName, bucket, object, true, 20, true);
                        resourceManager.getDataService().submit(uploadTask);
                        break;
                    }
                }

                final TaskMouseListener this$0;
                private final int val$type;
                private final String val$path;
                private final long val$size;
                private final String val$bucket;
                private final String val$object;
                private final ResourceManager val$resourceManager;
                private final IconTableModel val$model;
                private final String val$taskName;

            
            {
                this$0 = TaskMouseListener.this;
                type = i;
                path = s;
                size = l;
                bucket = s1;
                object = s2;
                resourceManager = resourcemanager;
                model = icontablemodel;
                taskName = s3;
                super();
            }
            }
);
            popupMenu.add(retry);
            popupMenu.add(remove);
        }
        popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
    }

    JTable table;
}
