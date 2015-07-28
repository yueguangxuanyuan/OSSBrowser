// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SyncTableMouseListener.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.oss.ossbrowser.control.SyncTask;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.utils.SyncUtils;
import com.aliyun.oss.ossbrowser.view.ConsolePanel;
import com.aliyun.oss.ossbrowser.view.SyncPanel;
import java.awt.event.*;
import java.io.File;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SyncTableMouseListener extends MouseAdapter
{

    public SyncTableMouseListener(ResourceManager resourceManager, JTable table)
    {
        this.resourceManager = resourceManager;
        this.table = table;
    }

    public void mouseClicked(MouseEvent e)
    {
        if(e.getClickCount() < 2 && e.getButton() == 3)
        {
            final int row = e.getY() / table.getRowHeight();
            int col = table.convertColumnIndexToView(0);
            final String local = (String)table.getValueAt(row, col);
            String cloud = (String)table.getValueAt(row, 1 - col);
            final SyncTask task = (SyncTask)resourceManager.getSyncTaskMap().get(local);
            if(cloud.indexOf("/") == -1)
                return;
            final String bucket = cloud.substring(0, cloud.indexOf("/"));
            final String key = cloud.substring(cloud.indexOf("/") + 1);
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem cancel = new JMenuItem("\u53D6\u6D88\u540C\u6B65");
            JMenuItem replace = new JMenuItem("\u66F4\u6362\u672C\u5730\u76EE\u5F55");
            popupMenu.add(cancel);
            popupMenu.add(replace);
            cancel.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    task.stop();
                    resourceManager.getSyncFileMap().remove(local);
                    resourceManager.getSyncTaskMap().remove(local);
                    resourceManager.getConsolePanel().getSyncPanel().getTableModel().removeRow(row);
                    SyncUtils.saveSyncList(resourceManager.getSyncFileMap(), resourceManager.getSyncListFile());
                }

                final SyncTableMouseListener this$0;
                private final SyncTask val$task;
                private final String val$local;
                private final int val$row;

            
            {
                this$0 = SyncTableMouseListener.this;
                task = synctask;
                local = s;
                row = i;
                super();
            }
            }
);
            replace.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    JFileChooser fileChooser = new JFileChooser(".");
                    fileChooser.setMultiSelectionEnabled(false);
                    fileChooser.setFileSelectionMode(1);
                    if(fileChooser.showOpenDialog(resourceManager.getMainFrame()) == 0)
                    {
                        File f = fileChooser.getSelectedFile();
                        task.stop();
                        resourceManager.getSyncFileMap().remove(local);
                        resourceManager.getSyncTaskMap().remove(local);
                        resourceManager.getConsolePanel().getSyncPanel().getTableModel().removeRow(row);
                        SyncUtils.addSyncTask(bucket, key, f.getAbsolutePath(), resourceManager);
                    }
                }

                final SyncTableMouseListener this$0;
                private final SyncTask val$task;
                private final String val$local;
                private final int val$row;
                private final String val$bucket;
                private final String val$key;

            
            {
                this$0 = SyncTableMouseListener.this;
                task = synctask;
                local = s;
                row = i;
                bucket = s1;
                key = s2;
                super();
            }
            }
);
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    ResourceManager resourceManager;
    JTable table;
}
