// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoginController.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.oss.ossbrowser.control.LoginTask;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.view.LoginDialog;
import com.aliyun.oss.ossbrowser.view.LoginPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import javax.swing.JButton;

public class LoginController
    implements ActionListener
{

    public void actionPerformed(ActionEvent e)
    {
        resourceManager.getLoginDialog().switchToPendingPanel();
        resourceManager.getLoginDialog().getLoginPanel().getLoginButton().setEnabled(false);
        resourceManager.getService().submit(new LoginTask(resourceManager));
    }

    public LoginController(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    private ResourceManager resourceManager;
}
