// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoginPanel.java

package com.aliyun.oss.ossbrowser.view;

import com.aliyun.oss.ossbrowser.utils.RSAEncrypt;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.prefs.Preferences;
import javax.swing.*;

public class LoginPanel extends JPanel {

	public LoginPanel() {
		prefs = Preferences.userRoot().node("ossbrowser/login");
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		JLabel lblAcccessId = new JLabel("\u8D26\u53F7:");
		springLayout.putConstraint("North", lblAcccessId, 40, "North", this);
		springLayout.putConstraint("West", lblAcccessId, 30, "West", this);
		add(lblAcccessId);
		JLabel lblAccessKey = new JLabel("\u5BC6\u7801:");
		springLayout.putConstraint("North", lblAccessKey, 30, "South",
				lblAcccessId);
		springLayout.putConstraint("West", lblAccessKey, 0, "West",
				lblAcccessId);
		add(lblAccessKey);
		accessID = new JTextField(40);
		springLayout.putConstraint("East", accessID, -30, "East", this);
		springLayout.putConstraint("North", accessID, 0, "North", lblAcccessId);
		accessID.setPreferredSize(new Dimension(40, 20));
		add(accessID);
		accessKey = new JPasswordField(40);
		springLayout.putConstraint("West", accessKey, 15, "East", lblAccessKey);
		springLayout.putConstraint("East", accessKey, 0, "East", accessID);
		springLayout
				.putConstraint("North", accessKey, 0, "North", lblAccessKey);
		springLayout.putConstraint("West", accessID, 0, "West", accessKey);
		add(accessKey);
		boolean rememberMe = prefs.getBoolean("LOGIN_REMEMBER_ME", false);
		boolean inc = prefs.getBoolean("LOGIN_INC", false);
		boolean assignHost = prefs.getBoolean("ASSIGN_HOST", false);
		ckbRememberMe = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
		ckbInc = new JCheckBox("\u5185\u7F51\u767B\u9646(\u4E91\u4E3B\u673A)");
		ckbAssignHost = new JCheckBox();
		host = new JTextField(17);
		if (assignHost) {
			inc = false;
			host.setText(prefs.get("ASSIGN_HOST_STRING", ""));
		} else {
			host.setEditable(false);
			host.setText("\u6307\u5B9A\u57DF\u540D");
		}
		ckbRememberMe.setSelected(rememberMe);
		ckbInc.setSelected(inc);
		ckbAssignHost.setSelected(assignHost);
		loginButton = new JButton("\u767B\u9646");
		add(loginButton);
		loginButton.setEnabled(false);
		JButton cancelButton = new JButton("\u53D6\u6D88");
		springLayout.putConstraint("West", ckbRememberMe, 0, "West", accessKey);
		springLayout.putConstraint("North", ckbRememberMe, 7, "South",
				accessKey);
		springLayout.putConstraint("East", loginButton, -6, "West",
				cancelButton);
		springLayout.putConstraint("North", loginButton, 7, "South",
				ckbAssignHost);
		springLayout.putConstraint("East", cancelButton, 0, "East", accessKey);
		springLayout.putConstraint("North", cancelButton, 0, "North",
				loginButton);
		springLayout.putConstraint("West", ckbInc, 7, "East", ckbRememberMe);
		springLayout.putConstraint("North", ckbInc, 0, "North", ckbRememberMe);
		springLayout.putConstraint("West", ckbAssignHost, 0, "West",
				ckbRememberMe);
		springLayout.putConstraint("North", ckbAssignHost, 7, "South",
				ckbRememberMe);
		springLayout.putConstraint("North", host, 0, "North", ckbAssignHost);
		springLayout.putConstraint("West", host, 0, "East", ckbAssignHost);
		add(ckbRememberMe);
		add(ckbInc);
		add(ckbAssignHost);
		add(host);
		add(cancelButton);
		String id = prefs.get("LOGIN_ACCESS_ID", "");
		accessID.setText(id);
		if (rememberMe)
			try {
				String key = prefs.get("LOGIN_ACCESS_KEY", "");
				accessKey.setText(RSAEncrypt.decrypt(key));
				loginButton.setEnabled(true);
			} catch (Exception e) {
				accessID.setText("");
				accessKey.setText("");
			}
		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

			final LoginPanel this$0;

			{
				this$0 = LoginPanel.this;
				super();
			}
		});
		accessID.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				if (accessID.getText().length() > 5
						&& accessKey.getText().length() > 5)
					loginButton.setEnabled(true);
				else
					loginButton.setEnabled(false);
			}

			final LoginPanel this$0;

			{
				this$0 = LoginPanel.this;
				super();
			}
		});
		accessKey.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				if (accessID.getText().length() > 5
						&& accessKey.getText().length() > 5)
					loginButton.setEnabled(true);
				else
					loginButton.setEnabled(false);
			}

			final LoginPanel this$0;

			{
				this$0 = LoginPanel.this;
				super();
			}
		});
		ckbAssignHost.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				JCheckBox box = (JCheckBox) e.getItemSelectable();
				if (box.isSelected()) {
					ckbInc.setSelected(false);
					host.setText(prefs.get("ASSIGN_HOST_STRING", ""));
					host.setEditable(true);
				} else {
					host.setEditable(false);
					host.setText("\u6307\u5B9A\u57DF\u540D");
				}
			}

			final LoginPanel this$0;

			{
				this$0 = LoginPanel.this;
				super();
			}
		});
	}

	public JTextField getAccesssID() {
		return accessID;
	}

	public JTextField getAccessKey() {
		return accessKey;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public boolean isRememberMe() {
		return ckbRememberMe.isSelected();
	}

	public boolean isInc() {
		return ckbInc.isSelected();
	}

	public boolean isAssignHost() {
		return ckbAssignHost.isSelected();
	}

	public String getAssignHost() {
		if (ckbAssignHost.isSelected())
			return host.getText();
		else
			return "";
	}

	public Preferences getPreferences() {
		return prefs;
	}

	private static final long serialVersionUID = 0x89f9ada547919e07L;
	private JTextField accessID;
	private JTextField accessKey;
	private JTextField host;
	private Preferences prefs;
	JButton loginButton;
	JCheckBox ckbRememberMe;
	JCheckBox ckbInc;
	JCheckBox ckbAssignHost;

}
