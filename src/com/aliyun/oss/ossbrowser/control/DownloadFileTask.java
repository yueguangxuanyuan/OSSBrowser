// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DownloadFileTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.aos.services.oss.internal.ProgressReportingInputStream;
import com.aliyun.aos.services.oss.model.*;
import com.aliyun.oss.ossbrowser.model.*;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.utils.Utils;
import com.aliyun.oss.ossbrowser.view.*;
import java.io.*;
import java.util.Date;

public class DownloadFileTask
    implements Runnable
{

    public DownloadFileTask(ResourceManager resourceManager, String bucketName, String key, File localFile, long fileSize, String taskName)
    {
        this(resourceManager, bucketName, key, localFile, fileSize, taskName, 5);
    }

    public DownloadFileTask(ResourceManager resourceManager, String bucketName, String key, File localFile, long fileSize, String taskName, 
            int retry)
    {
        this.resourceManager = resourceManager;
        this.bucketName = bucketName;
        this.key = key;
        this.localFile = localFile;
        this.fileSize = fileSize;
        this.taskName = taskName;
        this.retry = retry;
        eTag = "";
    }

    public Boolean call()
    {
        String log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Downloading file ").append(key).append("...").toString();
        resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
        if(download())
        {
            log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" file ").append(key).append(" has been successfully downloaded").toString();
            resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
            return Boolean.valueOf(true);
        } else
        {
            log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" file ").append(key).append(" download failed").toString();
            resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.ERROR, log);
            return Boolean.valueOf(false);
        }
    }

    public void run()
    {
        call();
    }

    private boolean download()
    {
        ProgressListenerImp progressListener;
        int r;
        IconTableModel model = resourceManager.getConsolePanel().getTasksPanel().getTableModel();
        progressListener = new ProgressListenerImp(resourceManager, model, taskName, fileSize, 2);
        r = 0;
          goto _L1
_L3:
        GetObjectRequest request = new GetObjectRequest(bucketName, key);
        OSSObject object = resourceManager.getOssClient().getObject(request);
        progressListener.setCurSize(0L);
        ProgressReportingInputStream progressInputStream = new ProgressReportingInputStream(object.getObjectContent(), progressListener);
        if(!inputStreamToFile(progressInputStream, localFile, progressListener))
            continue; /* Loop/switch isn't completed */
        eTag = object.getObjectMetadata().getETag();
        return true;
        Exception exception;
        exception;
        r++;
_L1:
        if(r < retry) goto _L3; else goto _L2
_L2:
        ProgressEvent progressEvent = new ProgressEvent(0);
        progressEvent.setEventCode(4);
        progressListener.progressChanged(progressEvent);
        return false;
    }

    private boolean inputStreamToFile(ProgressReportingInputStream in, File file, ProgressListener listener)
    {
        OutputStream outputStream;
        boolean success;
        ProgressEvent event;
        outputStream = null;
        success = true;
        event = new ProgressEvent(0);
        event.setEventCode(1);
        listener.progressChanged(event);
        event.setEventCode(0);
        file.getParentFile().mkdirs();
        try
        {
            outputStream = new BufferedOutputStream(new FileOutputStream(file));
            byte buffer[] = new byte[10240];
            int bytesRead;
            while((bytesRead = in.read(buffer)) > -1) 
                outputStream.write(buffer, 0, bytesRead);
            event.setEventCode(2);
            listener.progressChanged(event);
            break MISSING_BLOCK_LABEL_162;
        }
        catch(Exception e)
        {
            success = false;
        }
        try
        {
            outputStream.close();
        }
        catch(Exception exception1) { }
        try
        {
            in.close();
        }
        catch(Exception exception2) { }
        break MISSING_BLOCK_LABEL_181;
        Exception exception;
        exception;
        try
        {
            outputStream.close();
        }
        catch(Exception exception3) { }
        try
        {
            in.close();
        }
        catch(Exception exception4) { }
        throw exception;
        try
        {
            outputStream.close();
        }
        catch(Exception exception5) { }
        try
        {
            in.close();
        }
        catch(Exception exception6) { }
        return success;
    }

    public String getETag()
    {
        return eTag;
    }

    private ResourceManager resourceManager;
    private String bucketName;
    private String key;
    private File localFile;
    private String taskName;
    private long fileSize;
    private int retry;
    private String eTag;
}
