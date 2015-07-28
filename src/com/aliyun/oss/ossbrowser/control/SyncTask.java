// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SyncTask.java

package com.aliyun.oss.ossbrowser.control;

import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.aos.services.oss.model.*;
import com.aliyun.oss.ossbrowser.model.FileMeta;
import com.aliyun.oss.ossbrowser.model.SyncPoint;
import com.aliyun.oss.ossbrowser.utils.*;
import java.io.*;
import java.util.*;

// Referenced classes of package com.aliyun.oss.ossbrowser.control:
//            DeleteFileTask, UploadFileTask, UploadFolderTask, DownloadFileTask

public class SyncTask
    implements Runnable
{

    public SyncTask(String bucket, String prefix, String local, ResourceManager resourceManager)
    {
        stop = false;
        this.bucket = bucket;
        this.resourceManager = resourceManager;
        if(!prefix.endsWith("/"))
            this.prefix = (new StringBuilder(String.valueOf(prefix))).append("/").toString();
        else
            this.prefix = prefix;
        local = local.replace("\\", "/");
        if(!local.endsWith("/"))
            this.local = (new StringBuilder(String.valueOf(local))).append("/").toString();
        else
            this.local = local;
        syncPointPath = (new StringBuilder(String.valueOf(this.local))).append("OssSyncPoint.osp").toString();
        syncPointFile = new File(syncPointPath);
    }

    public void run()
    {
        Map currentFileMap = new HashMap();
        long lastDownloadTime = 0L;
        File file = new File(local);
        Map oldFileMap = getFileMap();
        while(!stop) 
        {
            try
            {
                Thread.sleep(5000L);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            currentFileMap.clear();
            getCurrentFileMap(file, currentFileMap);
            deleteFile(oldFileMap, currentFileMap);
            uploadFile(oldFileMap, currentFileMap);
            Date date = new Date();
            if(date.getTime() / 1000L - lastDownloadTime > 30L)
            {
                lastDownloadTime = date.getTime() / 1000L;
                downloadFile(oldFileMap);
            }
        }
    }

    public void stop()
    {
        stop = true;
    }

    private Map getFileMap()
    {
        if(!syncPointFile.exists())
            break MISSING_BLOCK_LABEL_157;
        try
        {
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(syncPointFile));
            SyncPoint syncPoint = (SyncPoint)oin.readObject();
            oin.close();
            if((new StringBuilder(String.valueOf(bucket))).append(prefix).toString().equals(((FileMeta)syncPoint.getMap().get("!C@L#O$U%D^P&A*T(H)")).getETag()))
                return syncPoint.getMap();
        }
        catch(Exception e)
        {
            Map map = new HashMap();
            map.put("!C@L#O$U%D^P&A*T(H)", new FileMeta(Long.valueOf(0L), (new StringBuilder(String.valueOf(bucket))).append(prefix).toString(), true));
            return map;
        }
        return new HashMap();
        Map map = new HashMap();
        map.put("!C@L#O$U%D^P&A*T(H)", new FileMeta(Long.valueOf(0L), (new StringBuilder(String.valueOf(bucket))).append(prefix).toString(), true));
        return map;
    }

    private void saveFileMap(Map fileMap)
    {
        SyncPoint syncPoint;
        ObjectOutputStream out;
        fileMap.put("!C@L#O$U%D^P&A*T(H)", new FileMeta(Long.valueOf(0L), (new StringBuilder(String.valueOf(bucket))).append(prefix).toString(), true));
        syncPoint = new SyncPoint();
        syncPoint.setMap(fileMap);
        out = null;
        if(!syncPointFile.exists())
        {
            syncPointFile.getParentFile().mkdirs();
            syncPointFile.createNewFile();
        }
        out = new ObjectOutputStream(new FileOutputStream(syncPointFile));
        out.writeObject(syncPoint);
        out.close();
        break MISSING_BLOCK_LABEL_172;
        FileNotFoundException e;
        e;
        e.printStackTrace();
        try
        {
            out.close();
        }
        catch(Exception exception1) { }
        break MISSING_BLOCK_LABEL_181;
        e;
        e.printStackTrace();
        try
        {
            out.close();
        }
        catch(Exception exception2) { }
        break MISSING_BLOCK_LABEL_181;
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

    private void getCurrentFileMap(File file, Map fileMap)
    {
        fileMap.put("!C@L#O$U%D^P&A*T(H)", new FileMeta(Long.valueOf(0L), (new StringBuilder(String.valueOf(bucket))).append(prefix).toString(), true));
        String fileList[] = file.list();
        if(fileList != null)
        {
            for(int i = 0; i < fileList.length; i++)
            {
                String pname = (new StringBuilder(String.valueOf(file.getAbsolutePath().replace("\\", "/")))).append("/").append(fileList[i]).toString();
                File tempFile = new File(pname);
                if(tempFile.isDirectory())
                {
                    fileMap.put((new StringBuilder(String.valueOf(pname))).append("/").toString(), new FileMeta(Long.valueOf(tempFile.lastModified()), "", false));
                    getCurrentFileMap(tempFile, fileMap);
                } else
                {
                    fileMap.put(pname, new FileMeta(Long.valueOf(tempFile.lastModified()), "", false));
                }
            }

        }
    }

    private void deleteFile(Map oldFileMap, Map currentFileMap)
    {
        Map dirs = new HashMap();
        for(Iterator it = oldFileMap.entrySet().iterator(); it.hasNext();)
        {
            if(stop)
                return;
            String key = (String)((java.util.Map.Entry)it.next()).getKey();
            if(!currentFileMap.containsKey(key))
            {
                String object = convertToCloudPath(key);
                if(FileAccess.isFolder(object))
                {
                    dirs.put(object, key);
                } else
                {
                    DeleteFileTask task = new DeleteFileTask(resourceManager, bucket, object, false, 10);
                    if(task.call().booleanValue())
                    {
                        it.remove();
                        saveFileMap(oldFileMap);
                    }
                }
            }
        }

        for(Iterator iterator = dirs.keySet().iterator(); iterator.hasNext();)
        {
            String dir = (String)iterator.next();
            if(stop)
                return;
            DeleteFileTask task = new DeleteFileTask(resourceManager, bucket, dir, false, 10);
            if(task.call().booleanValue())
            {
                oldFileMap.remove(dirs.get(dir));
                saveFileMap(oldFileMap);
            }
        }

    }

    private void uploadFile(Map oldFileMap, Map currentFileMap)
    {
        Set keySet = currentFileMap.keySet();
        for(Iterator iterator = keySet.iterator(); iterator.hasNext();)
        {
            String key = (String)iterator.next();
            if(stop)
                return;
            if(!key.equals(syncPointPath) && (!oldFileMap.containsKey(key) || !((FileMeta)oldFileMap.get(key)).isIgnoreUpload() && !((FileMeta)oldFileMap.get(key)).getLastModify().equals(((FileMeta)currentFileMap.get(key)).getLastModify())))
            {
                File file = new File(key);
                if(file.isFile())
                {
                    String taskName = Utils.generateTaskName(3, key, file.length(), bucket, prefix, resourceManager);
                    UploadFileTask task = new UploadFileTask(resourceManager, file, taskName, bucket, convertToCloudPrefix(key), false, 10);
                    if(task.call().booleanValue())
                    {
                        oldFileMap.put(key, new FileMeta(Long.valueOf(file.lastModified()), task.getETag(), false));
                        saveFileMap(oldFileMap);
                    }
                } else
                {
                    String taskName = Utils.generateTaskName(1, file.getName(), 0L, bucket, key, resourceManager);
                    String folderName = key.substring(local.length());
                    UploadFolderTask task = new UploadFolderTask(resourceManager, bucket, prefix, folderName, taskName, false, 10);
                    if(task.call().booleanValue())
                    {
                        oldFileMap.put(key, new FileMeta(Long.valueOf(file.lastModified()), "", false));
                        saveFileMap(oldFileMap);
                    }
                }
            }
        }

    }

    private void downloadFile(Map oldFileMap)
    {
        ListObjectsRequest request = new ListObjectsRequest(bucket, prefix, "", null, Integer.valueOf(1000));
        ObjectListing list;
        list = resourceManager.getOssClient().listObjects(request);
        downloadByListing(oldFileMap, list);
          goto _L1
_L3:
        if(stop)
            return;
        list = resourceManager.getOssClient().listNextBatchOfObjects(list);
        downloadByListing(oldFileMap, list);
_L1:
        if(list.isTruncated()) goto _L3; else goto _L2
_L2:
        break MISSING_BLOCK_LABEL_88;
        Exception exception;
        exception;
    }

    private void downloadByListing(Map oldFileMap, ObjectListing list)
    {
        for(Iterator iterator = list.getObjectSummaries().iterator(); iterator.hasNext();)
        {
            OSSObjectSummary summary = (OSSObjectSummary)iterator.next();
            if(stop)
                return;
            String key = summary.getKey();
            String path = convertToLocalPath(key);
            File file = new File(path);
            if(!path.equals(local) && !path.equals(syncPointPath) && (!oldFileMap.containsKey(path) || !((FileMeta)oldFileMap.get(path)).getETag().equals(summary.getETag()) || !file.exists() || file.length() != summary.getSize()))
                if(FileAccess.isFolder(key))
                {
                    file.mkdirs();
                    oldFileMap.put(path, new FileMeta(Long.valueOf(file.lastModified()), "", false));
                    saveFileMap(oldFileMap);
                } else
                {
                    file.getParentFile().mkdirs();
                    long size = summary.getSize();
                    String taskName = Utils.generateTaskName(4, file.getAbsolutePath(), size, bucket, key, resourceManager);
                    DownloadFileTask task = new DownloadFileTask(resourceManager, bucket, key, file, size, taskName);
                    oldFileMap.put(path, new FileMeta(Long.valueOf(file.lastModified()), task.getETag(), true));
                    saveFileMap(oldFileMap);
                    if(task.call().booleanValue())
                    {
                        oldFileMap.put(path, new FileMeta(Long.valueOf(file.lastModified()), task.getETag(), false));
                        saveFileMap(oldFileMap);
                    }
                }
        }

    }

    private String convertToLocalPath(String object)
    {
        return (new StringBuilder(String.valueOf(local))).append(object.substring(prefix.length())).toString();
    }

    private String convertToCloudPrefix(String file)
    {
        String path = convertToCloudPath(file);
        return path.substring(0, path.lastIndexOf("/") + 1);
    }

    private String convertToCloudPath(String file)
    {
        return (new StringBuilder(String.valueOf(prefix))).append(file.substring(local.length())).toString();
    }

    private static final int LOCAL_SCAN_INTERVAL = 5;
    private static final int CLOUD_SCAN_INTERVAL = 30;
    private static final int FAIL_RETRY = 10;
    private static final String SYNC_CLOUD_PATH_HARDCODE = "!C@L#O$U%D^P&A*T(H)";
    private String syncPointPath;
    private File syncPointFile;
    private boolean stop;
    private ResourceManager resourceManager;
    private String bucket;
    private String prefix;
    private String local;
}
