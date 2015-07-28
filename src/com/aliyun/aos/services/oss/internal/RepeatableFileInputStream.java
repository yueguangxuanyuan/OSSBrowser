// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RepeatableFileInputStream.java

package com.aliyun.aos.services.oss.internal;

import java.io.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RepeatableFileInputStream extends InputStream
{

    public RepeatableFileInputStream(File file)
        throws FileNotFoundException
    {
        this.file = null;
        fis = null;
        bytesReadPastMarkPoint = 0L;
        markPoint = 0L;
        if(file == null)
        {
            throw new IllegalArgumentException("File cannot be null");
        } else
        {
            fis = new FileInputStream(file);
            this.file = file;
            return;
        }
    }

    public void reset()
        throws IOException
    {
        fis.close();
        fis = new FileInputStream(file);
        long skipped = 0L;
        for(long toSkip = markPoint; toSkip > 0L; toSkip -= skipped)
            skipped = fis.skip(toSkip);

        if(log.isDebugEnabled())
            log.debug((new StringBuilder("Reset to mark point ")).append(markPoint).append(" after returning ").append(bytesReadPastMarkPoint).append(" bytes").toString());
        bytesReadPastMarkPoint = 0L;
    }

    public boolean markSupported()
    {
        return true;
    }

    public void mark(int readlimit)
    {
        markPoint += bytesReadPastMarkPoint;
        bytesReadPastMarkPoint = 0L;
        if(log.isDebugEnabled())
            log.debug((new StringBuilder("Input stream marked at ")).append(markPoint).append(" bytes").toString());
    }

    public int available()
        throws IOException
    {
        return fis.available();
    }

    public void close()
        throws IOException
    {
        fis.close();
    }

    public int read()
        throws IOException
    {
        int byteRead = fis.read();
        if(byteRead != -1)
        {
            bytesReadPastMarkPoint++;
            return byteRead;
        } else
        {
            return -1;
        }
    }

    public long skip(long n)
        throws IOException
    {
        long skipped = fis.skip(n);
        bytesReadPastMarkPoint += skipped;
        return skipped;
    }

    public int read(byte arg0[], int arg1, int arg2)
        throws IOException
    {
        int count = fis.read(arg0, arg1, arg2);
        bytesReadPastMarkPoint += count;
        return count;
    }

    public InputStream getWrappedInputStream()
    {
        return fis;
    }

    private static final Log log = LogFactory.getLog("com.aliyun.aos.services.oss.internal.RepeatableFileInputStream");
    private File file;
    private FileInputStream fis;
    private long bytesReadPastMarkPoint;
    private long markPoint;

}
