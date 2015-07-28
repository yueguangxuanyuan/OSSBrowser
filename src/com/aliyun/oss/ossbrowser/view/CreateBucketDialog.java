// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CreateBucketDialog.java

package com.aliyun.oss.ossbrowser.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CreateBucketDialog extends JDialog
{

    public static void main(String args[])
    {
        try
        {
            CreateBucketDialog dialog = new CreateBucketDialog(new JFrame());
            dialog.setDefaultCloseOperation(1);
            dialog.setVisible(true);
            dialog.setPreferredSize(new Dimension(400, 200));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public CreateBucketDialog(JFrame frame)
    {
        super(frame);
        setBounds(100, 100, 450, 160);
        setTitle("\u521B\u5EFABucket");
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel north = new JPanel();
        north.setPreferredSize(new Dimension(200, 40));
        contentPanel.add(north, "North");
        getContentPane().add(contentPanel, "Center");
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[8];
        gbl_contentPanel.rowHeights = new int[7];
        gbl_contentPanel.columnWeights = (new double[] {
            0.0D, 0.0D, 1.0D, 1.0D, 0.0D, 0.0D, 1.0D, 4.9406564584124654E-324D
        });
        gbl_contentPanel.rowWeights = (new double[] {
            0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9406564584124654E-324D
        });
        contentPanel.setLayout(gbl_contentPanel);
        JLabel lblNewLabel = new JLabel("Bucket\u540D\u79F0:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = 17;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 2;
        contentPanel.add(lblNewLabel, gbc_lblNewLabel);
        textField = new JTextField(20);
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = 2;
        gbc_textField.anchor = 16;
        gbc_textField.gridwidth = 4;
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.gridx = 2;
        gbc_textField.gridy = 2;
        contentPanel.add(textField, gbc_textField);
        textField.setColumns(10);
        textField.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e)
            {
                if(textField.getText().length() >= 3)
                    okButton.setEnabled(true);
                else
                    okButton.setEnabled(false);
            }

            public void keyReleased(KeyEvent keyevent)
            {
            }

            public void keyPressed(KeyEvent keyevent)
            {
            }

            final CreateBucketDialog this$0;

            
            {
                this$0 = CreateBucketDialog.this;
                super();
            }
        }
);
        JLabel lblBucketAccessContrl = new JLabel("\u8BBF\u95EE\u6743\u9650:");
        GridBagConstraints gbc_lblBucketAccessContrl = new GridBagConstraints();
        gbc_lblBucketAccessContrl.insets = new Insets(0, 0, 0, 5);
        gbc_lblBucketAccessContrl.gridx = 1;
        gbc_lblBucketAccessContrl.gridy = 5;
        contentPanel.add(lblBucketAccessContrl, gbc_lblBucketAccessContrl);
        comboBox = new JComboBox();
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.insets = new Insets(0, 0, 0, 5);
        gbc_comboBox.fill = 2;
        gbc_comboBox.gridx = 3;
        gbc_comboBox.gridy = 5;
        contentPanel.add(comboBox, gbc_comboBox);
        comboBox.addItem("private");
        comboBox.addItem("public-read");
        comboBox.addItem("public-read-write");
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(2));
        getContentPane().add(buttonPane, "South");
        okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
        okButton.setEnabled(false);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
            }

            final CreateBucketDialog this$0;

            
            {
                this$0 = CreateBucketDialog.this;
                super();
            }
        }
);
    }

    public void addActionListener(ActionListener listener)
    {
        okButton.addActionListener(listener);
    }

    public JTextField getTextField()
    {
        return textField;
    }

    public void setTextField(JTextField textField)
    {
        this.textField = textField;
    }

    public JComboBox getComboBox()
    {
        return comboBox;
    }

    public void setComboBox(JComboBox comboBox)
    {
        this.comboBox = comboBox;
    }

    public JButton getOkButton()
    {
        return okButton;
    }

    public void setOkButton(JButton okButton)
    {
        this.okButton = okButton;
    }

    private static final long serialVersionUID = 0x5d413ff03001fc13L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textField;
    private JComboBox comboBox;
    private JButton okButton;


}
