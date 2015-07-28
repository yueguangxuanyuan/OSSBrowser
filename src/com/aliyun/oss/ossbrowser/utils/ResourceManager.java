// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResourceManager.java

package com.aliyun.oss.ossbrowser.utils;

import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.oss.ossbrowser.model.FileTableModel;
import com.aliyun.oss.ossbrowser.view.*;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.prefs.Preferences;
import javax.swing.*;

public class ResourceManager
{

    public ResourceManager()
    {
    }

    public JFrame getMainFrame()
    {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame)
    {
        this.mainFrame = mainFrame;
    }

    public IOSSClient getOssClient()
    {
        return ossClient;
    }

    public void setOssClient(IOSSClient ossClient)
    {
        this.ossClient = ossClient;
    }

    public DefaultListModel getBucketListModel()
    {
        return bucketListModel;
    }

    public void setBucketListModel(DefaultListModel bucketListModel)
    {
        this.bucketListModel = bucketListModel;
    }

    public ExecutorService getService()
    {
        return service;
    }

    public void setService(ExecutorService service)
    {
        this.service = service;
    }

    public CreateBucketDialog getCreateBucketDialog()
    {
        return createBucketDialog;
    }

    public void setCreateBucketDialog(CreateBucketDialog createBucketDialog)
    {
        this.createBucketDialog = createBucketDialog;
    }

    public JList getBucketList()
    {
        return bucketList;
    }

    public void setBucketList(JList bucketList)
    {
        this.bucketList = bucketList;
    }

    public JTextField getBucketNameText()
    {
        return bucketNameText;
    }

    public void setBucketNameText(JTextField bucketNameText)
    {
        this.bucketNameText = bucketNameText;
    }

    public JComboBox getBucketAclComboBox()
    {
        return bucketAclComboBox;
    }

    public void setBucketAclComboBox(JComboBox bucketAclComboBox)
    {
        this.bucketAclComboBox = bucketAclComboBox;
    }

    public JTable getFileListTable()
    {
        return fileListTable;
    }

    public void setFileListTable(JTable fileListTable)
    {
        this.fileListTable = fileListTable;
    }

    public FileTableModel getFileTableModel()
    {
        return fileTableModel;
    }

    public void setFileTableModel(FileTableModel fileTableModel)
    {
        this.fileTableModel = fileTableModel;
    }

    public FileListTablePanel getFileListTablePanel()
    {
        return fileListTablePanel;
    }

    public void setFileListTablePanel(FileListTablePanel fileListTablePanel)
    {
        this.fileListTablePanel = fileListTablePanel;
    }

    public LoginDialog getLoginDialog()
    {
        return loginDialog;
    }

    public void setLoginDialog(LoginDialog loginDialog)
    {
        this.loginDialog = loginDialog;
    }

    public ConsolePanel getConsolePanel()
    {
        return consolePanel;
    }

    public void setConsolePanel(ConsolePanel consolePanel)
    {
        this.consolePanel = consolePanel;
    }

    public ExecutorService getDataService()
    {
        return dataService;
    }

    public void setDataService(ExecutorService dataService)
    {
        this.dataService = dataService;
    }

    public ExecutorService getSyncService()
    {
        return syncService;
    }

    public void setSyncService(ExecutorService syncService)
    {
        this.syncService = syncService;
    }

    public Map getBucketIndexMap()
    {
        return bucketIndexMap;
    }

    public void setBucketIndexMap(Map bucketIndexMap)
    {
        this.bucketIndexMap = bucketIndexMap;
    }

    public Map getSyncFileMap()
    {
        return syncFileMap;
    }

    public void setSyncFileMap(Map syncFileMap)
    {
        this.syncFileMap = syncFileMap;
    }

    public Map getSyncTaskMap()
    {
        return syncTaskMap;
    }

    public void setSyncTaskMap(Map syncTaskMap)
    {
        this.syncTaskMap = syncTaskMap;
    }

    public File getSyncListFile()
    {
        return SyncListFile;
    }

    public void setSyncListFile(File syncListFile)
    {
        SyncListFile = syncListFile;
    }

    public Preferences getUploadPerfs()
    {
        return UploadPerfs;
    }

    public void setUploadPerfs(Preferences uploadPerfs)
    {
        UploadPerfs = uploadPerfs;
    }

    private JFrame mainFrame;
    private IOSSClient ossClient;
    private DefaultListModel bucketListModel;
    private ExecutorService service;
    private ExecutorService dataService;
    private ExecutorService syncService;
    private CreateBucketDialog createBucketDialog;
    private JList bucketList;
    private JTextField bucketNameText;
    private JComboBox bucketAclComboBox;
    private ConsolePanel consolePanel;
    private FileListTablePanel fileListTablePanel;
    private JTable fileListTable;
    private FileTableModel fileTableModel;
    private LoginDialog loginDialog;
    private Map bucketIndexMap;
    private Map syncFileMap;
    private Map syncTaskMap;
    private File SyncListFile;
    private Preferences UploadPerfs;
}
