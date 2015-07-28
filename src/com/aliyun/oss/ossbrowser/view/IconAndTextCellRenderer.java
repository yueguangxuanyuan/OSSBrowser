// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TasksPanel.java

package com.aliyun.oss.ossbrowser.view;

import com.aliyun.oss.ossbrowser.model.IconAndText;
import com.aliyun.oss.ossbrowser.model.IconType;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

class IconAndTextCellRenderer extends DefaultTableCellRenderer
{

    IconAndTextCellRenderer()
    {
    }

    public Component getTableCellRendererComponent(final JTable table, Object value, boolean isSelected, boolean hasFocus, final int row, final int column)
    {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(value instanceof IconAndText)
        {
            JLabel label = (JLabel)c;
            IconAndText it = (IconAndText)value;
            ImageIcon icon = (ImageIcon)it.getIcon();
            if(it.getType() == IconType.ANIMATED)
                icon.setImageObserver(new ImageObserver() {

                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height)
                    {
                        if((infoflags & 0x30) != 0)
                        {
                            java.awt.Rectangle rect = table.getCellRect(row, column, false);
                            table.repaint(rect);
                        }
                        return (infoflags & 0xa0) == 0;
                    }

                    final IconAndTextCellRenderer this$0;
                    private final JTable val$table;
                    private final int val$row;
                    private final int val$column;

            
            {
                this$0 = IconAndTextCellRenderer.this;
                table = jtable;
                row = i;
                column = j;
                super();
            }
                }
);
            label.setText(it.getText());
            label.setIcon(it.getIcon());
        }
        return c;
    }

    private static final long serialVersionUID = 0x85a47c6bb3386fbeL;
}
