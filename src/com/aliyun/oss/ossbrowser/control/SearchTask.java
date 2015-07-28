// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SearchTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.aos.services.oss.model.*;
import com.aliyun.oss.ossbrowser.model.FileTableModel;
import com.aliyun.oss.ossbrowser.model.IconAndObjectSummary;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.view.ConsolePanel;
import com.aliyun.oss.ossbrowser.view.SearchPanel;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JTable;

public class SearchTask
    implements Runnable
{

    public SearchTask(ResourceManager resourceManager, String bucket, String prefix, String content)
    {
        currentFlag = 0;
        this.resourceManager = resourceManager;
        this.bucket = bucket;
        this.prefix = prefix;
        this.content = content;
    }

    public void run()
    {
        resourceManager.getConsolePanel().setSelectedIndex(4);
        currentFlag = flag.incrementAndGet();
        synchronized(lock)
        {
            FileTableModel model = resourceManager.getConsolePanel().getSearchPanel().getModel();
            JTable table = resourceManager.getConsolePanel().getSearchPanel().getFileTable();
            resourceManager.getFileListTable().clearSelection();
            model.removeAllRows(table);
            IconAndObjectSummary ic = IconAndObjectSummary.createLoadingItem(bucket);
            ic.getSummary().setKey("\u641C\u7D22\u4E2D...");
            model.addRow(new Object[] {
                ic
            });
            ListObjectsRequest request = new ListObjectsRequest(bucket, prefix, "", "", Integer.valueOf(1000));
            ObjectListing list = resourceManager.getOssClient().listObjects(request);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nextMark = "";
            do
            {
                for(Iterator iterator = list.getObjectSummaries().iterator(); iterator.hasNext();)
                {
                    OSSObjectSummary object = (OSSObjectSummary)iterator.next();
                    String key = getFileName(object.getKey());
                    if(!object.getKey().equals(nextMark) && key.toLowerCase().contains(content.toLowerCase()))
                        if(object.getKey().endsWith("/"))
                        {
                            IconAndObjectSummary item = IconAndObjectSummary.createFolderItem(bucket, object.getKey());
                            Object row[] = {
                                item
                            };
                            model.addRow(row);
                        } else
                        {
                            IconAndObjectSummary item = IconAndObjectSummary.createFileItem(object);
                            Object row[] = {
                                item, Long.valueOf(object.getSize()), object.getETag(), format.format(object.getLastModified())
                            };
                            model.addRow(row);
                        }
                }

                if(!list.isTruncated() || flag.get() != currentFlag)
                    break;
                nextMark = list.getNextMarker();
                list = resourceManager.getOssClient().listNextBatchOfObjects(list);
            } while(true);
            model.removeRow(0);
        }
    }

    public static void stop()
    {
        flag.incrementAndGet();
    }

    String getFileName(String path)
    {
        if(path.length() < 2)
            return path;
        int i = path.lastIndexOf('/', path.length() - 2);
        if(i != -1)
            return path.substring(i + 1);
        else
            return path;
    }

    private static AtomicInteger flag = new AtomicInteger(0);
    private static Object lock = new Object();
    private int currentFlag;
    private ResourceManager resourceManager;
    private String bucket;
    private String prefix;
    private String content;

}
