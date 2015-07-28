// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AddBucketTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.aos.AOSClientException;
import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.aos.services.oss.model.*;
import com.aliyun.oss.ossbrowser.model.*;
import com.aliyun.oss.ossbrowser.utils.*;
import com.aliyun.oss.ossbrowser.view.*;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

// Referenced classes of package com.aliyun.oss.ossbrowser.control:
//            RefreshBucketTask

public class AddBucketTask
    implements Runnable
{

    public AddBucketTask(ResourceManager manager, String bucketName, String bucketAcl)
    {
        this(manager, bucketName, bucketAcl, 1);
    }

    public AddBucketTask(ResourceManager manager, String bucketName, String bucketAcl, int retry)
    {
        resourceManager = manager;
        this.bucketName = bucketName;
        this.bucketAcl = bucketAcl;
        this.retry = retry;
    }

    public void run()
    {
        String log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Creating bucket ").append(bucketName).append("...").toString();
        resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
        synchronized(resourceManager.getFileTableModel())
        {
            try
            {
                addBucket();
                log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Bucket ").append(bucketName).append(" has been successfully created").toString();
                resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.INFO, log);
            }
            catch(IllegalArgumentException e)
            {
                JOptionPane.showMessageDialog(resourceManager.getMainFrame(), "Unsupported bucket name", "Error", 0);
                log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Bucket ").append(bucketName).append(" create failed, ").append(" Unsupported bucket name").toString();
                resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.ERROR, log);
            }
            catch(AOSClientException e)
            {
                JOptionPane.showMessageDialog(resourceManager.getMainFrame(), "Bucket create failed", "Error", 0);
                log = (new StringBuilder("[")).append(Utils.getFormatDate(new Date())).append("]").append(" Bucket ").append(bucketName).append(" create failed").toString();
                resourceManager.getConsolePanel().getLogPanel().addLog(LogLevel.ERROR, log);
            }
        }
    }

    private void addBucket()
    {
        for(int r = 0; r < retry; r++)
        {
            CreateBucketRequest request = new CreateBucketRequest(bucketName);
            request.setCannedAcl(CannedAccessControlList.valueOfAccessControlList(bucketAcl));
            try
            {
                Bucket b = resourceManager.getOssClient().createBucket(request);
                resourceManager.getCreateBucketDialog().setVisible(false);
                resourceManager.getBucketListModel().addElement(createBucketItem(b));
                resourceManager.getService().submit(new RefreshBucketTask(resourceManager));
            }
            catch(AOSClientException e)
            {
                if(r + 1 == retry)
                    throw e;
            }
        }

    }

    private BucketIconListItem createBucketItem(Bucket bucket)
    {
        return new BucketIconListItem(ImageResource.bucketIcon, bucket);
    }

    private String bucketName;
    private String bucketAcl;
    private ResourceManager resourceManager;
    private int retry;
}
