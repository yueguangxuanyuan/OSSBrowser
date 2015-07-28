// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoginDialog.java

package com.aliyun.oss.ossbrowser.view;

import com.aliyun.oss.ossbrowser.utils.ImageResource;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import javax.swing.*;
import org.apache.commons.codec.binary.Base64;

// Referenced classes of package com.aliyun.oss.ossbrowser.view:
//            LoginPanel

public class LoginDialog extends JDialog
{

    public LoginDialog()
    {
        setTitle("\u767B\u9646");
        loginPanel = new LoginPanel();
        loginPending = new JPanel();
        loginPending.add(new JLabel(ImageResource.loadingIcon));
        add(loginPanel, "Center");
        setPreferredSize(new Dimension(330, 250));
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 330) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 230) / 2;
        setLocation(w, h);
        pack();
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e)
            {
                dispose();
            }

            final LoginDialog this$0;

            
            {
                this$0 = LoginDialog.this;
                super();
            }
        }
);
    }

    public void switchToPendingPanel()
    {
        add(loginPending, "Center");
    }

    public void switchToLoginPanel()
    {
        add(loginPanel, "Center");
    }

    public LoginPanel getLoginPanel()
    {
        return loginPanel;
    }

    public static void main(String args[])
    {
        byte s[] = Base64.encodeBase64("9/2Q9j15zEPUOKfD0Bu0grVNWAo=".getBytes());
        try
        {
            byte e[] = Base64.decodeBase64(s);
            System.out.println(new String(s));
            System.err.println(new String(e, "utf-8"));
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    private static final long serialVersionUID = 0x90b0397fc6bc5b0aL;
    private LoginPanel loginPanel;
    private JPanel loginPending;
}
