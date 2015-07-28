// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UploadFolderRecursivelyTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.oss.ossbrowser.model.FileTableModel;
import com.aliyun.oss.ossbrowser.model.IconAndObjectSummary;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.utils.Utils;
import com.aliyun.oss.ossbrowser.view.FileListTablePanel;
import com.aliyun.oss.ossbrowser.view.FileMenuPanel;
import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;

// Referenced classes of package com.aliyun.oss.ossbrowser.control:
//            UploadFolderTask, UploadFileTask

public class UploadFolderRecursivelyTask
    implements Runnable
{

    public UploadFolderRecursivelyTask(ResourceManager resourceManager, String bucketName, String prefix, List fileList, boolean root)
    {
        this.resourceManager = resourceManager;
        this.bucketName = bucketName;
        this.prefix = prefix;
        this.fileList = fileList;
        this.root = root;
    }

    public void run()
    {
        if(prefix.length() <= 0 || prefix.endsWith("/")) goto _L2; else goto _L1
_L1:
        this;
        prefix;
        JVM INSTR new #53  <Class StringBuilder>;
        JVM INSTR dup_x1 ;
        JVM INSTR swap ;
        String.valueOf();
        StringBuilder();
        "/";
        append();
        toString();
        prefix;
_L2:
        if(root)
        {
            IconAndObjectSummary ic = IconAndObjectSummary.createLoadingItem(bucketName);
            resourceManager.getFileTableModel().removeAllRows(resourceManager.getFileListTable());
            resourceManager.getFileTableModel().addRow(new Object[] {
                ic
            });
            resourceManager.getFileListTablePanel().getFileMenu().setEnabled(false);
        }
        for(Iterator iterator = fileList.iterator(); iterator.hasNext();)
        {
            File f = (File)iterator.next();
            if(f.isDirectory())
            {
                List files = new ArrayList();
                files.addAll(Arrays.asList(f.listFiles()));
                String taskName = Utils.generateTaskName(1, f.getName(), 0L, bucketName, prefix, resourceManager);
                UploadFolderTask task = new UploadFolderTask(resourceManager, bucketName, prefix, f.getName(), taskName, root);
                resourceManager.getDataService().submit(task);
                (new UploadFolderRecursivelyTask(resourceManager, bucketName, (new StringBuilder(String.valueOf(prefix))).append(f.getName()).toString(), files, false)).run();
            } else
            {
                String taskName = Utils.generateTaskName(0, f.getAbsolutePath(), f.length(), bucketName, prefix, resourceManager);
                UploadFileTask task = new UploadFileTask(resourceManager, f, taskName, bucketName, prefix, root);
                resourceManager.getDataService().submit(task);
            }
        }

        return;
    }

    private ResourceManager resourceManager;
    private final String bucketName;
    private String prefix;
    private List fileList;
    private boolean root;
}
