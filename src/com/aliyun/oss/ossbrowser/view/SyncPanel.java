// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SyncPanel.java

package com.aliyun.oss.ossbrowser.view;

import com.aliyun.oss.ossbrowser.model.PropertyTableModel;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SyncPanel extends JPanel
{

    public SyncPanel()
    {
    }

    public void init()
    {
        syncTable = new JTable();
        syncTable.setDefaultRenderer(java/lang/String, null);
        tableContainer = new JScrollPane(syncTable);
        tableModel = new PropertyTableModel();
        String as[];
        int j = (as = columns).length;
        for(int i = 0; i < j; i++)
        {
            String str = as[i];
            tableModel.addColumn(str);
        }

        syncTable.setDefaultRenderer(java/lang/String, null);
        syncTable.setModel(tableModel);
        setLayout(new BorderLayout());
        add(tableContainer);
    }

    public DefaultTableModel getTableModel()
    {
        return tableModel;
    }

    public JTable getSyncTable()
    {
        return syncTable;
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
        SyncPanel console = new SyncPanel();
        console.init();
        frame.add(console);
        frame.pack();
    }

    private static final long serialVersionUID = 0xd335ab6539804f52L;
    private JTable syncTable;
    private JScrollPane tableContainer;
    private String columns[] = {
        "\u672C\u5730\u6587\u4EF6\u5939", "\u4E91\u7AEF\u6587\u4EF6\u5939"
    };
    private DefaultTableModel tableModel;
}
