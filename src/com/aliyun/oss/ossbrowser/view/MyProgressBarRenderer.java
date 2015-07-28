// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TasksPanel.java

package com.aliyun.oss.ossbrowser.view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

class MyProgressBarRenderer extends JProgressBar
    implements TableCellRenderer
{

    MyProgressBarRenderer()
    {
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Integer v = (Integer)value;
        setStringPainted(true);
        setMinimum(0);
        setMaximum(100);
        setBorder(BorderFactory.createEmptyBorder());
        if(isSelected)
            setBackground(new Color(206, 207, 255));
        else
            setBackground(Color.white);
        setValue(v.intValue());
        setToolTipText((new StringBuilder(String.valueOf(String.valueOf(getValue())))).append("%").toString());
        return this;
    }

    private static final long serialVersionUID = 0x444e9599fb694336L;
}
