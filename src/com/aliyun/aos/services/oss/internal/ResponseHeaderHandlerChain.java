// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResponseHeaderHandlerChain.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.AOSWebServiceResponse;
import com.aliyun.aos.http.HttpResponse;
import com.aliyun.aos.transform.Unmarshaller;
import java.util.*;

// Referenced classes of package com.aliyun.aos.services.oss.internal:
//            OSSXmlResponseHandler, HeaderHandler

public class ResponseHeaderHandlerChain extends OSSXmlResponseHandler
{

    public  ResponseHeaderHandlerChain(Unmarshaller responseUnmarshaller, HeaderHandler headerHandlers[])
    {
        super(responseUnmarshaller);
        this.headerHandlers = Arrays.asList(headerHandlers);
    }

    public AOSWebServiceResponse handle(HttpResponse response)
        throws Exception
    {
        AOSWebServiceResponse awsResponse = super.handle(response);
        Object result = awsResponse.getResult();
        if(result != null)
        {
            HeaderHandler handler;
            for(Iterator iterator = headerHandlers.iterator(); iterator.hasNext(); handler.handle(result, response))
                handler = (HeaderHandler)iterator.next();

        }
        return awsResponse;
    }

    private final List headerHandlers;
}
