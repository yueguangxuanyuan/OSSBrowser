// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileAccess.java

package com.aliyun.oss.ossbrowser.utils;

import com.aliyun.oss.ossbrowser.control.*;
import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

// Referenced classes of package com.aliyun.oss.ossbrowser.utils:
//            ResourceManager, Utils

public class FileAccess
{

    public FileAccess()
    {
    }

    public static boolean isFolder(String key)
    {
        return key.endsWith("/");
    }

    public static void uploadFiles(String bucket, String prefix, ResourceManager resourceManager)
    {
        fileChooser.setFileSelectionMode(0);
        fileChooser.setMultiSelectionEnabled(true);
        if(fileChooser.showOpenDialog(resourceManager.getMainFrame()) == 0)
            if(bucket != null)
            {
                List list = new ArrayList();
                File fs[] = fileChooser.getSelectedFiles();
                list.addAll(Arrays.asList(fs));
                List tasks = uploadTaskSplitor(bucket, prefix, resourceManager, list);
                UploadFileTask task;
                for(Iterator iterator = tasks.iterator(); iterator.hasNext(); resourceManager.getDataService().submit(task))
                    task = (UploadFileTask)iterator.next();

            } else
            {
                JOptionPane.showMessageDialog(resourceManager.getLoginDialog(), "Upload failed, Please select a bucket.");
            }
    }

    public static void uploadFolder(String bucket, String prefix, ResourceManager resourceManager)
    {
        if(bucket != null)
        {
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.setFileSelectionMode(1);
            if(fileChooser.showOpenDialog(resourceManager.getMainFrame()) == 0)
            {
                File f = fileChooser.getSelectedFile();
                List files = new ArrayList();
                files.add(f);
                UploadFolderRecursivelyTask task = new UploadFolderRecursivelyTask(resourceManager, bucket, prefix, files, true);
                resourceManager.getDataService().submit(task);
            }
        } else
        {
            JOptionPane.showMessageDialog(resourceManager.getLoginDialog(), "Upload failed, Please select a bucket.");
        }
    }

    public static void delete(String bucket, String key, ResourceManager resourceManager)
    {
        if(key == null || key.equals(""))
            return;
        int ret = JOptionPane.showConfirmDialog(resourceManager.getFileListTablePanel(), (new StringBuilder("\u786E\u5B9E\u8981\u5220\u9664\"")).append(key).append("\"\u5417?").toString(), "\u786E\u8BA4\u6587\u4EF6\u5220\u9664", 0);
        if(ret == 0)
        {
            DeleteFileTask task = new DeleteFileTask(resourceManager, bucket, key);
            resourceManager.getDataService().submit(task);
        }
    }

    public static void download(String bucket, String key, ResourceManager resourceManager)
    {
        if(key.equals(".."))
            return;
        if(key.endsWith("/"))
            fileChooser.setFileSelectionMode(1);
        else
            fileChooser.setFileSelectionMode(0);
        fileChooser.setSelectedFile(new File(Utils.getFilename(key)));
        if(fileChooser.showSaveDialog(resourceManager.getMainFrame()) == 0)
        {
            File saveAs = fileChooser.getSelectedFile();
            if(key.endsWith("/"))
            {
                int pos = key.lastIndexOf("/", key.length() - 2) + 1;
                String folderName = key.substring(pos);
                saveAs = new File((new StringBuilder(String.valueOf(saveAs.getAbsolutePath()))).append("/").append(folderName).toString());
            }
            DownloadTask task = new DownloadTask(resourceManager, bucket, key, saveAs);
            resourceManager.getService().submit(task);
        }
    }

    public static void createFolder(String bucket, String prefix, ResourceManager resourceManager)
    {
        if(bucket != null)
        {
            String folder = JOptionPane.showInputDialog("\u6587\u4EF6\u5939\u540D");
            if(folder == null)
                return;
            String taskName = Utils.generateTaskName(1, folder, 0L, bucket, prefix, resourceManager);
            UploadFolderTask task = new UploadFolderTask(resourceManager, bucket, prefix, folder, taskName);
            resourceManager.getService().submit(task);
        }
    }

    public static void refresh(String bucket, String prefix, ResourceManager resourceManager, boolean readCache)
    {
        if(bucket != null)
        {
            RefreshFileListTask task = new RefreshFileListTask(resourceManager, bucket, prefix, true, readCache);
            resourceManager.getService().submit(task);
        }
    }

    public static void back(String bucket, String prefix, ResourceManager resourceManager)
    {
        int i;
        for(i = prefix.length() - 1; prefix.charAt(i) == '/'; i--);
        if(i != -1)
        {
            i = prefix.substring(0, i).lastIndexOf("/");
            refresh(bucket, prefix.substring(0, i + 1), resourceManager, true);
        } else
        {
            refresh(bucket, "", resourceManager, true);
        }
    }

    private static List uploadTaskSplitor(String bucket, String prefix, ResourceManager resourceManager, List uploads)
    {
        List tasks = new ArrayList();
        List taskNames = new ArrayList();
        for(int i = 0; i < uploads.size(); i++)
        {
            String taskName = Utils.generateTaskName(0, ((File)uploads.get(i)).getAbsolutePath(), ((File)uploads.get(i)).length(), bucket, prefix, resourceManager);
            taskNames.add(taskName);
            UploadFileTask task = new UploadFileTask(resourceManager, (File)uploads.get(i), taskName, bucket, prefix);
            tasks.add(task);
        }

        return tasks;
    }

    private static JFileChooser fileChooser = new JFileChooser(".");

}
