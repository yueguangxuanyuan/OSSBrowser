// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileListTablePanel.java

package com.aliyun.oss.ossbrowser.view;

import com.aliyun.oss.ossbrowser.model.*;
import com.aliyun.oss.ossbrowser.utils.ImageResource;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintStream;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

// Referenced classes of package com.aliyun.oss.ossbrowser.view:
//            FileMenuPanel

public class FileListTablePanel extends JPanel
{

    public FileListTablePanel()
    {
        fileTable = null;
        model = null;
    }

    public void init()
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(850, 28));
        searchInput = new JTextField();
        JPanel pathPanel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel(new BorderLayout());
        pathPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
        searchPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
        text = new JLabel(" \u5F53\u524D\u8DEF\u5F84:  ");
        path = new JTextField("");
        path.setEditable(true);
        path.setPreferredSize(new Dimension(230, 24));
        searchInput.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent keyevent)
            {
            }

            public void keyReleased(KeyEvent keyevent)
            {
            }

            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == 10)
                    searchButton.doClick();
            }

            final FileListTablePanel this$0;

            
            {
                this$0 = FileListTablePanel.this;
                super();
            }
        }
);
        pathPanel.add(text, "West");
        pathPanel.add(path, "Center");
        searchPanel.setSize(258, 24);
        searchInput.setPreferredSize(new Dimension(234, 24));
        searchButton = new JButton(ImageResource.findIcon);
        searchButton.setToolTipText("search");
        searchButton.setEnabled(true);
        searchPanel.add(searchInput, "Center");
        searchPanel.add(searchButton, "East");
        fileMenu = new FileMenuPanel();
        fileMenu.init();
        panel.add(pathPanel, "Center");
        panel.add(searchPanel, "East");
        setLayout(new BorderLayout());
        add(panel, "North");
        model = new FileTableModel();
        fileTable = new JTable(model);
        fileTable.setColumnSelectionAllowed(false);
        fileTable.setRowHeight(16);
        for(int i = 0; i < columnName.length; i++)
            model.addColumn(columnName[i]);

        add(new JScrollPane(fileTable));
        fileTable.getColumn("\u6587\u4EF6\u540D").setCellRenderer(new FileIconCellRender(false));
        fileTable.getColumn("\u5927\u5C0F").setCellRenderer(new FileIconCellRender(false));
        TableRowSorter sorter = new TableRowSorter(model);
        sorter.setComparator(0, new FileNameComparator());
        sorter.setComparator(1, new SizeComparator());
        sorter.setComparator(2, new StringComparator());
        sorter.setComparator(3, new StringComparator());
        fileTable.setRowSorter(sorter);
        add(fileMenu, "South");
        setBorder(new TitledBorder("\u6587\u4EF6\u7BA1\u7406"));
    }

    public String getSearchInput()
    {
        return searchInput.getText();
    }

    public FileMenuPanel getFileMenu()
    {
        return fileMenu;
    }

    public JTable getFileTable()
    {
        return fileTable;
    }

    public FileTableModel getModel()
    {
        return model;
    }

    public void setPath(String path)
    {
        this.path.setText(path);
    }

    public String getPath()
    {
        return path.getText();
    }

    public JButton getSearchButton()
    {
        return searchButton;
    }

    public void addPathListener(KeyListener listener)
    {
        path.addKeyListener(listener);
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(850, 200);
        frame.setDefaultCloseOperation(3);
        FileListTablePanel fp = new FileListTablePanel();
        fp.init();
        frame.add(fp);
        String s = "/a/b/c/";
        String ss[] = s.split("/");
        String args1[];
        int j = (args1 = ss).length;
        for(int i = 0; i < j; i++)
        {
            String k = args1[i];
            System.out.println(k.length());
        }

    }

    private static final long serialVersionUID = 0x6a3cd6562b03afa6L;
    private JTable fileTable;
    private FileTableModel model;
    private String columnName[] = {
        "\u6587\u4EF6\u540D", "\u5927\u5C0F", "ETag", "\u6700\u540E\u4FEE\u6539\u65F6\u95F4"
    };
    private JTextField searchInput;
    private JButton searchButton;
    private JLabel text;
    private JTextField path;
    private FileMenuPanel fileMenu;

}
