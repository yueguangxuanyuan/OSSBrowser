// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OSSXmlResponseHandler.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.AOSWebServiceResponse;
import com.aliyun.aos.http.HttpResponse;
import com.aliyun.aos.transform.Unmarshaller;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package com.aliyun.aos.services.oss.internal:
//            AbstractOSSResponseHandler

public class OSSXmlResponseHandler extends AbstractOSSResponseHandler
{

    public OSSXmlResponseHandler(Unmarshaller responseUnmarshaller)
    {
        this.responseUnmarshaller = responseUnmarshaller;
    }

    public AOSWebServiceResponse handle(HttpResponse response)
        throws Exception
    {
        AOSWebServiceResponse aosResponse = parseResponseMetadata(response);
        responseHeaders = response.getHeaders();
        if(responseUnmarshaller != null)
        {
            log.trace("Beginning to parse service response XML");
            Object result = responseUnmarshaller.unmarshall(response.getContent());
            log.trace("Done parsing service response XML");
            aosResponse.setResult(result);
        }
        return aosResponse;
    }

    public Map getResponseHeaders()
    {
        return responseHeaders;
    }


    private Unmarshaller responseUnmarshaller;
    private static final Log log = LogFactory.getLog("com.AOSaws.request");
    private Map responseHeaders;

}
