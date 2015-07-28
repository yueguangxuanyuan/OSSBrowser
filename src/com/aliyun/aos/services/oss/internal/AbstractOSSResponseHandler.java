// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractOSSResponseHandler.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.AOSWebServiceResponse;
import com.aliyun.aos.http.HttpResponse;
import com.aliyun.aos.http.HttpResponseHandler;
import com.aliyun.aos.services.oss.OSSResponseMetadata;
import com.aliyun.aos.services.oss.model.ObjectMetadata;
import java.text.ParseException;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package com.aliyun.aos.services.oss.internal:
//            OSSMetadataResponseHandler, ServiceUtils

public abstract class AbstractOSSResponseHandler
    implements HttpResponseHandler
{

    public AbstractOSSResponseHandler()
    {
    }

    public boolean needsConnectionLeftOpen()
    {
        return false;
    }

    protected AOSWebServiceResponse parseResponseMetadata(HttpResponse response)
    {
        AOSWebServiceResponse awsResponse = new AOSWebServiceResponse();
        String awsRequestId = (String)response.getHeaders().get("x-oss-request-id");
        Map metadataMap = new HashMap();
        metadataMap.put("AOS_REQUEST_ID", awsRequestId);
        awsResponse.setResponseMetadata(new OSSResponseMetadata(metadataMap));
        return awsResponse;
    }

    protected void populateObjectMetadata(HttpResponse response, ObjectMetadata metadata)
    {
        for(Iterator iterator = response.getHeaders().entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry header = (java.util.Map.Entry)iterator.next();
            String key = (String)header.getKey();
            if(key.startsWith("x-oss-meta-"))
            {
                key = key.substring("x-oss-meta-".length());
                metadata.addUserMetadata(key, (String)header.getValue());
            } else
            if(!ignoredHeaders.contains(key))
                if(key.equals("Last-Modified"))
                    try
                    {
                        metadata.setHeader(key, ServiceUtils.parseRfc822Date((String)header.getValue()));
                    }
                    catch(ParseException pe)
                    {
                        log.warn((new StringBuilder("Unable to parse last modified date: ")).append((String)header.getValue()).toString(), pe);
                    }
                else
                if(key.equals("Content-Length"))
                    try
                    {
                        metadata.setHeader(key, Long.valueOf(Long.parseLong((String)header.getValue())));
                    }
                    catch(NumberFormatException nfe)
                    {
                        log.warn((new StringBuilder("Unable to parse content length: ")).append((String)header.getValue()).toString(), nfe);
                    }
                else
                if(key.equals("ETag"))
                    metadata.setHeader(key, ServiceUtils.removeQuotes((String)header.getValue()));
                else
                    metadata.setHeader(key, header.getValue());
        }

    }

    private static final Log log = LogFactory.getLog("com/aliyun/aos/services/oss/internal/OSSMetadataResponseHandler");
    private static final Set ignoredHeaders;

    static 
    {
        ignoredHeaders = new HashSet();
        ignoredHeaders.add("Date");
        ignoredHeaders.add("Server");
        ignoredHeaders.add("x-oss-request-id");
    }
}
