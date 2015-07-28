// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IconAndObjectSummary.java

package com.aliyun.oss.ossbrowser.model;

import com.aliyun.aos.services.oss.model.OSSObjectSummary;
import com.aliyun.oss.ossbrowser.utils.ImageResource;
import java.util.HashMap;
import javax.swing.Icon;

// Referenced classes of package com.aliyun.oss.ossbrowser.model:
//            IconType

public class IconAndObjectSummary
{

    public IconAndObjectSummary()
    {
    }

    public Icon getIcon()
    {
        return icon;
    }

    public void setIcon(Icon icon)
    {
        this.icon = icon;
    }

    public IconType getIconType()
    {
        return iconType;
    }

    public void setIconType(IconType iconType)
    {
        this.iconType = iconType;
    }

    public OSSObjectSummary getSummary()
    {
        return summary;
    }

    public void setSummary(OSSObjectSummary summary)
    {
        this.summary = summary;
    }

    public static IconAndObjectSummary createFileItem(OSSObjectSummary summary)
    {
        IconAndObjectSummary item = new IconAndObjectSummary();
        Icon icon = ImageResource.defaultIcon;
        int pos = summary.getKey().lastIndexOf(".") + 1;
        if(pos != -1)
        {
            String ending = summary.getKey().substring(pos);
            if(ImageResource.sEndingToIcon.containsKey(ending))
                icon = (Icon)ImageResource.sEndingToIcon.get(ending);
        }
        item.setIcon(icon);
        item.setIconType(IconType.STATIC);
        item.setSummary(summary);
        return item;
    }

    public static IconAndObjectSummary createFolderItem(String bucketName, String path)
    {
        OSSObjectSummary summary = new OSSObjectSummary();
        summary.setBucketName(bucketName);
        summary.setKey(path);
        IconAndObjectSummary item = new IconAndObjectSummary();
        item.setIcon(ImageResource.folderIcon);
        item.setIconType(IconType.STATIC);
        item.setSummary(summary);
        return item;
    }

    public static IconAndObjectSummary createLoadingItem(String bucketName)
    {
        OSSObjectSummary summary = new OSSObjectSummary();
        summary.setBucketName(bucketName);
        summary.setKey("Loading...");
        IconAndObjectSummary item = new IconAndObjectSummary();
        item.setIcon(ImageResource.loadingIcon);
        item.setIconType(IconType.ANIMATED);
        item.setSummary(summary);
        return item;
    }

    private Icon icon;
    private IconType iconType;
    private OSSObjectSummary summary;
}
