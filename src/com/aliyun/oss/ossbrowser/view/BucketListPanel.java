// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BucketListPanel.java

package com.aliyun.oss.ossbrowser.view;

import com.aliyun.oss.ossbrowser.model.BucketIconListItemRenderer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

// Referenced classes of package com.aliyun.oss.ossbrowser.view:
//            BucketMenuPanel

public class BucketListPanel extends JPanel
{

    public BucketListPanel()
    {
        bucketList = null;
        menu = null;
        listModel = new DefaultListModel();
    }

    public void init()
    {
        bucketList = new JList();
        bucketList.setOpaque(false);
        bucketList.setCellRenderer(new BucketIconListItemRenderer());
        bucketList.setModel(listModel);
        bucketList.setSelectionMode(0);
        setLayout(new BorderLayout());
        menu = new BucketMenuPanel();
        menu.init();
        menu.setPreferredSize(new Dimension(285, 35));
        add(menu, "North");
        JScrollPane panel = new JScrollPane(bucketList);
        add(panel, "Center");
        TitledBorder border = new TitledBorder("bucket");
        setBorder(border);
    }

    public JList getBucketList()
    {
        return bucketList;
    }

    public void setBucketList(JList bucketList)
    {
        this.bucketList = bucketList;
    }

    public DefaultListModel getListModel()
    {
        return listModel;
    }

    public void setListModel(DefaultListModel listModel)
    {
        this.listModel = listModel;
    }

    public void addActionListener(ActionListener listener)
    {
        menu.delBucket.addActionListener(listener);
        menu.newBucket.addActionListener(listener);
        menu.refreshBucket.addActionListener(listener);
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
        BucketListPanel bucketMenu = new BucketListPanel();
        bucketMenu.init();
        frame.add(bucketMenu);
    }

    private static final long serialVersionUID = 0x2f645ab0335e338fL;
    private JList bucketList;
    private BucketMenuPanel menu;
    DefaultListModel listModel;
}
