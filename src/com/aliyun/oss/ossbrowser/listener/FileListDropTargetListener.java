// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileListDropTargetListener.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.aos.services.oss.model.Bucket;
import com.aliyun.oss.ossbrowser.control.UploadFolderRecursivelyTask;
import com.aliyun.oss.ossbrowser.model.BucketIconListItem;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.view.FileListTablePanel;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.util.concurrent.ExecutorService;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class FileListDropTargetListener
    implements DropTargetListener
{

    public FileListDropTargetListener(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    public void dragEnter(DropTargetDragEvent droptargetdragevent)
    {
    }

    public void dragOver(DropTargetDragEvent droptargetdragevent)
    {
    }

    public void dropActionChanged(DropTargetDragEvent droptargetdragevent)
    {
    }

    public void dragExit(DropTargetEvent droptargetevent)
    {
    }

    public void drop(DropTargetDropEvent dtde)
    {
        if(!dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
            break MISSING_BLOCK_LABEL_99;
        refreshBucketAndPrefix();
        if(bucket == null)
        {
            JOptionPane.showMessageDialog(resourceManager.getLoginDialog(), "Upload failed, Please select a bucket.");
            return;
        }
        dtde.acceptDrop(3);
        java.util.List list = (java.util.List)dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
        UploadFolderRecursivelyTask task = new UploadFolderRecursivelyTask(resourceManager, bucket, prefix, list, true);
        resourceManager.getDataService().submit(task);
        dtde.dropComplete(true);
        break MISSING_BLOCK_LABEL_111;
        dtde.rejectDrop();
        break MISSING_BLOCK_LABEL_111;
        Exception e;
        e;
        e.printStackTrace();
    }

    private void refreshBucketAndPrefix()
    {
        bucket = prefix = null;
        int index = resourceManager.getBucketList().getSelectedIndex();
        if(index != -1)
        {
            BucketIconListItem item = (BucketIconListItem)resourceManager.getBucketListModel().get(index);
            bucket = item.getBucket().getName();
        }
        prefix = resourceManager.getFileListTablePanel().getPath();
    }

    private ResourceManager resourceManager;
    private String bucket;
    private String prefix;
}
