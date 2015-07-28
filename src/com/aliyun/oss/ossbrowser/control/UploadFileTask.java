// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UploadFileTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.aos.services.oss.internal.ProgressReportingInputStream;
import com.aliyun.aos.services.oss.model.*;
import com.aliyun.oss.ossbrowser.model.*;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.utils.Utils;
import com.aliyun.oss.ossbrowser.view.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.prefs.Preferences;

// Referenced classes of package com.aliyun.oss.ossbrowser.control:
//            RefreshFileListTask

public class UploadFileTask
    implements Runnable
{

    public UploadFileTask(ResourceManager resourceManager, File file, String taskName, String bucketName, String prefix)
    {
        this(resourceManager, file, taskName, bucketName, prefix, true);
    }

    public UploadFileTask(ResourceManager resourceManager, File file, String taskName, String bucketName, String prefix, boolean fresh)
    {
        this(resourceManager, file, taskName, bucketName, prefix, fresh, 5);
    }

    public UploadFileTask(ResourceManager resourceManager, File file, String taskName, String bucketName, String prefix, boolean fresh, int retry)
    {
        this(resourceManager, file, taskName, bucketName, prefix, fresh, 5, false);
    }

    public UploadFileTask(ResourceManager resourceManager, File file, String taskName, String bucketName, String prefix, boolean fresh, int retry, 
            boolean reUpload)
    {
        needFresh = true;
        this.reUpload = false;
        this.file = file;
        this.taskName = taskName;
        this.resourceManager = resourceManager;
        this.bucketName = bucketName;
        this.prefix = prefix;
        needFresh = fresh;
        this.reUpload = reUpload;
        this.retry = retry;
        eTag = "";
    }

    public Boolean call()
    {
        if(prefix.length() == 0 || prefix.endsWith("/")) goto _L2; else goto _L1
_L1:
        this;
        prefix;
        JVM INSTR new #80  <Class StringBuilder>;
        JVM INSTR dup_x1 ;
        JVM INSTR swap ;
        String.valueOf();
        StringBuilder();
        "/";
        append();
        toString();
        prefix;
_L2:
        String log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Uploading file ").append(file.getAbsolutePath()).append("...").toString();
        resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
        String filePath = file.getName();
        String key = (new StringBuilder(String.valueOf(prefix))).append(filePath).toString();
        IconTableModel model = resourceManager.getConsolePanel().getTasksPanel().getTableModel();
        ProgressListenerImp progressListenerImp = new ProgressListenerImp(resourceManager, model, taskName, file.length(), 0);
        boolean inc = resourceManager.getLoginDialog().getLoginPanel().getPreferences().getBoolean("LOGIN_INC", false);
        int k = inc ? 10 : 1;
        boolean success;
        if(file.length() < (long)(0xa00000 * k))
        {
            success = upload(key, progressListenerImp, retry).booleanValue();
        } else
        {
            long blockSize = 0x100000 * k;
            if(file.length() / blockSize > 9000L)
                blockSize = file.length() / 9000L;
            success = multiPartUpload(key, blockSize, progressListenerImp, retry).booleanValue();
        }
        if(success)
        {
            log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" file ").append(file.getAbsolutePath()).append(" has been successfully uploaded").toString();
            resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
        } else
        {
            log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" upload file ").append(file.getAbsolutePath()).append(" failed").toString();
            resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.ERROR, log);
        }
        if(needFresh)
            (new RefreshFileListTask(resourceManager, bucketName, prefix, false, false)).run();
        return Boolean.valueOf(success);
    }

    public void run()
    {
        call();
    }

    private Boolean upload(String key, ProgressListenerImp progressListenerImp, int retry)
    {
        for(int r = 0; r < retry;)
        {
            PutObjectRequest request = new PutObjectRequest(bucketName, key, file);
            progressListenerImp.setCurSize(0L);
            request.setProgressListener(progressListenerImp);
            try
            {
                PutObjectResult result = resourceManager.getOssClient().putObject(request);
                eTag = result.getETag();
                return Boolean.valueOf(true);
            }
            catch(Exception exception)
            {
                r++;
            }
        }

        ProgressEvent progressEvent = new ProgressEvent(0);
        progressEvent.setEventCode(4);
        progressListenerImp.progressChanged(progressEvent);
        return Boolean.valueOf(false);
    }

    private boolean GetUploadMeta(String bucket, String key, UploadMeta meta)
    {
        Preferences prefs = resourceManager.getUploadPerfs();
        Utils.GetUploadMeta(prefs, Utils.GetUploadMetaKey(bucket, key), meta);
        return Utils.CheckUploadMeta(meta);
    }

    private Boolean multiPartUpload(String key, long blockSize, ProgressListenerImp progressListenerImp, int retry)
    {
        int i = 0;
        int r = 0;
        ArrayList etags = new ArrayList();
        String uploadId = "";
        UploadMeta meta = new UploadMeta();
        if(reUpload && GetUploadMeta(bucketName, key, meta))
        {
            uploadId = meta.getUploadId();
            etags = meta.getEtags();
            blockSize = meta.getBlockSize();
            i = meta.getCurrentPart() + 1;
            progressListenerImp.setCurSize((long)i * blockSize);
        } else
        {
            InitiateMultipartUploadRequest req = new InitiateMultipartUploadRequest(bucketName, key);
            InitiateMultipartUploadResult res = resourceManager.getOssClient().initiateMultipartUpload(req);
            uploadId = res.getUploadId();
        }
        meta.setBlockSize(blockSize);
        meta.setCurrentPart(i);
        meta.setEtags(etags);
        meta.setLastModify(file.lastModified());
        meta.setLength(file.length());
        meta.setPath(file.getAbsolutePath());
        meta.setUploadId(uploadId);
        meta.setBucket(bucketName);
        meta.setObject(key);
        for(long partNum = file.length() / blockSize + 1L; (long)i < partNum; i++)
        {
            UploadPartRequest upRequest = new UploadPartRequest();
            long partSize = (long)(i + 1) != partNum ? blockSize : file.length() % blockSize;
            upRequest.setBucketName(bucketName);
            upRequest.setKey(key);
            upRequest.setPartNumber(i + 1);
            upRequest.setUploadId(uploadId);
            upRequest.setPartSize(partSize);
            for(r = 0; r < retry;)
                try
                {
                    InputStream in = new FileInputStream(file);
                    in.skip((long)i * blockSize);
                    ProgressReportingInputStream progressInputStream = new ProgressReportingInputStream(in, progressListenerImp);
                    upRequest.setInputStream(progressInputStream);
                    UploadPartResult uploadPartResult = resourceManager.getOssClient().uploadPart(upRequest);
                    etags.add(uploadPartResult.getPartETag());
                    meta.setEtags(etags);
                    meta.setCurrentPart(i);
                    Utils.PutUploadMeta(resourceManager.getUploadPerfs(), Utils.GetUploadMetaKey(bucketName, key), meta);
                    break;
                }
                catch(Exception e)
                {
                    progressListenerImp.setCurSize((long)i * blockSize);
                    e.printStackTrace();
                    r++;
                }

            if(r == retry)
            {
                ProgressEvent progressEvent = new ProgressEvent(0);
                progressEvent.setEventCode(4);
                progressListenerImp.progressChanged(progressEvent);
                return Boolean.valueOf(false);
            }
        }

        CompleteMultipartUploadResult result = null;
        for(r = 0; r < retry;)
            try
            {
                CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(bucketName, key, uploadId, etags);
                result = resourceManager.getOssClient().completeMultipartUpload(completeMultipartUploadRequest);
                Utils.RemoveUploadMeta(resourceManager.getUploadPerfs(), Utils.GetUploadMetaKey(bucketName, key));
                break;
            }
            catch(Exception exception)
            {
                r++;
            }

        if(r == retry)
        {
            ProgressEvent progressEvent = new ProgressEvent(0);
            progressEvent.setEventCode(4);
            progressListenerImp.progressChanged(progressEvent);
            return Boolean.valueOf(false);
        } else
        {
            ProgressEvent progressEvent = new ProgressEvent(0);
            progressEvent.setEventCode(2);
            progressListenerImp.progressChanged(progressEvent);
            eTag = result.getETag();
            return Boolean.valueOf(true);
        }
    }

    public String getETag()
    {
        return eTag;
    }

    private ResourceManager resourceManager;
    private File file;
    private String taskName;
    private final String bucketName;
    private String prefix;
    private boolean needFresh;
    private boolean reUpload;
    private String eTag;
    private int retry;
    private static final int MULTI_PART_UPLOAD_SIZE = 0xa00000;
    private static final int PART_SIZE = 0x100000;
}
