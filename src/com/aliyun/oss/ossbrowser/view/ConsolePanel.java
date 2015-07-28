// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConsolePanel.java

package com.aliyun.oss.ossbrowser.view;

import com.aliyun.oss.ossbrowser.listener.ConsolePanelChangeListener;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import javax.swing.JTabbedPane;

// Referenced classes of package com.aliyun.oss.ossbrowser.view:
//            TasksPanel, HttpHeadersPanel, PropertyPanel, LogPanel, 
//            SearchPanel, SyncPanel

public class ConsolePanel extends JTabbedPane
{

    public ConsolePanel()
    {
    }

    public void init(ResourceManager resourceManager)
    {
        tasksPanel = new TasksPanel();
        httpHeadPanel = new HttpHeadersPanel();
        propertiesPanel = new PropertyPanel();
        logPanel = new LogPanel();
        searchPanel = new SearchPanel();
        syncPanel = new SyncPanel();
        tasksPanel.init(resourceManager);
        httpHeadPanel.init();
        propertiesPanel.init();
        logPanel.init();
        searchPanel.init();
        syncPanel.init();
        addTab("\u4EFB\u52A1", tasksPanel);
        addTab("Http\u8868\u5934", httpHeadPanel);
        addTab("\u5C5E\u6027", propertiesPanel);
        addTab("\u65E5\u5FD7", logPanel);
        addTab("\u641C\u7D22", searchPanel);
        addTab("\u540C\u6B65", syncPanel);
        setTabPlacement(1);
        addChangeListener(new ConsolePanelChangeListener(resourceManager));
    }

    public TasksPanel getTasksPanel()
    {
        return tasksPanel;
    }

    public HttpHeadersPanel getHttpHeadPanel()
    {
        return httpHeadPanel;
    }

    public PropertyPanel getPropertiesPanel()
    {
        return propertiesPanel;
    }

    public LogPanel getLogPanel()
    {
        return logPanel;
    }

    public SearchPanel getSearchPanel()
    {
        return searchPanel;
    }

    public SyncPanel getSyncPanel()
    {
        return syncPanel;
    }

    private static final long serialVersionUID = 0xd6480ad29065ff8fL;
    public static final int TASK_PANEL = 0;
    public static final int HTTP_HEAD_PANEL = 1;
    public static final int PROPERTY_PANEL = 2;
    public static final int EVENT_PANEL = 0;
    private TasksPanel tasksPanel;
    private HttpHeadersPanel httpHeadPanel;
    private PropertyPanel propertiesPanel;
    private LogPanel logPanel;
    private SearchPanel searchPanel;
    private SyncPanel syncPanel;
}
