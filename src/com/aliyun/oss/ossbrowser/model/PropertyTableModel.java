// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PropertyTableModel.java

package com.aliyun.oss.ossbrowser.model;

import com.aliyun.aos.services.oss.model.*;
import com.aliyun.oss.ossbrowser.utils.Utils;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.table.DefaultTableModel;

public class PropertyTableModel extends DefaultTableModel
{

    public PropertyTableModel()
    {
        lock = new Object();
    }

    public boolean isCellEditable(int row, int column)
    {
        return false;
    }

    public void removeAllRows()
    {
        dataVector.clear();
        fireTableRowsDeleted(0, getRowCount());
    }

    public int getRequestCount()
    {
        return requestCount.get();
    }

    public int requestCountInc()
    {
        return requestCount.incrementAndGet();
    }

    public void initBucketProperty()
    {
        synchronized(lock)
        {
            removeAllRows();
            addRow(new Object[] {
                "Owner DisplayName", "Loading..."
            });
            addRow(new Object[] {
                "Owner ID", "Loading..."
            });
            addRow(new Object[] {
                "Name", "Loading..."
            });
            addRow(new Object[] {
                "Creation Date", "Loading..."
            });
        }
    }

    public void clearProperty()
    {
        synchronized(lock)
        {
            removeAllRows();
        }
    }

    public void initObjectProperty()
    {
        synchronized(lock)
        {
            removeAllRows();
            addRow(new Object[] {
                "ETag", "Loading..."
            });
            addRow(new Object[] {
                "Key", "Loading..."
            });
            addRow(new Object[] {
                "Last Modified", "Loading..."
            });
            addRow(new Object[] {
                "Owner ID", "Loading..."
            });
            addRow(new Object[] {
                "Owner DisplayName", "Loading..."
            });
            addRow(new Object[] {
                "Size", "Loading..."
            });
            addRow(new Object[] {
                "Storage Class", "Loading..."
            });
        }
    }

    public void setObjectProperty(OSSObjectSummary object, int count)
    {
        synchronized(lock)
        {
            if(count == requestCount.get())
            {
                setValueAt(object.getETag(), 0, 1);
                setValueAt(object.getKey(), 1, 1);
                setValueAt(object.getLastModified(), 2, 1);
                setValueAt(object.getOwner().getId(), 3, 1);
                setValueAt(object.getOwner().getDisplayName(), 4, 1);
                setValueAt(Long.valueOf(object.getSize()), 5, 1);
                setValueAt(object.getStorageClass(), 6, 1);
            }
        }
    }

    public void setBucketBaseProperties(Bucket bucket, int count)
    {
        synchronized(lock)
        {
            if(count == requestCount.get())
            {
                setValueAt(bucket.getOwner().getDisplayName(), 0, 1);
                setValueAt(bucket.getOwner().getId(), 1, 1);
                setValueAt(bucket.getName(), 2, 1);
                setValueAt(Utils.getFormatDate(bucket.getCreationDate()), 3, 1);
            }
        }
    }

    public void setBucketSizeProperties(ObjectListing list, int count)
    {
        synchronized(lock)
        {
            if(count == requestCount.get())
            {
                int totalFile = 0;
                int totalFolder = 0;
                long totalSize = 0L;
                setValueAt(Integer.valueOf(list.getObjectSummaries().size()), 4, 1);
                for(Iterator iterator = list.getObjectSummaries().iterator(); iterator.hasNext();)
                {
                    OSSObjectSummary object = (OSSObjectSummary)iterator.next();
                    totalSize += object.getSize();
                    if(object.getKey().endsWith("/"))
                        totalFolder++;
                    else
                        totalFile++;
                }

                setValueAt(Integer.valueOf(totalFile), 5, 1);
                setValueAt(Integer.valueOf(totalFolder), 6, 1);
                setValueAt(Utils.getFormatSize(totalSize), 7, 1);
            }
        }
    }

    private static final long serialVersionUID = 0x7c5427657676434dL;
    private static AtomicInteger requestCount = new AtomicInteger(0);
    private Object lock;

}
