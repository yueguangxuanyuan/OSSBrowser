// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenerateUrlButtonListener.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.aos.HttpMethod;
import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.utils.Utils;
import com.aliyun.oss.ossbrowser.view.ConsolePanel;
import com.aliyun.oss.ossbrowser.view.HttpHeadersPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Date;
import javax.swing.JOptionPane;

public class GenerateUrlButtonListener
    implements ActionListener
{

    public GenerateUrlButtonListener(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    public void actionPerformed(ActionEvent e)
    {
        String bucket = Utils.getCurrentBucketName(resourceManager);
        String object = Utils.getCurrentObject(resourceManager);
        String time = JOptionPane.showInputDialog("\u8FDE\u63A5\u6709\u6548\u65F6\u95F4(\u79D2)");
        if(time != null)
        {
            long t = 0L;
            try
            {
                t = Long.valueOf(time).longValue();
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(resourceManager.getMainFrame(), "\u8BF7\u8F93\u5165\u5927\u4E8E0\u7684\u6574\u6570", "\u9519\u8BEF", 0);
                return;
            }
            if(t < 0L)
            {
                JOptionPane.showMessageDialog(resourceManager.getMainFrame(), "\u8BF7\u8F93\u5165\u5927\u4E8E0\u7684\u6574\u6570", "\u9519\u8BEF", 0);
                return;
            }
            Date date = new Date((new Date()).getTime() + t * 1000L);
            URL url = resourceManager.getOssClient().generatePresignedUrl(bucket, object, date, HttpMethod.GET);
            resourceManager.getConsolePanel().getHttpHeadPanel().setUrl(url.toString());
        }
    }

    ResourceManager resourceManager;
}
