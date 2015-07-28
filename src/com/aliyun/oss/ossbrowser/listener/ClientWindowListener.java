// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClientWindowListener.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.oss.ossbrowser.utils.ImageResource;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ClientWindowListener extends WindowAdapter
{

    public ClientWindowListener(final JFrame jFrame)
    {
        trayIcon = null;
        tray = null;
        frame = null;
        if(SystemTray.isSupported())
        {
            frame = jFrame;
            PopupMenu pop = new PopupMenu();
            MenuItem exit = new MenuItem("\u9000\u51FA");
            exit.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }

                final ClientWindowListener this$0;

            
            {
                this$0 = ClientWindowListener.this;
                super();
            }
            }
);
            MenuItem show = new MenuItem("\u6253\u5F00\u4E3B\u9762\u677F");
            show.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    jFrame.setExtendedState(0);
                    jFrame.setVisible(true);
                    jFrame.toFront();
                }

                final ClientWindowListener this$0;
                private final JFrame val$jFrame;

            
            {
                this$0 = ClientWindowListener.this;
                jFrame = jframe;
                super();
            }
            }
);
            pop.add(show);
            pop.add(exit);
            tray = SystemTray.getSystemTray();
            trayIcon = new TrayIcon(ImageResource.trayIcon.getImage(), "OSSBrowser", pop);
            trayIcon.addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e)
                {
                    if(e.getClickCount() == 2)
                    {
                        jFrame.setExtendedState(0);
                        jFrame.setVisible(true);
                        jFrame.toFront();
                    }
                }

                final ClientWindowListener this$0;
                private final JFrame val$jFrame;

            
            {
                this$0 = ClientWindowListener.this;
                jFrame = jframe;
                super();
            }
            }
);
            try
            {
                tray.add(trayIcon);
            }
            catch(AWTException e1)
            {
                e1.printStackTrace();
            }
        }
    }

    public void windowIconified(WindowEvent e)
    {
        if(SystemTray.isSupported())
            frame.setVisible(false);
        else
            super.windowIconified(e);
    }

    TrayIcon trayIcon;
    SystemTray tray;
    Frame frame;
}
