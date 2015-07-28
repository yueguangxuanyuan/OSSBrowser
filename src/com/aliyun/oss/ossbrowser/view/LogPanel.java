// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogPanel.java

package com.aliyun.oss.ossbrowser.view;

import com.aliyun.oss.ossbrowser.model.*;
import com.aliyun.oss.ossbrowser.utils.ImageResource;
import java.awt.BorderLayout;
import javax.swing.*;

public class LogPanel extends JPanel
{

    public LogPanel()
    {
    }

    public void init()
    {
        model = new DefaultListModel();
        logList = new JList();
        logList.setOpaque(false);
        logList.setCellRenderer(new LogIconListItemRenderer());
        logList.setModel(model);
        logList.setSelectionMode(0);
        logList.setDoubleBuffered(true);
        panel = new JScrollPane(logList);
        scrollBar = panel.getVerticalScrollBar();
        setLayout(new BorderLayout());
        add(panel);
    }

    public DefaultListModel getListModel()
    {
        return model;
    }

    public synchronized void addLog(LogLevel type, String text)
    {
        Icon icon = null;
        switch($SWITCH_TABLE$com$aliyun$oss$ossbrowser$model$LogLevel()[type.ordinal()])
        {
        case 1: // '\001'
            icon = ImageResource.logWarningIcon;
            break;

        case 2: // '\002'
        case 3: // '\003'
            icon = ImageResource.logErrorIcon;
            break;
        }
        final LogIconListItem ic = new LogIconListItem(icon, text);
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                model.addElement(ic);
            }

            final LogPanel this$0;
            private final LogIconListItem val$ic;

            
            {
                this$0 = LogPanel.this;
                ic = logiconlistitem;
                super();
            }
        }
);
        scrollBar.setValue(scrollBar.getMaximum());
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
        LogPanel log = new LogPanel();
        log.init();
        frame.add(log);
        log.addLog(LogLevel.ERROR, "dfsd");
    }

    static int[] $SWITCH_TABLE$com$aliyun$oss$ossbrowser$model$LogLevel()
    {
        $SWITCH_TABLE$com$aliyun$oss$ossbrowser$model$LogLevel;
        if($SWITCH_TABLE$com$aliyun$oss$ossbrowser$model$LogLevel == null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        JVM INSTR pop ;
        int ai[] = new int[LogLevel.values().length];
        try
        {
            ai[LogLevel.ERROR.ordinal()] = 3;
        }
        catch(NoSuchFieldError _ex) { }
        try
        {
            ai[LogLevel.INFO.ordinal()] = 1;
        }
        catch(NoSuchFieldError _ex) { }
        try
        {
            ai[LogLevel.WARNING.ordinal()] = 2;
        }
        catch(NoSuchFieldError _ex) { }
        return $SWITCH_TABLE$com$aliyun$oss$ossbrowser$model$LogLevel = ai;
    }

    private static final long serialVersionUID = 0x2c02d622d030de80L;
    private JList logList;
    private DefaultListModel model;
    private JScrollPane panel;
    private JScrollBar scrollBar;
    private static int $SWITCH_TABLE$com$aliyun$oss$ossbrowser$model$LogLevel[];

}
