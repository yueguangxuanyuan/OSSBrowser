// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InputSubstream.java

package com.aliyun.aos.services.oss.internal;

import java.io.*;

public final class InputSubstream extends FilterInputStream
{

    public InputSubstream(InputStream in, long offset, long length, boolean closeSourceStream)
    {
        super(in);
        markedPosition = 0L;
        currentPosition = 0L;
        requestedLength = length;
        requestedOffset = offset;
        this.closeSourceStream = closeSourceStream;
    }

    public int read()
        throws IOException
    {
        byte b[] = new byte[1];
        int bytesRead = read(b, 0, 1);
        if(bytesRead == -1)
            return bytesRead;
        else
            return b[0];
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        long skippedBytes;
        for(; currentPosition < requestedOffset; currentPosition += skippedBytes)
            skippedBytes = super.skip(requestedOffset - currentPosition);

        long bytesRemaining = (requestedLength + requestedOffset) - currentPosition;
        if(bytesRemaining <= 0L)
        {
            return -1;
        } else
        {
            len = (int)Math.min(len, bytesRemaining);
            int bytesRead = super.read(b, off, len);
            currentPosition += bytesRead;
            return bytesRead;
        }
    }

    public synchronized void mark(int readlimit)
    {
        markedPosition = currentPosition;
        super.mark(readlimit);
    }

    public synchronized void reset()
        throws IOException
    {
        currentPosition = markedPosition;
        super.reset();
    }

    public void close()
        throws IOException
    {
        if(closeSourceStream)
            super.close();
    }

    public int available()
        throws IOException
    {
        long bytesRemaining;
        if(currentPosition < requestedOffset)
            bytesRemaining = requestedLength;
        else
            bytesRemaining = (requestedLength + requestedOffset) - currentPosition;
        return (int)Math.min(bytesRemaining, super.available());
    }

    private long currentPosition;
    private final long requestedOffset;
    private final long requestedLength;
    private final boolean closeSourceStream;
    private long markedPosition;
}
