// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MenuMouseListener.java

package com.aliyun.oss.ossbrowser.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;

public class MenuMouseListener
    implements MouseListener
{

    public MenuMouseListener(JMenuItem menuItem)
    {
        this.menuItem = menuItem;
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mousePressed(MouseEvent e)
    {
        menuItem.setArmed(true);
    }

    public void mouseReleased(MouseEvent e)
    {
        menuItem.setArmed(true);
    }

    public void mouseEntered(MouseEvent e)
    {
        menuItem.setArmed(true);
    }

    public void mouseExited(MouseEvent e)
    {
        menuItem.setArmed(false);
    }

    private JMenuItem menuItem;
}
