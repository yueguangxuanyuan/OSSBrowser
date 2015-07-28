// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IconTableModel.java

package com.aliyun.oss.ossbrowser.model;

import com.aliyun.oss.ossbrowser.control.*;
import com.aliyun.oss.ossbrowser.utils.*;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

// Referenced classes of package com.aliyun.oss.ossbrowser.model:
//            IconAndText

public class IconTableModel extends DefaultTableModel
{

    public IconTableModel(ResourceManager resourceManager)
    {
        requestCount = new AtomicInteger(0);
        lock = new Object();
        this.resourceManager = resourceManager;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }

    public Class getColumnClass(int columnIndex)
    {
        if(columnIndex == 0)
            return com/aliyun/oss/ossbrowser/model/IconAndText;
        else
            return java/lang/Object;
    }

    public int requstCountInc()
    {
        return requestCount.incrementAndGet();
    }

    public void addRow(String taskName, final long size, String bucket, String object, String path, int status, 
            ResourceManager resourceManager)
    {
        final IconAndText it = new IconAndText(ImageResource.loadingIcon, taskName, bucket, object, path, status, size, resourceManager);
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                synchronized(lock)
                {
                    addRow(new Object[] {
                        it, Utils.getFormatSize(size), Integer.valueOf(0), "Waiting", "0.00 KB/s"
                    });
                }
            }

            final IconTableModel this$0;
            private final IconAndText val$it;
            private final long val$size;

            
            {
                this$0 = IconTableModel.this;
                it = iconandtext;
                size = l;
                super();
            }
        }
);
    }

    public void removeTask(final String taskName)
    {
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                Object obj = lock;
                JVM INSTR monitorenter ;
                int l;
                int i;
                l = getRowCount();
                i = 0;
                  goto _L1
_L3:
                IconAndText value = (IconAndText)getValueAt(i, 0);
                if(!value.getText().equals(taskName))
                    continue; /* Loop/switch isn't completed */
                removeRow(i);
                return;
                i++;
_L1:
                if(i < l) goto _L3; else goto _L2
                obj;
                JVM INSTR monitorexit ;
                throw ;
_L2:
            }

            final IconTableModel this$0;
            private final String val$taskName;

            
            {
                this$0 = IconTableModel.this;
                taskName = s;
                super();
            }
        }
);
    }

    public void removeAllFailTask()
    {
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                synchronized(lock)
                {
                    for(int i = 0; i < getRowCount();)
                    {
                        String status = (String)getValueAt(i, 3);
                        int k = i;
                        if(status.equals("Failed") || status.equals("Completed"))
                        {
                            IconAndText iconAndText = (IconAndText)getValueAt(i, 0);
                            String bucket = iconAndText.getBucket();
                            String object = iconAndText.getObject();
                            String path = iconAndText.getPath();
                            File file = new File(path);
                            Utils.RemoveUploadMeta(resourceManager.getUploadPerfs(), Utils.GetUploadMetaKey(bucket, (new StringBuilder(String.valueOf(object))).append(file.getName()).toString()));
                            removeRow(k);
                        } else
                        {
                            i++;
                        }
                    }

                }
            }

            final IconTableModel this$0;

            
            {
                this$0 = IconTableModel.this;
                super();
            }
        }
);
    }

    public void retryAllFailTask()
    {
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                synchronized(lock)
                {
                    for(int i = 0; i < getRowCount(); i++)
                    {
                        String status = (String)getValueAt(i, 3);
                        if(status.equals("Failed"))
                        {
                            setValueAt("Waiting", i, 3);
                            IconAndText iconAndText = (IconAndText)getValueAt(i, 0);
                            ResourceManager resourceManager = iconAndText.getResourceManager();
                            String taskName = iconAndText.getText();
                            String bucket = iconAndText.getBucket();
                            String object = iconAndText.getObject();
                            String path = iconAndText.getPath();
                            int type = iconAndText.getStatus();
                            long size = iconAndText.getSize();
                            switch(type)
                            {
                            case 2: // '\002'
                                DownloadFileTask downloadTask = new DownloadFileTask(resourceManager, bucket, object, new File(path), size, taskName);
                                resourceManager.getService().submit(downloadTask);
                                break;

                            case 1: // '\001'
                                UploadFolderTask uploadFolderTask = new UploadFolderTask(resourceManager, bucket, object, path, taskName, false);
                                resourceManager.getDataService().submit(uploadFolderTask);
                                // fall through

                            case 0: // '\0'
                                UploadFileTask uploadTask = new UploadFileTask(resourceManager, new File(path), taskName, bucket, object, false, 20, true);
                                resourceManager.getDataService().submit(uploadTask);
                                break;
                            }
                        }
                    }

                }
            }

            final IconTableModel this$0;

            
            {
                this$0 = IconTableModel.this;
                super();
            }
        }
);
    }

    public void setValueAt(final String taskName, final int progress, final String status, final long speed)
    {
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                Object obj = lock;
                JVM INSTR monitorenter ;
                int l;
                int i;
                l = getRowCount();
                i = 0;
                  goto _L1
_L3:
                IconAndText value = (IconAndText)getValueAt(i, 0);
                if(!value.getText().equals(taskName))
                    continue; /* Loop/switch isn't completed */
                if(progress != -1)
                    setValueAt(Integer.valueOf(progress), i, 2);
                if(status != null)
                    setValueAt(status, i, 3);
                if(speed != -1L)
                    setValueAt((new StringBuilder(String.valueOf(Utils.getFormatSize(speed)))).append("/s").toString(), i, 4);
                if(status.equals("Failed"))
                    value.setIcon(ImageResource.pauseIcon);
                else
                    value.setIcon(ImageResource.loadingIcon);
                return;
                i++;
_L1:
                if(i < l) goto _L3; else goto _L2
                obj;
                JVM INSTR monitorexit ;
                throw ;
_L2:
            }

            final IconTableModel this$0;
            private final String val$taskName;
            private final int val$progress;
            private final String val$status;
            private final long val$speed;

            
            {
                this$0 = IconTableModel.this;
                taskName = s;
                progress = i;
                status = s1;
                speed = l;
                super();
            }
        }
);
    }

    private static final long serialVersionUID = 0x66134b72f48ee17fL;
    AtomicInteger requestCount;
    Object lock;
    ResourceManager resourceManager;
}
