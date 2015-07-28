// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileIconCellRender.java

package com.aliyun.oss.ossbrowser.model;

import com.aliyun.aos.services.oss.model.OSSObjectSummary;
import com.aliyun.oss.ossbrowser.utils.Utils;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

// Referenced classes of package com.aliyun.oss.ossbrowser.model:
//            IconAndObjectSummary, IconType

public class FileIconCellRender extends DefaultTableCellRenderer
{

    public FileIconCellRender(boolean isAbsolutePath)
    {
        this.isAbsolutePath = isAbsolutePath;
    }

    public Component getTableCellRendererComponent(final JTable table, Object value, boolean isSelected, boolean hasFocus, final int row, final int column)
    {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(value instanceof Long)
        {
            JLabel label = (JLabel)c;
            label.setText(Utils.getFormatSize(((Long)value).longValue()));
        } else
        if(value instanceof IconAndObjectSummary)
        {
            JLabel label = (JLabel)c;
            IconAndObjectSummary is = (IconAndObjectSummary)value;
            ImageIcon icon = (ImageIcon)is.getIcon();
            if(is.getIconType() == IconType.ANIMATED)
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

                    final FileIconCellRender this$0;
                    private final JTable val$table;
                    private final int val$row;
                    private final int val$column;

            
            {
                this$0 = FileIconCellRender.this;
                table = jtable;
                row = i;
                column = j;
                super();
            }
                }
);
            OSSObjectSummary summary = is.getSummary();
            String path = summary.getKey();
            if(isAbsolutePath)
            {
                label.setText(path);
            } else
            {
                String ss[] = path.split("/");
                if(ss.length == 0)
                    label.setText(path);
                else
                if(isFolderPath(path))
                    label.setText((new StringBuilder(String.valueOf(ss[ss.length - 1]))).append("/").toString());
                else
                    label.setText(ss[ss.length - 1]);
            }
            label.setIcon(is.getIcon());
        }
        return c;
    }

    private static boolean isFolderPath(String s)
    {
        return s.lastIndexOf('/') == s.length() - 1;
    }

    private static final long serialVersionUID = 0x8309419ed8fe3d2L;
    private boolean isAbsolutePath;
}
