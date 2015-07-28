// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PropertyPanel.java

package com.aliyun.oss.ossbrowser.view;

import com.aliyun.oss.ossbrowser.model.PropertyTableModel;
import java.awt.BorderLayout;
import javax.swing.*;

public class PropertyPanel extends JPanel
{

    public PropertyPanel()
    {
    }

    public void init()
    {
        propertyTable = new JTable();
        propertyTable.setDefaultRenderer(java/lang/String, null);
        tableContainer = new JScrollPane(propertyTable);
        tableModel = new PropertyTableModel();
        String as[];
        int j = (as = columns).length;
        for(int i = 0; i < j; i++)
        {
            String str = as[i];
            tableModel.addColumn(str);
        }

        propertyTable.setDefaultRenderer(java/lang/String, null);
        propertyTable.setModel(tableModel);
        setLayout(new BorderLayout());
        add(tableContainer);
    }

    public PropertyTableModel getTableModel()
    {
        return tableModel;
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
        PropertyPanel console = new PropertyPanel();
        console.init();
        frame.add(console);
        frame.pack();
    }

    private static final long serialVersionUID = 0xd335ab6539804f52L;
    private JTable propertyTable;
    private JScrollPane tableContainer;
    private String columns[] = {
        "\u5C5E\u6027", "\u5C5E\u6027\u503C"
    };
    private PropertyTableModel tableModel;
}
