// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainFrame.java

package com.aliyun.oss.ossbrowser.main;

import com.aliyun.oss.ossbrowser.control.SyncTask;
import com.aliyun.oss.ossbrowser.listener.*;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.utils.SyncUtils;
import com.aliyun.oss.ossbrowser.view.*;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import java.awt.Dimension;
import java.awt.dnd.DropTarget;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.prefs.Preferences;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame
{

    public MainFrame()
    {
    }

    public void init()
    {
        bucketList = new BucketListPanel();
        fileList = new FileListTablePanel();
        consolePanel = new ConsolePanel();
        resourceManager = new ResourceManager();
        bucketController = new BucketController(resourceManager);
        createBucketDialog = new CreateBucketDialog(this);
        loginDialog = new LoginDialog();
        bucketList.init();
        fileList.init();
        consolePanel.init(resourceManager);
        consolePanel.setPreferredSize(new Dimension(1200, 230));
        bucketList.setPreferredSize(new Dimension(285, 500));
        fileList.setPreferredSize(new Dimension(850, 500));
        fileList.getFileTable().getSelectionModel().addListSelectionListener(new FileSelectionListener(resourceManager));
        fileList.getFileTable().setTransferHandler(new FileListTransferHandler(resourceManager));
        fileList.getFileTable().setDragEnabled(true);
        new DropTarget(fileList.getFileTable(), 3, new FileListDropTargetListener(resourceManager));
        new DropTarget(this, 3, new FileListDropTargetListener(resourceManager));
        JSplitPane splitPanel1 = new JSplitPane(1, true, bucketList, fileList);
        splitPanel1.setOneTouchExpandable(true);
        splitPanel1.setDividerSize(10);
        JSplitPane splitPanel2 = new JSplitPane(0, true, splitPanel1, consolePanel);
        splitPanel2.setDividerLocation(500);
        splitPanel2.setOneTouchExpandable(true);
        splitPanel2.setDividerSize(10);
        add(splitPanel2, "Center");
        setPreferredSize(new Dimension(1200, 700));
        setMinimumSize(new Dimension(1200, 700));
        setVisible(false);
        setDefaultCloseOperation(3);
        createBucketDialog.addActionListener(bucketController);
        java.util.concurrent.ExecutorService service = Executors.newFixedThreadPool(5);
        java.util.concurrent.ExecutorService dataService = Executors.newFixedThreadPool(5);
        resourceManager.setBucketAclComboBox(createBucketDialog.getComboBox());
        resourceManager.setBucketNameText(createBucketDialog.getTextField());
        resourceManager.setService(service);
        resourceManager.setDataService(dataService);
        resourceManager.setBucketList(bucketList.getBucketList());
        resourceManager.setBucketListModel(bucketList.getListModel());
        resourceManager.setCreateBucketDialog(createBucketDialog);
        resourceManager.setConsolePanel(consolePanel);
        bucketList.addActionListener(bucketController);
        resourceManager.setFileListTable(fileList.getFileTable());
        resourceManager.setFileTableModel(fileList.getModel());
        resourceManager.setBucketIndexMap(new HashMap());
        bucketListSelectionListener = new BucketListSelectionListener(resourceManager);
        bucketList.getBucketList().addListSelectionListener(bucketListSelectionListener);
        fileItemMouseListener = new FileItemMouseListener(resourceManager, fileList.getFileTable());
        fileList.getFileTable().addMouseListener(fileItemMouseListener);
        fileList.getSearchButton().addActionListener(new SearchButtonListener(resourceManager));
        consolePanel.getSearchPanel().getFileTable().addMouseListener(new SearchMouseListener(resourceManager, consolePanel.getSearchPanel().getFileTable()));
        consolePanel.getHttpHeadPanel().getGenerateUrlButton().addActionListener(new GenerateUrlButtonListener(resourceManager));
        fileMenuListener = new FileMenuListener(resourceManager);
        resourceManager.setMainFrame(this);
        resourceManager.setFileListTablePanel(fileList);
        fileList.getFileMenu().addActionListener(fileMenuListener);
        fileList.addPathListener(new PathKeyListerner(resourceManager));
        loginController = new LoginController(resourceManager);
        resourceManager.setLoginDialog(loginDialog);
        loginDialog.getLoginPanel().getLoginButton().addActionListener(loginController);
        resourceManager.setSyncTaskMap(new HashMap());
        String syncFileName = resourceManager.getLoginDialog().getLoginPanel().getPreferences().get("LOGIN_ACCESS_ID", "OssSyncList");
        File syncFile = new File((new StringBuilder(String.valueOf(syncFileName))).append(".osl").toString());
        resourceManager.setSyncListFile(syncFile);
        resourceManager.setSyncFileMap(SyncUtils.getSyncList(syncFile));
        addSyncTask(resourceManager.getSyncFileMap(), syncFile);
        JTable syncTable = resourceManager.getConsolePanel().getSyncPanel().getSyncTable();
        syncTable.addMouseListener(new SyncTableMouseListener(resourceManager, syncTable));
    }

    private void addSyncTask(Map syncList, File saveFile)
    {
        for(Iterator iterator = syncList.keySet().iterator(); iterator.hasNext();)
        {
            String local = (String)iterator.next();
            String cloudPath = (String)syncList.get(local);
            String bucket = "";
            String key = "";
            if(cloudPath.indexOf("/") != -1)
            {
                bucket = cloudPath.substring(0, cloudPath.indexOf("/"));
                key = cloudPath.substring(cloudPath.indexOf("/") + 1);
                SyncTask task = new SyncTask(bucket, key, local, resourceManager);
                resourceManager.getSyncTaskMap().put(local, task);
                resourceManager.getConsolePanel().getSyncPanel().getTableModel().addRow(new Object[] {
                    local, cloudPath
                });
            }
        }

        SyncUtils.saveSyncList(syncList, saveFile);
    }

    public static void makeSingle(String singleId)
    {
        RandomAccessFile raf = null;
        FileChannel channel = null;
        FileLock lock = null;
        try
        {
            File sf = new File((new StringBuilder(String.valueOf(System.getProperty("java.io.tmpdir")))).append(singleId).append(".single").toString());
            sf.deleteOnExit();
            sf.createNewFile();
            raf = new RandomAccessFile(sf, "rw");
            channel = raf.getChannel();
            lock = channel.tryLock();
            if(lock == null)
            {
                JOptionPane.showMessageDialog(null, "ossbrowser\u6B63\u5728\u8FD0\u884C");
                throw new Error("An instance of the application is running.");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String args[])
    {
        makeSingle("ossbrowser.sge");
        try
        {
            UIManager.setLookAndFeel(new McWinLookAndFeel());
        }
        catch(UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
        MainFrame m = new MainFrame();
        m.init();
        m.setMinimumSize(new Dimension(1220, 730));
        m.setMaximumSize(new Dimension(1230, 730));
        m.setTitle("OSSBrowser v1.4");
        m.loginDialog.setVisible(true);
    }

    private static final long serialVersionUID = 0x9fc811d5db5bcf63L;
    private BucketListPanel bucketList;
    private FileListTablePanel fileList;
    private ConsolePanel consolePanel;
    private CreateBucketDialog createBucketDialog;
    private ResourceManager resourceManager;
    private BucketController bucketController;
    private BucketListSelectionListener bucketListSelectionListener;
    private FileItemMouseListener fileItemMouseListener;
    private FileMenuListener fileMenuListener;
    private LoginDialog loginDialog;
    private LoginController loginController;
}
