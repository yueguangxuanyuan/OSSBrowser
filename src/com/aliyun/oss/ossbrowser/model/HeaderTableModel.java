// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HeaderTableModel.java

package com.aliyun.oss.ossbrowser.model;

import com.aliyun.aos.services.oss.model.ObjectMetadata;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.table.DefaultTableModel;

public class HeaderTableModel extends DefaultTableModel
{

    public HeaderTableModel()
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

    public void initProperty()
    {
        synchronized(lock)
        {
            removeAllRows();
        }
    }

    public void setObjectProperty(ObjectMetadata meta, int count)
    {
        synchronized(lock)
        {
            if(count == requestCount.get())
            {
                Map userMeta = meta.getRawMetadata();
                String header;
                for(Iterator iterator = userMeta.keySet().iterator(); iterator.hasNext(); addRow(new Object[] {
    header, userMeta.get(header)
}))
                    header = (String)iterator.next();

            }
        }
    }

    public int getRequestCount()
    {
        return requestCount.get();
    }

    public int requestCountInc()
    {
        return requestCount.incrementAndGet();
    }

    private static final long serialVersionUID = 0xbd92eb5e0982d07cL;
    private static AtomicInteger requestCount = new AtomicInteger(0);
    private Object lock;

}
