// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogIconListItem.java

package com.aliyun.oss.ossbrowser.model;

import javax.swing.Icon;

public class LogIconListItem
{

    public LogIconListItem(Icon icon, String text)
    {
        this.icon = icon;
        this.text = text;
    }

    public Icon getIcon()
    {
        return icon;
    }

    public void setIcon(Icon icon)
    {
        this.icon = icon;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    private Icon icon;
    private String text;
}
