// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileTableModel.java

package com.aliyun.oss.ossbrowser.model;

import com.aliyun.aos.services.oss.model.OSSObjectSummary;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;

// Referenced classes of package com.aliyun.oss.ossbrowser.model:
//            IconAndObjectSummary

public class FileTableModel extends DefaultTableModel
{

    public FileTableModel()
    {
        bucketName = null;
    }

    public boolean isCellEditable(int row, int column)
    {
        return false;
    }

    public void removeAllRows(JTable table)
    {
        RowSorter rowSort = table.getRowSorter();
        if(rowSort != null)
            rowSort.setSortKeys(null);
        dataVector.clear();
        fireTableDataChanged();
    }

    public void removeByKey(String path)
    {
        for(int i = 0; i < getRowCount(); i++)
        {
            String name = ((IconAndObjectSummary)getValueAt(i, 0)).getSummary().getKey();
            if(path.equals(name))
            {
                removeRow(i);
                return;
            }
        }

    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public Class getColumnClass(int columnIndex)
    {
        if(columnIndex == 0)
            return com/aliyun/oss/ossbrowser/model/IconAndObjectSummary;
        else
            return java/lang/Object;
    }

    private static final long serialVersionUID = 0x344288d0342b8402L;
    private String bucketName;
}
