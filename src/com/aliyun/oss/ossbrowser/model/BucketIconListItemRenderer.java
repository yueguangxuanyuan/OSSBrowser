// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BucketIconListItemRenderer.java

package com.aliyun.oss.ossbrowser.model;

import com.aliyun.aos.services.oss.model.Bucket;
import java.awt.Color;
import java.awt.Component;
import javax.swing.*;

// Referenced classes of package com.aliyun.oss.ossbrowser.model:
//            BucketIconListItem

public class BucketIconListItemRenderer extends JLabel
    implements ListCellRenderer
{

    public BucketIconListItemRenderer()
    {
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        BucketIconListItem item = (BucketIconListItem)value;
        setIcon(item.getIcon());
        setText(item.getBucket().getName());
        setOpaque(true);
        if(isSelected)
            setBackground(Color.GRAY.brighter());
        else
            setBackground(Color.WHITE);
        return this;
    }

    private static final long serialVersionUID = 0xd965db10d5eb2cd3L;
}
