// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogIconListItemRenderer.java

package com.aliyun.oss.ossbrowser.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;

// Referenced classes of package com.aliyun.oss.ossbrowser.model:
//            LogIconListItem

public class LogIconListItemRenderer extends JLabel
    implements ListCellRenderer
{

    public LogIconListItemRenderer()
    {
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        LogIconListItem item = (LogIconListItem)value;
        setIcon(item.getIcon());
        setText(item.getText());
        setOpaque(true);
        if(isSelected)
            setBackground(Color.GRAY.brighter());
        else
            setBackground(Color.WHITE);
        return this;
    }

    private static final long serialVersionUID = 0x3be3e0ee844a26cbL;
}
