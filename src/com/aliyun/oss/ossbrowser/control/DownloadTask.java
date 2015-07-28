// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DownloadTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.aos.services.oss.model.*;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.utils.Utils;
import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;

// Referenced classes of package com.aliyun.oss.ossbrowser.control:
//            DownloadFileTask

public class DownloadTask
    implements Runnable
{

    public DownloadTask(ResourceManager resourceManager, String bucketName, String key, File localFile)
    {
        this.resourceManager = resourceManager;
        this.bucketName = bucketName;
        this.key = key;
        this.localFile = localFile;
    }

    public void run()
    {
        List taskNames = new ArrayList();
        if(key.endsWith("/"))
        {
            localFile.mkdir();
            ListObjectsRequest request = new ListObjectsRequest(bucketName, key, "", null, Integer.valueOf(1000));
            ObjectListing objects = resourceManager.getOssClient().listObjects(request);
            do
            {
                for(Iterator iterator = objects.getObjectSummaries().iterator(); iterator.hasNext();)
                {
                    OSSObjectSummary object = (OSSObjectSummary)iterator.next();
                    String path = (new StringBuilder(String.valueOf(localFile.getAbsolutePath()))).append("/").append(object.getKey().substring(key.length())).toString();
                    File file = new File(path);
                    if(!path.endsWith("/"))
                    {
                        String taskName = Utils.generateTaskName(2, path, object.getSize(), bucketName, object.getKey(), resourceManager);
                        taskNames.add(taskName);
                        DownloadFileTask task = new DownloadFileTask(resourceManager, bucketName, object.getKey(), file, object.getSize(), taskName);
                        resourceManager.getDataService().submit(task);
                    } else
                    {
                        file.mkdirs();
                    }
                }

                if(!objects.isTruncated())
                    break;
                objects = resourceManager.getOssClient().listNextBatchOfObjects(objects);
            } while(true);
        } else
        {
            ObjectMetadata meta = resourceManager.getOssClient().getObjectMetadata(bucketName, key);
            String taskName = Utils.generateTaskName(2, localFile.getAbsolutePath(), meta.getContentLength(), bucketName, key, resourceManager);
            DownloadFileTask task = new DownloadFileTask(resourceManager, bucketName, key, localFile, meta.getContentLength(), taskName);
            resourceManager.getDataService().submit(task);
        }
    }

    private ResourceManager resourceManager;
    private String bucketName;
    private String key;
    private File localFile;
}
