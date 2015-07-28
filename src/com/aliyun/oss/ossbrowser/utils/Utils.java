// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Utils.java

package com.aliyun.oss.ossbrowser.utils;

import com.aliyun.aos.services.oss.model.Bucket;
import com.aliyun.aos.services.oss.model.OSSObjectSummary;
import com.aliyun.oss.ossbrowser.model.*;
import com.aliyun.oss.ossbrowser.view.ConsolePanel;
import com.aliyun.oss.ossbrowser.view.TasksPanel;
import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.*;

// Referenced classes of package com.aliyun.oss.ossbrowser.utils:
//            ResourceManager

public class Utils
{

    public Utils()
    {
    }

    public static String getFormatSize(double size)
    {
        double kiloByte = size / 1024D;
        if(kiloByte < 1.0D)
            return (new StringBuilder(String.valueOf(size))).append(" Byte(s)").toString();
        double megaByte = kiloByte / 1024D;
        if(megaByte < 1.0D)
        {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return (new StringBuilder(String.valueOf(result1.setScale(2, 4).toPlainString()))).append(" KB").toString();
        }
        double gigaByte = megaByte / 1024D;
        if(gigaByte < 1.0D)
        {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return (new StringBuilder(String.valueOf(result2.setScale(2, 4).toPlainString()))).append(" MB").toString();
        }
        double teraBytes = gigaByte / 1024D;
        if(teraBytes < 1.0D)
        {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return (new StringBuilder(String.valueOf(result3.setScale(2, 4).toPlainString()))).append(" GB").toString();
        } else
        {
            BigDecimal result4 = new BigDecimal(teraBytes);
            return (new StringBuilder(String.valueOf(result4.setScale(2, 4).toPlainString()))).append(" TB").toString();
        }
    }

