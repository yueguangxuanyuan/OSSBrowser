// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BucketMenuPanel.java

package com.aliyun.oss.ossbrowser.view;

import com.aliyun.oss.ossbrowser.listener.MenuMouseListener;
import com.aliyun.oss.ossbrowser.utils.ImageResource;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BucketMenuPanel extends JPanel
{

    public BucketMenuPanel()
    {
    }

    void init()
    {
        setSize(250, 34);
        java.awt.LayoutManager layoutManager = new GridLayout(1, 3);
        setLayout(layoutManager);
        Insets i = new Insets(0, 0, 0, 0);
        newBucket = new JMenuItem("\u65B0\u5EFA", ImageResource.newBucketIcon);
        newBucket.setMargin(i);
        newBucket.setBorder(new EmptyBorder(0, 0, 0, 0));
        newBucket.setIconTextGap(0);
        newBucket.addMouseListener(new MenuMouseListener(newBucket));
        add(newBucket);
        delBucket = new JMenuItem("\u5220\u9664", ImageResource.deleteBucketIcon);
        delBucket.setMargin(i);
        delBucket.setBorder(new EmptyBorder(0, 0, 0, 0));
        delBucket.setIconTextGap(0);
        delBucket.addMouseListener(new MenuMouseListener(delBucket));
        add(delBucket);
        refreshBucket = new JMenuItem("\u5237\u65B0", ImageResource.refreshBucketIcon);
        refreshBucket.setMargin(i);
        refreshBucket.setBorder(new EmptyBorder(0, 0, 0, 0));
        refreshBucket.setIconTextGap(0);
        refreshBucket.addMouseListener(new MenuMouseListener(refreshBucket));
        add(refreshBucket);
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
        BucketMenuPanel bucketMenu = new BucketMenuPanel();
        bucketMenu.init();
        frame.add(bucketMenu);
    }

    private static final long serialVersionUID = 0xc24018c2ad9d96aaL;
    JMenuItem newBucket;
    JMenuItem delBucket;
    JMenuItem refreshBucket;
}
