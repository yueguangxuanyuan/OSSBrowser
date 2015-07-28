// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UploadFolderTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.aos.services.oss.internal.StringInputStream;
import com.aliyun.aos.services.oss.model.PutObjectRequest;
import com.aliyun.oss.ossbrowser.model.LogLevel;
import com.aliyun.oss.ossbrowser.model.ProgressListenerImp;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.utils.Utils;
import com.aliyun.oss.ossbrowser.view.*;
import java.util.Date;

// Referenced classes of package com.aliyun.oss.ossbrowser.control:
//            RefreshFileListTask

public class UploadFolderTask
    implements Runnable
{

    public UploadFolderTask(ResourceManager resourceManager, String bucketName, String prefix, String folderName, String taskName)
    {
        this(resourceManager, bucketName, prefix, folderName, taskName, true);
    }

    public UploadFolderTask(ResourceManager resourceManager, String bucketName, String prefix, String folderName, String taskName, boolean fresh)
    {
        this(resourceManager, bucketName, prefix, folderName, taskName, fresh, 5);
    }

    public UploadFolderTask(ResourceManager resourceManager, String bucketName, String prefix, String folderName, String taskName, boolean fresh, int retry)
    {
        this.resourceManager = resourceManager;
        this.bucketName = bucketName;
        this.prefix = prefix;
        this.folderName = folderName;
        this.taskName = taskName;
        needFresh = fresh;
        this.retry = retry;
        if(prefix.length() > 0 && !prefix.endsWith("/"))
            this.prefix = (new StringBuilder(String.valueOf(prefix))).append("/").toString();
        if(!folderName.endsWith("/"))
            this.folderName = (new StringBuilder(String.valueOf(folderName))).append("/").toString();
    }

    public void run()
    {
        call();
    }

    public Boolean call()
    {
        String log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Uploading folder ").append(prefix).append(folderName).append("...").toString();
        resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
        if(uploadFoloder(retry))
        {
            log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" folder ").append(prefix).append(folderName).append("has been successfully uploaded").toString();
            resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
            if(needFresh)
                (new RefreshFileListTask(resourceManager, bucketName, prefix, true, false)).run();
            return Boolean.valueOf(true);
        } else
        {
            log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" upload folder ").append(prefix).append(folderName).append("failed").toString();
            resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.ERROR, log);
            return Boolean.valueOf(false);
        }
    }

    private boolean uploadFoloder(int retry)
    {
        int r = 0;
          goto _L1
_L3:
        com.aliyun.oss.ossbrowser.model.IconTableModel model = resourceManager.getConsolePanel().getTasksPanel().getTableModel();
        PutObjectRequest req = new PutObjectRequest(bucketName, (new StringBuilder(String.valueOf(prefix))).append(folderName).toString(), new StringInputStream(""), null);
        req.setProgressListener(new ProgressListenerImp(resourceManager, model, taskName, "".length(), 1));
        resourceManager.getOssClient().putObject(req);
        return true;
        Exception exception;
        exception;
        r++;
_L1:
        if(r < retry) goto _L3; else goto _L2
_L2:
        return false;
    }

    private ResourceManager resourceManager;
    private final String bucketName;
    private String folderName;
    private String prefix;
    private String taskName;
    private boolean needFresh;
    private int retry;
}
