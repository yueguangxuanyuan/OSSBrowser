// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HttpHeadersPanel.java

package com.aliyun.oss.ossbrowser.view;

import com.aliyun.oss.ossbrowser.model.HeaderTableModel;
import java.awt.BorderLayout;
import javax.swing.*;

public class HttpHeadersPanel extends JPanel
{

    public HttpHeadersPanel()
    {
    }

    public void init()
    {
        JPanel textPanel = new JPanel();
        headerTable = new JTable();
        url = new JLabel("URL\u5730\u5740 ");
        urlInput = new JTextField(100);
        urlInput.setEditable(false);
        generateUrl = new JButton("\u751F\u6210\u52A0\u5BC6\u8FDE\u63A5");
        textPanel.setLayout(new BorderLayout());
        textPanel.add(url, "West");
        textPanel.add(urlInput, "Center");
        textPanel.add(generateUrl, "East");
        tableContainer = new JScrollPane(headerTable);
        tableModel = new HeaderTableModel();
        String as[];
        int j = (as = columns).length;
        for(int i = 0; i < j; i++)
        {
            String str = as[i];
            tableModel.addColumn(str);
        }

        headerTable.setDefaultRenderer(java/lang/String, null);
        headerTable.setModel(tableModel);
        setLayout(new BorderLayout());
        add(textPanel, "North");
        add(tableContainer, "Center");
    }

    public HeaderTableModel getTableModel()
    {
        return tableModel;
    }

    public void setUrl(String bucketName, String key)
    {
        setUrl((new StringBuilder("http://oss.aliyuncs.com/")).append(bucketName).append("/").append(key).toString());
    }

    public synchronized void setUrl(String url)
    {
        urlInput.setText(url);
    }

    public JButton getGenerateUrlButton()
    {
        return generateUrl;
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
        HttpHeadersPanel console = new HttpHeadersPanel();
        console.init();
        frame.add(console);
        frame.pack();
    }

    private static final long serialVersionUID = 0xd0a564523156ced0L;
    private JTable headerTable;
    private JLabel url;
    private JTextField urlInput;
    private JScrollPane tableContainer;
    private JButton generateUrl;
    private String columns[] = {
        "Http\u8868\u5934", "\u53C2\u6570\u503C"
    };
    private HeaderTableModel tableModel;
}
