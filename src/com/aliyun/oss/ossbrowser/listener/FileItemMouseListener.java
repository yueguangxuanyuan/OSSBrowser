// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileItemMouseListener.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.aos.services.oss.model.OSSObjectSummary;
import com.aliyun.oss.ossbrowser.model.IconAndObjectSummary;
import com.aliyun.oss.ossbrowser.utils.*;
import java.awt.event.*;
import java.io.File;
import java.util.Map;
import javax.swing.*;

public class FileItemMouseListener extends MouseAdapter
{

    public FileItemMouseListener(ResourceManager resourceManager, JTable table)
    {
        this.resourceManager = resourceManager;
        this.table = table;
    }

    public void mouseClicked(MouseEvent e)
    {
        int row = e.getY() / table.getRowHeight();
        int col = table.convertColumnIndexToView(0);
        IconAndObjectSummary os = (IconAndObjectSummary)table.getValueAt(row, col);
        final String bucket = os.getSummary().getBucketName();
        String prefix = os.getSummary().getType();
        final String key = os.getSummary().getKey();
        int index = -1;
        if(e.getClickCount() >= 2)
        {
            if(FileAccess.isFolder(key))
            {
                index = getBucketIndex(bucket);
                if(index == -1)
                    return;
                resourceManager.getBucketList().setSelectedIndex(index);
                FileAccess.refresh(bucket, key, resourceManager, true);
            } else
            if(key.equals(".."))
                FileAccess.back(bucket, prefix, resourceManager);
            else
            if(!key.equals("Loading..."))
                FileAccess.download(bucket, key, resourceManager);
        } else
        if(e.getClickCount() < 2 && e.getButton() == 3)
        {
            JPopupMenu popupMenu = new JPopupMenu();
            if(!key.equals(".."))
            {
                JMenuItem download = new JMenuItem("\u4E0B\u8F7D");
                download.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        FileAccess.download(bucket, key, resourceManager);
                    }

                    final FileItemMouseListener this$0;
                    private final String val$bucket;
                    private final String val$key;

            
            {
                this$0 = FileItemMouseListener.this;
                bucket = s;
                key = s1;
                super();
            }
                }
);
                popupMenu.add(download);
                JMenuItem delete = new JMenuItem("\u5220\u9664");
                delete.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        FileAccess.delete(bucket, key, resourceManager);
                    }

                    final FileItemMouseListener this$0;
                    private final String val$bucket;
                    private final String val$key;

            
            {
                this$0 = FileItemMouseListener.this;
                bucket = s;
                key = s1;
                super();
            }
                }
);
                popupMenu.add(delete);
            }
            if(FileAccess.isFolder(key))
            {
                JMenuItem sync = new JMenuItem("\u540C\u6B65\u5230\u672C\u5730");
                sync.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        JFileChooser fileChooser = new JFileChooser(".");
                        fileChooser.setMultiSelectionEnabled(false);
                        fileChooser.setFileSelectionMode(1);
                        if(fileChooser.showOpenDialog(resourceManager.getMainFrame()) == 0)
                        {
                            File f = fileChooser.getSelectedFile();
                            SyncUtils.addSyncTask(bucket, key, f.getAbsolutePath(), resourceManager);
                        }
                    }

                    final FileItemMouseListener this$0;
                    private final String val$bucket;
                    private final String val$key;

            
            {
                this$0 = FileItemMouseListener.this;
                bucket = s;
                key = s1;
                super();
            }
                }
);
                popupMenu.add(sync);
            }
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private int getBucketIndex(String bucket)
    {
        DefaultListModel defaultlistmodel = resourceManager.getBucketListModel();
        JVM INSTR monitorenter ;
        return ((Integer)resourceManager.getBucketIndexMap().get(bucket)).intValue();
        defaultlistmodel;
        JVM INSTR monitorexit ;
        throw ;
    }

    private ResourceManager resourceManager;
    private JTable table;

}
