// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SyncUtils.java

package com.aliyun.oss.ossbrowser.utils;

import com.aliyun.oss.ossbrowser.control.SyncTask;
import com.aliyun.oss.ossbrowser.model.SyncList;
import com.aliyun.oss.ossbrowser.view.ConsolePanel;
import com.aliyun.oss.ossbrowser.view.SyncPanel;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

// Referenced classes of package com.aliyun.oss.ossbrowser.utils:
//            ResourceManager

public class SyncUtils
{

    public SyncUtils()
    {
    }

    public static void addSyncTask(String bucket, String key, String local, ResourceManager resourceManager)
    {
        String cloudPath = (new StringBuilder(String.valueOf(bucket))).append("/").append(key).toString();
        if(resourceManager.getSyncFileMap().containsKey(local))
        {
            JOptionPane.showMessageDialog(resourceManager.getMainFrame(), (new StringBuilder("\u672C\u5730\u6587\u4EF6\u5939:")).append(local).append("\u5DF2\u7ECF\u5728\u540C\u6B65\u5217\u8868\u4E2D\u5B58\u5728,").append("\u65E0\u6CD5\u6DFB\u52A0\u5230\u540C\u6B65\u5217\u8868\u4E2D!").toString(), "\u9519\u8BEF", 0);
            return;
        }
        if(resourceManager.getSyncFileMap().containsValue(cloudPath))
        {
            JOptionPane.showMessageDialog(resourceManager.getMainFrame(), (new StringBuilder("\u4E91\u7AEF\u6587\u4EF6\u5939:")).append(cloudPath).append("\u5DF2\u7ECF\u5728\u540C\u6B65\u5217\u8868\u4E2D\u5B58\u5728,").append("\u65E0\u6CD5\u6DFB\u52A0\u5230\u540C\u6B65\u5217\u8868\u4E2D!").toString(), "\u9519\u8BEF", 0);
            return;
        } else
        {
            SyncTask task = new SyncTask(bucket, key, local, resourceManager);
            (new Thread(task)).start();
            resourceManager.getSyncTaskMap().put(local, task);
            resourceManager.getSyncFileMap().put(local, cloudPath);
            resourceManager.getConsolePanel().getSyncPanel().getTableModel().addRow(new Object[] {
                local, cloudPath
            });
            saveSyncList(resourceManager.getSyncFileMap(), resourceManager.getSyncListFile());
            return;
        }
    }

    public static void saveSyncList(Map map, File file)
    {
        SyncList syncList;
        ObjectOutputStream out;
        syncList = new SyncList(map);
        out = null;
        if(!file.exists())
            file.createNewFile();
        out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(syncList);
        out.close();
        break MISSING_BLOCK_LABEL_103;
        FileNotFoundException e;
        e;
        e.printStackTrace();
        try
        {
            out.close();
        }
        catch(Exception exception1) { }
        break MISSING_BLOCK_LABEL_112;
        e;
        e.printStackTrace();
        try
        {
            out.close();
        }
        catch(Exception exception2) { }
        break MISSING_BLOCK_LABEL_112;
        Exception exception;
        exception;
        try
        {
            out.close();
        }
        catch(Exception exception3) { }
        throw exception;
        try
        {
            out.close();
        }
        catch(Exception exception4) { }
    }

    public static Map getSyncList(File file)
    {
        if(file.exists())
            try
            {
                ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
                SyncList syncList = (SyncList)oin.readObject();
                oin.close();
                return syncList.getMap();
            }
            catch(Exception e)
            {
                return new HashMap();
            }
        else
            return new HashMap();
    }
}
