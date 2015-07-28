// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoginTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.aos.AOSClientException;
import com.aliyun.aos.auth.BasicAOSCredentials;
import com.aliyun.aos.services.oss.OSSClientImpl;
import com.aliyun.aos.services.oss.model.*;
import com.aliyun.oss.ossbrowser.listener.ClientWindowListener;
import com.aliyun.oss.ossbrowser.model.*;
import com.aliyun.oss.ossbrowser.utils.*;
import com.aliyun.oss.ossbrowser.view.*;
import java.io.PrintStream;
import java.util.*;
import java.util.prefs.Preferences;
import javax.swing.*;

public class LoginTask
    implements Runnable
{

    public LoginTask(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    public void run()
    {
        LoginPanel loginPanel = resourceManager.getLoginDialog().getLoginPanel();
        String accessKey = loginPanel.getAccessKey().getText().trim();
        String accessID = loginPanel.getAccesssID().getText().trim();
        boolean rememberMe = loginPanel.isRememberMe();
        boolean inc = loginPanel.isInc();
        boolean assignHost = loginPanel.isAssignHost();
        Preferences prefs = loginPanel.getPreferences();
        try
        {
            OSSClientImpl client = new OSSClientImpl(new BasicAOSCredentials(accessID, accessKey));
            if(inc)
                client.setEndpoint("storage-vm.aliyun-inc.com");
            if(assignHost)
            {
                client.setEndpoint(loginPanel.getAssignHost());
                prefs.put("ASSIGN_HOST_STRING", loginPanel.getAssignHost());
            }
            prefs.putBoolean("LOGIN_REMEMBER_ME", rememberMe);
            prefs.putBoolean("LOGIN_INC", inc);
            prefs.putBoolean("ASSIGN_HOST", assignHost);
            prefs.put("LOGIN_ACCESS_ID", accessID);
            if(rememberMe)
                try
                {
                    prefs.put("LOGIN_ACCESS_KEY", RSAEncrypt.encrypt(accessKey));
                }
                catch(Exception e)
                {
                    prefs.putBoolean("LOGIN_REMEMBER_ME", false);
                }
            List buckets = client.listBuckets().getBuckets();
            int index = 0;
            Bucket bucket;
            for(Iterator iterator = buckets.iterator(); iterator.hasNext(); resourceManager.getBucketIndexMap().put(bucket.getName(), Integer.valueOf(index++)))
            {
                bucket = (Bucket)iterator.next();
                resourceManager.getBucketListModel().addElement(createBucketItem(bucket));
            }

            resourceManager.setOssClient(client);
            resourceManager.getMainFrame().addWindowListener(new ClientWindowListener(resourceManager.getMainFrame()));
            Preferences prefs1 = Preferences.userRoot().node((new StringBuilder("ossbrowser/upload")).append(accessID).toString());
            resourceManager.setUploadPerfs(prefs1);
            String keys[] = prefs1.childrenNames();
            String as[];
            int j = (as = keys).length;
            for(int i = 0; i < j; i++)
            {
                String key = as[i];
                UploadMeta meta = new UploadMeta();
                Utils.GetUploadMeta(prefs1, key, meta);
                if(Utils.CheckUploadMeta(meta))
                {
                    String taskName = Utils.generateTaskName(0, meta.getPath(), meta.getLength(), meta.getBucket(), Utils.getFilePrefix(meta.getObject()), resourceManager);
                    com.aliyun.oss.ossbrowser.model.IconTableModel model = resourceManager.getConsolePanel().getTasksPanel().getTableModel();
                    ProgressListenerImp progressListenerImp = new ProgressListenerImp(resourceManager, model, taskName, meta.getLength(), 0);
                    progressListenerImp.setCurSize((long)(meta.getCurrentPart() + 1) * meta.getBlockSize());
                    ProgressEvent progressEvent = new ProgressEvent(0);
                    progressEvent.setEventCode(4);
                    progressListenerImp.progressChanged(progressEvent);
                }
            }

            Map syncTaskMap = resourceManager.getSyncTaskMap();
            String local;
            for(Iterator iterator1 = syncTaskMap.keySet().iterator(); iterator1.hasNext(); (new Thread((Runnable)syncTaskMap.get(local))).start())
                local = (String)iterator1.next();

            resourceManager.getMainFrame().setVisible(true);
            resourceManager.getLoginDialog().setVisible(false);
            resourceManager.getLoginDialog().dispose();
        }
        catch(AOSClientException ex)
        {
            JOptionPane.showMessageDialog(resourceManager.getLoginDialog(), "Login failed, Please check Secret Key Pair.");
            resourceManager.getLoginDialog().getLoginPanel().getLoginButton().setEnabled(true);
        }
        catch(Exception ex)
        {
            System.err.println(ex.toString());
        }
    }

    private BucketIconListItem createBucketItem(Bucket bucket)
    {
        return new BucketIconListItem(ImageResource.bucketIcon, bucket);
    }

    private ResourceManager resourceManager;
}
