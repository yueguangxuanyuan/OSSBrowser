// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProgressReportingInputStream.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.services.oss.model.ProgressEvent;
import com.aliyun.aos.services.oss.model.ProgressListener;
import java.io.*;

public class ProgressReportingInputStream extends FilterInputStream
{

    public ProgressReportingInputStream(InputStream in, ProgressListener listener)
    {
        super(in);
        this.listener = listener;
    }

    public int read()
        throws IOException
    {
        int data = super.read();
        if(data != -1)
            notify(1);
        return data;
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        int bytesRead = super.read(b, off, len);
        if(bytesRead != -1)
            notify(bytesRead);
        return bytesRead;
    }

    public void close()
        throws IOException
    {
        if(unnotifiedByteCount > 0)
        {
            listener.progressChanged(new ProgressEvent(unnotifiedByteCount));
            unnotifiedByteCount = 0;
        }
        super.close();
    }

    private void notify(int bytesRead)
    {
        unnotifiedByteCount += bytesRead;
        if(unnotifiedByteCount >= 8192)
        {
            listener.progressChanged(new ProgressEvent(unnotifiedByteCount));
            unnotifiedByteCount = 0;
        }
    }

    private static final int NOTIFICATION_THRESHOLD = 8192;
    private final ProgressListener listener;
    private int unnotifiedByteCount;
}
