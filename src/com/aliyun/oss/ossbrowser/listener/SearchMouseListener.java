// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SearchMouseListener.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.aos.services.oss.model.OSSObjectSummary;
import com.aliyun.oss.ossbrowser.control.*;
import com.aliyun.oss.ossbrowser.model.FileTableModel;
import com.aliyun.oss.ossbrowser.model.IconAndObjectSummary;
import com.aliyun.oss.ossbrowser.utils.FileAccess;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.view.ConsolePanel;
import com.aliyun.oss.ossbrowser.view.SearchPanel;
import java.awt.event.*;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import javax.swing.*;

public class SearchMouseListener extends MouseAdapter
{

    public SearchMouseListener(ResourceManager resourceManager, JTable table)
    {
        this.resourceManager = resourceManager;
        this.table = table;
    }

    public void mouseClicked(MouseEvent e)
    {
        int index;
        final IconAndObjectSummary os;
        final String bucket;
        final String key;
label0:
        {
            index = -1;
            int row = e.getY() / table.getRowHeight();
            int col = table.convertColumnIndexToView(0);
            os = (IconAndObjectSummary)table.getValueAt(row, col);
            bucket = os.getSummary().getBucketName();
            key = os.getSummary().getKey();
            if(e.getClickCount() < 2)
                break MISSING_BLOCK_LABEL_191;
            if(key.equals("\u641C\u7D22\u4E2D..."))
                break MISSING_BLOCK_LABEL_351;
            if(!FileAccess.isFolder(key))
                break MISSING_BLOCK_LABEL_177;
            synchronized(resourceManager.getBucketListModel())
            {
                index = ((Integer)resourceManager.getBucketIndexMap().get(os.getSummary().getBucketName())).intValue();
                if(index != -1)
                    break label0;
            }
            return;
        }
        defaultlistmodel;
        JVM INSTR monitorexit ;
        resourceManager.getBucketList().setSelectedIndex(index);
        (new RefreshFileListTask(resourceManager, bucket, key, true, true)).run();
        break MISSING_BLOCK_LABEL_351;
        FileAccess.download(bucket, key, resourceManager);
        break MISSING_BLOCK_LABEL_351;
        if(e.getClickCount() < 2 && e.getButton() == 3)
        {
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem stopSearch = new JMenuItem("\u505C\u6B62\u641C\u7D22");
            stopSearch.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    SearchTask.stop();
                }

                final SearchMouseListener this$0;

            
            {
                this$0 = SearchMouseListener.this;
                super();
            }
            }
);
            popupMenu.add(stopSearch);
            if(!os.getSummary().getKey().equals("\u641C\u7D22\u4E2D..."))
            {
                JMenuItem download = new JMenuItem("\u4E0B\u8F7D");
                download.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        FileAccess.download(bucket, key, resourceManager);
                    }

                    final SearchMouseListener this$0;
                    private final String val$bucket;
                    private final String val$key;

            
            {
                this$0 = SearchMouseListener.this;
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
                        delete(os);
                    }

                    final SearchMouseListener this$0;
                    private final IconAndObjectSummary val$os;

            
            {
                this$0 = SearchMouseListener.this;
                os = iconandobjectsummary;
                super();
            }
                }
);
                popupMenu.add(delete);
            }
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private void delete(IconAndObjectSummary os)
    {
        String key = os.getSummary().getKey();
        DeleteFileTask task = new DeleteFileTask(resourceManager, os.getSummary().getBucketName(), key);
        resourceManager.getDataService().submit(task);
        resourceManager.getConsolePanel().getSearchPanel().getModel().removeByKey(key);
    }

    ResourceManager resourceManager;
    JTable table;

}
