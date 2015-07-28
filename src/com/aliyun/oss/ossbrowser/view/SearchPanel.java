// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SearchPanel.java

package com.aliyun.oss.ossbrowser.view;

import com.aliyun.oss.ossbrowser.model.*;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class SearchPanel extends JPanel
{

    public SearchPanel()
    {
        fileTable = null;
        model = null;
    }

    public void init()
    {
        model = new FileTableModel();
        fileTable = new JTable(model);
        fileTable.setColumnSelectionAllowed(false);
        fileTable.setRowHeight(16);
        for(int i = 0; i < columnName.length; i++)
            model.addColumn(columnName[i]);

        fileTable.getColumn("\u6587\u4EF6\u540D").setCellRenderer(new FileIconCellRender(true));
        fileTable.getColumn("\u5927\u5C0F").setCellRenderer(new FileIconCellRender(true));
        TableRowSorter sorter = new TableRowSorter(model);
        sorter.setComparator(0, new FileNameComparator());
        sorter.setComparator(1, new SizeComparator());
        sorter.setComparator(2, new StringComparator());
        sorter.setComparator(3, new StringComparator());
        fileTable.setRowSorter(sorter);
        setLayout(new BorderLayout());
        add(new JScrollPane(fileTable));
    }

    public JTable getFileTable()
    {
        return fileTable;
    }

    public FileTableModel getModel()
    {
        return model;
    }

    private static final long serialVersionUID = 0x4e161eade5ff441L;
    private JTable fileTable;
    private FileTableModel model;
    private String columnName[] = {
        "\u6587\u4EF6\u540D", "\u5927\u5C0F", "ETag", "\u6700\u540E\u4FEE\u6539\u65F6\u95F4"
    };
}
