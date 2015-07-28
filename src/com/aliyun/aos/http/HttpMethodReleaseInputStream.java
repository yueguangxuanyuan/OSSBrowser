// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HttpMethodReleaseInputStream.java

package com.aliyun.aos.http;

import java.io.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.AbortableHttpRequest;

public class HttpMethodReleaseInputStream extends InputStream
{

    public HttpMethodReleaseInputStream(HttpEntityEnclosingRequest httpMethod)
    {
        inputStream = null;
        httpRequest = null;
        alreadyReleased = false;
        underlyingStreamConsumed = false;
        httpRequest = httpMethod;
        try
        {
            inputStream = httpMethod.getEntity().getContent();
        }
        catch(IOException e)
        {
            if(log.isWarnEnabled())
                log.warn("Unable to obtain HttpMethod's response data stream", e);
            try
            {
                httpMethod.getEntity().getContent().close();
            }
            catch(Exception exception) { }
            inputStream = new ByteArrayInputStream(new byte[0]);
        }
    }

    public HttpEntityEnclosingRequest getHttpRequest()
    {
        return httpRequest;
    }

    protected void releaseConnection()
        throws IOException
    {
        if(!alreadyReleased)
        {
            if(!underlyingStreamConsumed && (httpRequest instanceof AbortableHttpRequest))
            {
                AbortableHttpRequest abortableHttpRequest = (AbortableHttpRequest)httpRequest;
                abortableHttpRequest.abort();
            }
            inputStream.close();
            alreadyReleased = true;
        }
    }

    public int read()
        throws IOException
    {
        try
        {
            int read = inputStream.read();
            if(read == -1)
            {
                underlyingStreamConsumed = true;
                if(!alreadyReleased)
                {
                    releaseConnection();
                    if(log.isDebugEnabled())
                        log.debug("Released HttpMethod as its response data stream is fully consumed");
                }
            }
            return read;
        }
        catch(IOException e)
        {
            releaseConnection();
            if(log.isDebugEnabled())
                log.debug("Released HttpMethod as its response data stream threw an exception", e);
            throw e;
        }
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        try
        {
            int read = inputStream.read(b, off, len);
            if(read == -1)
            {
                underlyingStreamConsumed = true;
                if(!alreadyReleased)
                {
                    releaseConnection();
                    if(log.isDebugEnabled())
                        log.debug("Released HttpMethod as its response data stream is fully consumed");
                }
            }
            return read;
        }
        catch(IOException e)
        {
            releaseConnection();
            if(log.isDebugEnabled())
                log.debug("Released HttpMethod as its response data stream threw an exception", e);
            throw e;
        }
    }

    public int available()
        throws IOException
    {
        try
        {
            return inputStream.available();
        }
        catch(IOException e)
        {
            releaseConnection();
            if(log.isDebugEnabled())
                log.debug("Released HttpMethod as its response data stream threw an exception", e);
            throw e;
        }
    }

    public void close()
        throws IOException
    {
        if(!alreadyReleased)
        {
            releaseConnection();
            if(log.isDebugEnabled())
                log.debug("Released HttpMethod as its response data stream is closed");
        }
        inputStream.close();
    }

    protected void finalize()
        throws Throwable
    {
        if(!alreadyReleased)
        {
            if(log.isWarnEnabled())
                log.warn("Attempting to release HttpMethod in finalize() as its response data stream has gone out of scope. This attempt will not always succeed and cannot be relied upon! Please ensure S3 response data streams are always fully consumed or closed to avoid HTTP connection starvation.");
            releaseConnection();
            if(log.isWarnEnabled())
                log.warn("Successfully released HttpMethod in finalize(). You were lucky this time... Please ensure S3 response data streams are always fully consumed or closed.");
        }
        super.finalize();
    }

    private static final Log log = LogFactory.getLog(com/aliyun/aos/http/HttpMethodReleaseInputStream);
    private InputStream inputStream;
    private HttpEntityEnclosingRequest httpRequest;
    private boolean alreadyReleased;
    private boolean underlyingStreamConsumed;

}
