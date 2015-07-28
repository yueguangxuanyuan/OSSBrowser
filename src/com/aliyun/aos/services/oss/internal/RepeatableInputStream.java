// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RepeatableInputStream.java

package com.aliyun.aos.services.oss.internal;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RepeatableInputStream extends InputStream
{

    public RepeatableInputStream(InputStream inputStream, int bufferSize)
    {
        is = null;
        this.bufferSize = 0;
        bufferOffset = 0;
        bytesReadPastMark = 0L;
        buffer = null;
        if(inputStream == null)
            throw new IllegalArgumentException("InputStream cannot be null");
        is = inputStream;
        this.bufferSize = bufferSize;
        buffer = new byte[this.bufferSize];
        if(log.isDebugEnabled())
            log.debug((new StringBuilder("Underlying input stream will be repeatable up to ")).append(buffer.length).append(" bytes").toString());
    }

    public void reset()
        throws IOException
    {
        if(bytesReadPastMark <= (long)bufferSize)
        {
            if(log.isDebugEnabled())
                log.debug((new StringBuilder("Reset after reading ")).append(bytesReadPastMark).append(" bytes.").toString());
            bufferOffset = 0;
        } else
        {
            throw new IOException((new StringBuilder("Input stream cannot be reset as ")).append(bytesReadPastMark).append(" bytes have been written, exceeding the available buffer size of ").append(bufferSize).toString());
        }
    }

    public boolean markSupported()
    {
        return true;
    }

    public synchronized void mark(int readlimit)
    {
        if(log.isDebugEnabled())
            log.debug((new StringBuilder("Input stream marked at ")).append(bytesReadPastMark).append(" bytes").toString());
        if(bytesReadPastMark <= (long)bufferSize && buffer != null)
        {
            byte newBuffer[] = new byte[bufferSize];
            System.arraycopy(buffer, bufferOffset, newBuffer, 0, (int)(bytesReadPastMark - (long)bufferOffset));
            buffer = newBuffer;
            bytesReadPastMark -= bufferOffset;
            bufferOffset = 0;
        } else
        {
            bufferOffset = 0;
            bytesReadPastMark = 0L;
            buffer = new byte[bufferSize];
        }
    }

    public int available()
        throws IOException
    {
        return is.available();
    }

    public void close()
        throws IOException
    {
        is.close();
    }

    public int read(byte out[], int outOffset, int outLength)
        throws IOException
    {
        byte tmp[] = new byte[outLength];
        if((long)bufferOffset < bytesReadPastMark && buffer != null)
        {
            int bytesFromBuffer = tmp.length;
            if((long)(bufferOffset + bytesFromBuffer) > bytesReadPastMark)
                bytesFromBuffer = (int)bytesReadPastMark - bufferOffset;
            System.arraycopy(buffer, bufferOffset, out, outOffset, bytesFromBuffer);
            bufferOffset += bytesFromBuffer;
            return bytesFromBuffer;
        }
        int count = is.read(tmp);
        if(count <= 0)
            return count;
        if(bytesReadPastMark + (long)count <= (long)bufferSize)
        {
            System.arraycopy(tmp, 0, buffer, (int)bytesReadPastMark, count);
            bufferOffset += count;
        } else
        if(buffer != null)
        {
            if(log.isDebugEnabled())
                log.debug((new StringBuilder("Buffer size ")).append(bufferSize).append(" has been exceeded and the input stream ").append("will not be repeatable until the next mark. Freeing buffer memory").toString());
            buffer = null;
        }
        System.arraycopy(tmp, 0, out, outOffset, count);
        bytesReadPastMark += count;
        return count;
    }

    public int read()
        throws IOException
    {
        byte tmp[] = new byte[1];
        int count = read(tmp);
        if(count != -1)
            return tmp[0];
        else
            return count;
    }

    public InputStream getWrappedInputStream()
    {
        return is;
    }

    private static final Log log = LogFactory.getLog("com.aliyun.aos.services.oss.internal.RepeatableInputStream");
    private InputStream is;
    private int bufferSize;
    private int bufferOffset;
    private long bytesReadPastMark;
    private byte buffer[];

}
