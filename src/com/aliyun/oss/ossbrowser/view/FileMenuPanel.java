// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileMenuPanel.java

package com.aliyun.oss.ossbrowser.view;

import com.aliyun.oss.ossbrowser.listener.MenuMouseListener;
import com.aliyun.oss.ossbrowser.utils.ImageResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Referenced classes of package com.aliyun.oss.ossbrowser.view:
//            UploadPannel

public class FileMenuPanel extends JPanel
{

    public FileMenuPanel()
    {
    }

    public void init()
    {
        GridLayout layout = new GridLayout(1, 6);
        download = new JMenuItem("\u4E0B\u8F7D", ImageResource.downloadIcon);
        upload = new JMenuItem("\u4E0A\u4F20", ImageResource.uploadIcon);
        delete = new JMenuItem("\u5220\u9664", ImageResource.deleteIcon);
        newFolder = new JMenuItem("\u65B0\u5EFA\u6587\u4EF6\u5939", ImageResource.newFolder);
        refresh = new JMenuItem("\u5237\u65B0", ImageResource.refreshIcon);
        upload.setLayout(new BorderLayout());
        up = new UploadPannel();
        up.init();
        upload.setMargin(new Insets(1, 1, 1, 1));
        JLabel l = new JLabel(ImageResource.triangleIcon);
        upload.add(l, "East");
        Insets i = new Insets(0, 0, 0, 0);
        download.setMargin(i);
        download.setBorder(new EmptyBorder(0, 0, 0, 0));
        download.setIconTextGap(0);
        upload.setMargin(i);
        upload.setBorder(new EmptyBorder(0, 0, 0, 0));
        upload.setIconTextGap(0);
        newFolder.setMargin(i);
        newFolder.setBorder(new EmptyBorder(0, 0, 0, 0));
        newFolder.setIconTextGap(0);
        refresh.setIconTextGap(0);
        refresh.setMargin(i);
        refresh.setBorder(new EmptyBorder(0, 0, 0, 0));
        add(download);
        add(upload);
        add(delete);
        add(newFolder);
        add(refresh);
        upload.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                JMenuItem item = (JMenuItem)e.getSource();
                up.show(item, item.getLocation().x - item.getBounds().x, item.getY() + 20);
            }

            final FileMenuPanel this$0;

            
            {
                this$0 = FileMenuPanel.this;
                super();
            }
        }
);
        download.addMouseListener(new MenuMouseListener(download));
        upload.addMouseListener(new MenuMouseListener(upload));
        delete.addMouseListener(new MenuMouseListener(delete));
        newFolder.addMouseListener(new MenuMouseListener(newFolder));
        refresh.addMouseListener(new MenuMouseListener(refresh));
        setLayout(layout);
        items = new JMenuItem[7];
        items[0] = download;
        items[1] = upload;
        items[2] = newFolder;
        items[3] = delete;
        items[4] = up.uploadFiles;
        items[5] = up.uploadFolder;
        items[6] = refresh;
    }

    public void disableAllButtons()
    {
        JMenuItem ajmenuitem[];
        int j = (ajmenuitem = items).length;
        for(int i = 0; i < j; i++)
        {
            JMenuItem item = ajmenuitem[i];
            item.setEnabled(false);
        }

    }

    public void enableAllButtons()
    {
        JMenuItem ajmenuitem[];
        int j = (ajmenuitem = items).length;
        for(int i = 0; i < j; i++)
        {
            JMenuItem item = ajmenuitem[i];
            item.setEnabled(true);
        }

    }

    public void addActionListener(ActionListener listener)
    {
        download.addActionListener(listener);
        upload.addActionListener(listener);
        newFolder.addActionListener(listener);
        refresh.addActionListener(listener);
        delete.addActionListener(listener);
        up.uploadFiles.addActionListener(listener);
        up.uploadFolder.addActionListener(listener);
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
        FileMenuPanel fm = new FileMenuPanel();
        fm.init();
        frame.add(fm);
    }

    private static final long serialVersionUID = 0xf20da08515c3a9b7L;
    private JMenuItem download;
    private JMenuItem upload;
    private JMenuItem delete;
    private JMenuItem newFolder;
    private JMenuItem refresh;
    private JMenuItem items[];
    private UploadPannel up;

}
