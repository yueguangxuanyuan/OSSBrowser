// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileMenuPanel.java

package com.aliyun.oss.ossbrowser.view;

import com.aliyun.oss.ossbrowser.utils.ImageResource;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

class UploadPannel extends JPopupMenu
{

    UploadPannel()
    {
    }

    public void init()
    {
        uploadFiles = new JMenuItem("\u4E0A\u4F20\u6587\u4EF6", ImageResource.uploadFileIcon);
        uploadFolder = new JMenuItem("\u4E0A\u4F20\u6587\u4EF6\u5939", ImageResource.uploadIcon);
        setSize(200, 60);
        add(uploadFiles);
        add(uploadFolder);
    }

    private static final long serialVersionUID = 0x4966c0a5980af74aL;
    JMenuItem uploadFiles;
    JMenuItem uploadFolder;
}