    public static String getFormatDate(Date date)
    {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String getFilename(String path)
    {
        if(path.length() == 0)
            return path;
        int i = path.lastIndexOf('/', path.length() - 1);
        if(i != -1)
            return path.substring(i + 1);
        else
            return path;
    }

    public static String getFilePrefix(String name)
    {
        if(name.length() == 0)
            return name;
        int i = name.lastIndexOf('/', name.length() - 2);
        if(i != -1)
            return (new StringBuilder(String.valueOf(name.substring(0, i)))).append("/").toString();
        else
            return "";
    }

    public static String generateTaskName(int taskType, String fileName, long fileSize, String bucket, String object, ResourceManager resourceManager)
    {
        String taskName = getFormatDate(new Date());
        IconTableModel model = resourceManager.getConsolePanel().getTasksPanel().getTableModel();
        switch(taskType)
        {
        case 0: // '\0'
            taskName = (new StringBuilder(String.valueOf(taskName))).append("  \u4E0A\u4F20\u6587\u4EF6: ").toString();
            break;

        case 1: // '\001'
            taskName = (new StringBuilder(String.valueOf(taskName))).append("  \u521B\u5EFA\u6587\u4EF6\u5939: ").toString();
            break;

        case 2: // '\002'
            taskName = (new StringBuilder(String.valueOf(taskName))).append("  \u4E0B\u8F7D\u6587\u4EF6: ").toString();
            break;

        case 3: // '\003'
            taskName = (new StringBuilder(String.valueOf(taskName))).append("  \u540C\u6B65\u6587\u4EF6\u5230\u4E91\u7AEF: ").toString();
            break;

        case 4: // '\004'
            taskName = (new StringBuilder(String.valueOf(taskName))).append("  \u540C\u6B65\u6587\u4EF6\u5230\u672C\u5730: ").toString();
            break;
        }
        taskName = (new StringBuilder(String.valueOf(taskName))).append(fileName).toString();
        model.addRow(taskName, fileSize, bucket, object, fileName, taskType, resourceManager);
        return taskName;
    }

    public static Bucket GetCurrentBucket(ResourceManager resourceManager)
    {
        Bucket bucket = null;
        int index = resourceManager.getBucketList().getSelectedIndex();
        if(index != -1)
        {
            BucketIconListItem item = (BucketIconListItem)resourceManager.getBucketListModel().get(index);
            bucket = item.getBucket();
        }
        return bucket;
    }

    public static String getCurrentBucketName(ResourceManager resourceManager)
    {
        String bucket = "";
        JTable table = resourceManager.getFileListTable();
        int col = table.convertColumnIndexToView(0);
        if(resourceManager.getFileTableModel().getRowCount() != 0)
        {
            IconAndObjectSummary item = (IconAndObjectSummary)resourceManager.getFileListTable().getValueAt(0, col);
            bucket = item.getSummary().getBucketName();
        } else
        {
            int index = resourceManager.getBucketList().getSelectedIndex();
            if(index != -1)
            {
                BucketIconListItem item = (BucketIconListItem)resourceManager.getBucketListModel().get(index);
                bucket = item.getBucket().getName();
            }
        }
        return bucket;
    }

    public static String getCurrentObject(ResourceManager resourceManager)
    {
        String object = "";
        JTable table = resourceManager.getFileListTable();
        if(table.getRowCount() == 0)
            return object;
        int row = table.getSelectedRow();
        int col = table.convertColumnIndexToView(0);
        if(row != -1 && resourceManager.getFileTableModel().getRowCount() != 0 && resourceManager.getFileTableModel().getRowCount() >= row)
        {
            IconAndObjectSummary os = (IconAndObjectSummary)resourceManager.getFileListTable().getValueAt(row, col);
            object = os.getSummary().getKey();
        }
        return object;
    }

    public static String getCurrentPrefix(ResourceManager resourceManager)
    {
        String prefix = "";
        JTable table = resourceManager.getFileListTable();
        if(table.getRowCount() == 0)
            return prefix;
        int row = table.convertRowIndexToView(0);
        int col = table.convertColumnIndexToView(0);
        if(resourceManager.getFileTableModel().getRowCount() != 0)
        {
            IconAndObjectSummary item = (IconAndObjectSummary)table.getValueAt(row, col);
            String key = item.getSummary().getKey();
            if(key.equals("..") && item.getSummary().getETag() == null)
                prefix = item.getSummary().getType();
        }
        return prefix;
    }

    public static byte[] SerializeUploadMeta(UploadMeta meta)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(meta);
            return baos.toByteArray();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean DeserializeUploadMeta(byte bytes[], UploadMeta meta)
    {
        try
        {
            java.io.InputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream oin = new ObjectInputStream(in);
            meta.Copy((UploadMeta)oin.readObject());
            oin.close();
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    public static boolean CheckUploadMeta(UploadMeta meta)
    {
        File file = new File(meta.getPath());
        return file.exists() && meta.getPath().equals(file.getAbsolutePath()) && meta.getLastModify() == file.lastModified() && meta.getLength() == file.length();
    }

    private static byte[][] breakIntoPieces(byte raw[])
    {
        int pieceLength = 4096;
        int numPieces = ((raw.length + pieceLength) - 1) / pieceLength;
        byte pieces[][] = new byte[numPieces][];
        for(int i = 0; i < numPieces; i++)
        {
            int startByte = i * pieceLength;
            int endByte = startByte + pieceLength;
            if(endByte > raw.length)
                endByte = raw.length;
            int length = endByte - startByte;
            pieces[i] = new byte[length];
            System.arraycopy(raw, startByte, pieces[i], 0, length);
        }

        return pieces;
    }

    private static byte[] combinePieces(byte pieces[][])
    {
        int length = 0;
        for(int i = 0; i < pieces.length; i++)
            length += pieces[i].length;

        byte raw[] = new byte[length];
        int cursor = 0;
        for(int i = 0; i < pieces.length; i++)
        {
            System.arraycopy(pieces[i], 0, raw, cursor, pieces[i].length);
            cursor += pieces[i].length;
        }

        return raw;
    }

    public static String GetUploadMetaKey(String bucket, String object)
    {
        String str = (new StringBuilder(String.valueOf(bucket))).append("/").append(object).toString();
        return str.replace("+", "++").replace("/", "+");
    }

    public static void PutUploadMeta(Preferences rootPrefs, String key, UploadMeta meta)
    {
        byte seBytes[] = SerializeUploadMeta(meta);
        byte bytess[][] = breakIntoPieces(seBytes);
        Preferences prefs = rootPrefs.node(key);
        for(int i = 0; i < bytess.length; i++)
            prefs.putByteArray((new StringBuilder()).append(i).toString(), bytess[i]);

    }

    public static void GetUploadMeta(Preferences rootPrefs, String key, UploadMeta meta)
    {
        Preferences prefs = rootPrefs.node(key);
        try
        {
            int num = prefs.keys().length;
            byte bytess[][] = new byte[num][];
            for(int i = 0; i < num; i++)
                bytess[i] = prefs.getByteArray((new StringBuilder()).append(i).toString(), null);

            byte bytes[] = combinePieces(bytess);
            DeserializeUploadMeta(bytes, meta);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void RemoveUploadMeta(Preferences rootPrefs, String key)
    {
        try
        {
            rootPrefs.node(key).removeNode();
        }
        catch(BackingStoreException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String args1[])
    {
    }

    public static final String LOGIN_REMEMBER_ME = "LOGIN_REMEMBER_ME";
    public static final String LOGIN_ACCESS_ID = "LOGIN_ACCESS_ID";
    public static final String LOGIN_ACCESS_KEY = "LOGIN_ACCESS_KEY";
    public static final String LOGIN_INC = "LOGIN_INC";
    public static final String ASSIGN_HOST = "ASSIGN_HOST";
    public static final String ASSIGN_HOST_STRING = "ASSIGN_HOST_STRING";
    public static final int UPLOAD_FILE = 0;
    public static final int CREATE_FOLDER = 1;
    public static final int DOWNLOAD_FILE = 2;
    public static final int SYNC_UPLOAD_FILE = 3;
    public static final int SYNC_DOWNLOAD_FILE = 4;
}
