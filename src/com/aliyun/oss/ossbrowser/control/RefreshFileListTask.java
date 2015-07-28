// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RefreshFileListTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.aos.services.oss.model.*;
import com.aliyun.oss.ossbrowser.model.*;
import com.aliyun.oss.ossbrowser.utils.*;
import com.aliyun.oss.ossbrowser.view.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultListModel;
import javax.swing.JTable;

public class RefreshFileListTask
    implements Runnable
{

    public RefreshFileListTask(ResourceManager resourceManager, String bucket, String prefix, boolean isForce, boolean readCache)
    {
        currentFlag = 0;
        this.resourceManager = resourceManager;
        bucketName = bucket;
        this.prefix = prefix;
        this.isForce = isForce;
        this.readCache = readCache;
    }

    public void run()
    {
        refresh();
    }

    private boolean refresh()
    {
        FileTableModel tableModel;
        List fileList;
label0:
        {
            tableModel = resourceManager.getFileTableModel();
            fileList = new LinkedList();
            synchronized(lock)
            {
                if(!isRefreshing || !bucketName.equals(refreshingBucketName) || !prefix.equals(refreshingPrefix))
                    break label0;
            }
            return true;
        }
        if(isForce || !isRefreshing)
            break MISSING_BLOCK_LABEL_75;
        obj;
        JVM INSTR monitorexit ;
        return false;
        obj;
        JVM INSTR monitorexit ;
label1:
        {
            currentFlag = flag.incrementAndGet();
            String log;
            synchronized(resourceManager.getBucketListModel())
            {
                synchronized(lock)
                {
                    refreshingBucketName = bucketName;
                    refreshingPrefix = prefix;
                    isRefreshing = true;
                }
                if(!readCache || !readFromCache())
                    break label1;
                synchronized(lock)
                {
                    isRefreshing = false;
                }
            }
            return true;
        }
        log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Collecting files from ").append(bucketName).append("/").append(prefix).append("...").toString();
        resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
        initFileList(fileList);
        listAllFile(fileList);
        synchronized(lock)
        {
            isRefreshing = false;
        }
        defaultlistmodel;
        JVM INSTR monitorexit ;
        String log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Files has been collected from ").append(bucketName).append("/").append(prefix).toString();
        resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
        if(tableModel.getRowCount() == 2)
        {
            String s1 = ((IconAndObjectSummary)resourceManager.getFileTableModel().getValueAt(1, 0)).getSummary().getKey();
            if(s1.endsWith("//"))
                return (new RefreshFileListTask(resourceManager, bucketName, s1, isForce, readCache)).refresh();
            fileListCache.add(bucketName, prefix, fileList);
        } else
        {
            fileListCache.add(bucketName, prefix, fileList);
        }
        return true;
    }

    private void initFileList(List fileList)
    {
        FileTableModel tableModel = resourceManager.getFileTableModel();
        resourceManager.getFileListTablePanel().setPath(refreshingPrefix);
        resourceManager.getFileListTable().clearSelection();
        tableModel.setBucketName(bucketName);
        tableModel.removeAllRows(resourceManager.getFileListTable());
        IconAndObjectSummary ic = IconAndObjectSummary.createLoadingItem(bucketName);
        tableModel.addRow(new Object[] {
            ic
        });
        if(!prefix.equals(""))
        {
            IconAndObjectSummary is = IconAndObjectSummary.createFolderItem(bucketName, "..");
            is.getSummary().setType(prefix);
            Object row[] = {
                is
            };
            fileList.add(((Object) (row)));
            tableModel.addRow(row);
        }
    }

    private void listAllFile(List fileList)
    {
        FileTableModel tableModel = resourceManager.getFileTableModel();
        Set filesSet = new HashSet();
        ListObjectsRequest request = new ListObjectsRequest(bucketName, prefix, "", "/", Integer.valueOf(1000));
        ObjectListing list = resourceManager.getOssClient().listObjects(request);
        filesSet.addAll(list.getObjectSummaries());
        Object row[];
        for(Iterator iterator = list.getCommonPrefixes().iterator(); iterator.hasNext(); tableModel.addRow(row))
        {
            String folder = (String)iterator.next();
            IconAndObjectSummary item = IconAndObjectSummary.createFolderItem(bucketName, folder);
            row = (new Object[] {
                item
            });
            fileList.add(((Object) (row)));
        }

        while(list.isTruncated()) 
        {
            String nextMark = list.getNextMarker();
            list = resourceManager.getOssClient().listNextBatchOfObjects(list);
            filesSet.addAll(list.getObjectSummaries());
            for(Iterator iterator1 = list.getCommonPrefixes().iterator(); iterator1.hasNext();)
            {
                String folder = (String)iterator1.next();
                if(!folder.equals(nextMark))
                {
                    IconAndObjectSummary item = IconAndObjectSummary.createFolderItem(bucketName, folder);
                    Object row[] = {
                        item
                    };
                    fileList.add(((Object) (row)));
                    tableModel.addRow(row);
                }
            }

            if(flag.get() != currentFlag)
            {
                isRefreshing = false;
                return;
            }
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Iterator iterator2 = filesSet.iterator(); iterator2.hasNext();)
        {
            OSSObjectSummary summary = (OSSObjectSummary)iterator2.next();
            if(!summary.getKey().equals(prefix))
            {
                IconAndObjectSummary item = IconAndObjectSummary.createFileItem(summary);
                Object row[] = {
                    item, Long.valueOf(summary.getSize()), summary.getETag(), format.format(summary.getLastModified()), summary.getStorageClass(), summary.getType()
                };
                fileList.add(((Object) (row)));
                tableModel.addRow(row);
            }
        }

        tableModel.removeRow(0);
    }

    private boolean readFromCache()
    {
        List fileList = fileListCache.get(bucketName, prefix);
        if(fileList != null)
        {
            FileTableModel tableModel = resourceManager.getFileTableModel();
            resourceManager.getFileListTablePanel().setPath(prefix);
            tableModel.removeAllRows(resourceManager.getFileListTable());
            Object row[];
            for(Iterator iterator = fileList.iterator(); iterator.hasNext(); tableModel.addRow(row))
                row = (Object[])iterator.next();

            return true;
        } else
        {
            return false;
        }
    }

    private ResourceManager resourceManager;
    private final String bucketName;
    private String prefix;
    private int currentFlag;
    private boolean isForce;
    private boolean readCache;
    private static Object lock = new Object();
    private static boolean isRefreshing = false;
    private static String refreshingBucketName = "";
    private static String refreshingPrefix = "";
    private static AtomicInteger flag = new AtomicInteger(0);
    private static FileListCache fileListCache = new FileListCache(100);

}
