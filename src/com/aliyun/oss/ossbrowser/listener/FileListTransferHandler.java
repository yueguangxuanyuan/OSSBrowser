// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileListTransferHandler.java

package com.aliyun.oss.ossbrowser.listener;

import com.aliyun.aos.services.oss.IOSSClient;
import com.aliyun.aos.services.oss.model.GetObjectRequest;
import com.aliyun.aos.services.oss.model.OSSObjectSummary;
import com.aliyun.oss.ossbrowser.model.IconAndObjectSummary;
import com.aliyun.oss.ossbrowser.utils.ResourceManager;
import com.aliyun.oss.ossbrowser.utils.Utils;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

public class FileListTransferHandler extends TransferHandler
{

    public FileListTransferHandler(ResourceManager resourceManager)
    {
        this.resourceManager = resourceManager;
    }

    protected Transferable createTransferable(JComponent c)
    {
        Transferable packet = null;
        if(c instanceof JTable)
        {
            final JTable table = (JTable)c;
            final int indexs[] = ((JTable)c).getSelectedRows();
            final File tempFolder = createTempFolder();
            tempFolder.mkdirs();
            c.putClientProperty("TransferedFile", tempFolder);
            packet = new Transferable() {

                public DataFlavor[] getTransferDataFlavors()
                {
                    return (new DataFlavor[] {
                        DataFlavor.javaFileListFlavor
                    });
                }

                public boolean isDataFlavorSupported(DataFlavor flavor)
                {
                    return flavor.equals(DataFlavor.javaFileListFlavor);
                }

                public Object getTransferData(DataFlavor flavor)
                    throws UnsupportedFlavorException, IOException
                {
                    int ai[];
                    int j = (ai = indexs).length;
                    for(int i = 0; i < j; i++)
                    {
                        int index = ai[i];
                        IconAndObjectSummary item = (IconAndObjectSummary)table.getValueAt(index, table.convertColumnIndexToView(0));
                        String key = item.getSummary().getKey();
                        String path = (new StringBuilder(String.valueOf(tempFolder.getAbsolutePath()))).append("/").append(Utils.getFilename(key)).toString();
                        if(!key.equals("..") && !key.endsWith("/"))
                        {
                            String bucketName = item.getSummary().getBucketName();
                            GetObjectRequest requst = new GetObjectRequest(bucketName, key);
                            resourceManager.getOssClient().getObject(requst, new File(path));
                        }
                    }

                    if(tempFolder.listFiles().length == 0)
                        return null;
                    java.util.List data = new ArrayList();
                    File afile[];
                    int l = (afile = tempFolder.listFiles()).length;
                    for(int k = 0; k < l; k++)
                    {
                        File file = afile[k];
                        data.add(file);
                    }

                    return data;
                }

                final FileListTransferHandler this$0;
                private final int val$indexs[];
                private final JTable val$table;
                private final File val$tempFolder;

            
            {
                this$0 = FileListTransferHandler.this;
                indexs = ai;
                table = jtable;
                tempFolder = file;
                super();
            }
            }
;
            return packet;
        } else
        {
            return packet;
        }
    }

    protected void exportDone(JComponent source, Transferable data, int action)
    {
        File f = (File)source.getClientProperty("TransferedFile");
        if(f != null && f.exists())
            deleteFile(f);
    }

    public int getSourceActions(JComponent c)
    {
        return 2;
    }

    private File createTempFolder()
    {
        Date date = new Date();
        return new File((new StringBuilder(String.valueOf(SYS_TEMP))).append(File.separator).append("ossbrowser").append(File.separator).append(date.getTime()).toString());
    }

    public static void deleteFile(File root)
    {
        if(root.isFile())
        {
            root.delete();
            return;
        }
        File afile[];
        int j = (afile = root.listFiles()).length;
        for(int i = 0; i < j; i++)
        {
            File file = afile[i];
            deleteFile(file);
        }

        root.delete();
    }

    public static void main(String args[])
    {
        Date date = new Date();
        File f = new File((new StringBuilder(String.valueOf(SYS_TEMP))).append(File.separator).append("ossbrowser").append(File.separator).append(date.getTime()).toString());
        System.out.println(f.getAbsolutePath());
    }

    private static final long serialVersionUID = 0x9c504d426968110L;
    private static final String TRANSFER_FILE_PROPERTY = "TransferedFile";
    private static final String SYS_TEMP = System.getProperty("java.io.tmpdir");
    private ResourceManager resourceManager;


}
