// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RepeatableInputStreamRequestEntity.java

package com.aliyun.aos.http;

import com.aliyun.aos.Request;
import java.io.*;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.InputStreamEntity;

// Referenced classes of package com.aliyun.aos.http:
//            AOSHttpClient

class RepeatableInputStreamRequestEntity extends BasicHttpEntity
{

    RepeatableInputStreamRequestEntity(Request request)
    {
        firstAttempt = true;
        setChunked(false);
        long contentLength = -1L;
        try
        {
            String contentLengthString = (String)request.getHeaders().get("Content-Length");
            if(contentLengthString != null)
                contentLength = Long.parseLong(contentLengthString);
        }
        catch(NumberFormatException nfe)
        {
            log.warn("Unable to parse content length from request.  Buffering contents in memory.");
        }
        String contentType = (String)request.getHeaders().get("Content-Type");
        inputStreamRequestEntity = new InputStreamEntity(request.getContent(), contentLength);
        inputStreamRequestEntity.setContentType(contentType);
        content = request.getContent();
        setContent(content);
        setContentType(contentType);
        setContentLength(contentLength);
    }

    public boolean isChunked()
    {
        return false;
    }

    public boolean isRepeatable()
    {
        return content.markSupported() || inputStreamRequestEntity.isRepeatable();
    }

    public void writeTo(OutputStream output)
        throws IOException
    {
        if(!firstAttempt && isRepeatable())
            content.reset();
        firstAttempt = false;
        inputStreamRequestEntity.writeTo(output);
    }

    private boolean firstAttempt;
    private InputStreamEntity inputStreamRequestEntity;
    private InputStream content;
    private static final Log log = LogFactory.getLog(com/aliyun/aos/http/AOSHttpClient);

}
