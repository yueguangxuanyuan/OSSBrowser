// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TasksPanel.java

package com.aliyun.oss.ossbrowser.view;

import com.aliyun.oss.ossbrowser.listener.TaskMouseListener;
import com.aliyun.oss.ossbrowser.model.IconAndText;
import com.aliyun.oss.ossbrowser.model.IconTableModel;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.TableColumn;

// Referenced classes of package com.aliyun.oss.ossbrowser.view:
//            IconAndTextCellRenderer, MyProgressBarRenderer

public class TasksPanel extends JPanel
{

    public TasksPanel()
    {
    }

    public void init(ResourceManager resourceManager)
    {
        taskTable = new JTable();
        tableModel = new IconTableModel(resourceManager);
        for(int i = 0; i < columns.length; i++)
            tableModel.addColumn(columns[i]);

        tableContainer = new JScrollPane(taskTable);
        taskTable.setDefaultRenderer(com/aliyun/oss/ossbrowser/model/IconAndText, new IconAndTextCellRenderer());
        taskTable.setModel(tableModel);
        taskTable.getColumn("\u8FDB\u5EA6").setCellRenderer(new MyProgressBarRenderer());
        taskTable.addMouseListener(new TaskMouseListener(taskTable));
        setLayout(new BorderLayout());
        add(tableContainer);
    }

    public JTable getTaskTable()
    {
        return taskTable;
    }

    public IconTableModel getTableModel()
    {
        return tableModel;
    }

    private static final long serialVersionUID = 0x971ea574f21cf09cL;
    private IconTableModel tableModel;
    private JTable taskTable;
    private JScrollPane tableContainer;
    private String columns[] = {
        "\u4EFB\u52A1\u540D\u79F0", "\u5927\u5C0F", "\u8FDB\u5EA6", "\u72B6\u6001", "\u901F\u5EA6"
    };
}
