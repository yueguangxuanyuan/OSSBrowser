// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HttpRequestFactory.java

package com.aliyun.aos.http;

import com.aliyun.aos.*;
import com.aliyun.aos.util.HttpUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

// Referenced classes of package com.aliyun.aos.http:
//            HttpMethodName, RepeatableInputStreamRequestEntity, ExecutionContext

class HttpRequestFactory
{

    HttpRequestFactory()
    {
    }

    HttpRequestBase createHttpRequest(Request request, ClientConfiguration clientConfiguration, HttpEntity previousEntity, ExecutionContext context)
    {
        URI endpoint = request.getEndpoint();
        String uri = endpoint.toString();
        if(request.getResourcePath() != null && request.getResourcePath().length() > 0)
        {
            if(!request.getResourcePath().startsWith("/"))
                uri = (new StringBuilder(String.valueOf(uri))).append("/").toString();
            uri = (new StringBuilder(String.valueOf(uri))).append(request.getResourcePath()).toString();
        }
        String encodedParams = encodeParameters(request);
        boolean requestHasNoPayload = request.getContent() != null;
        boolean requestIsPost = request.getHttpMethod() == HttpMethodName.POST;
        boolean putParamsInUri = !requestIsPost || requestHasNoPayload;
        if(encodedParams != null && putParamsInUri)
            uri = (new StringBuilder(String.valueOf(uri))).append("?").append(encodedParams).toString();
        HttpRequestBase httpRequest;
        if(request.getHttpMethod() == HttpMethodName.POST)
        {
            HttpPost postMethod = new HttpPost(uri);
            if(request.getContent() == null && encodedParams != null)
                postMethod.setEntity(newStringEntity(encodedParams));
            else
                postMethod.setEntity(new RepeatableInputStreamRequestEntity(request));
            httpRequest = postMethod;
        } else
        if(request.getHttpMethod() == HttpMethodName.PUT)
        {
            HttpPut putMethod = new HttpPut(uri);
            httpRequest = putMethod;
            putMethod.getParams().setParameter("http.protocol.expect-continue", Boolean.valueOf(true));
            if(previousEntity != null)
                putMethod.setEntity(previousEntity);
            else
            if(request.getContent() != null)
            {
                HttpEntity entity = new RepeatableInputStreamRequestEntity(request);
                if(request.getHeaders().get("Content-Length") == null)
                    entity = newBufferedHttpEntity(entity);
                putMethod.setEntity(entity);
            }
        } else
        if(request.getHttpMethod() == HttpMethodName.GET)
            httpRequest = new HttpGet(uri);
        else
        if(request.getHttpMethod() == HttpMethodName.DELETE)
            httpRequest = new HttpDelete(uri);
        else
        if(request.getHttpMethod() == HttpMethodName.HEAD)
            httpRequest = new HttpHead(uri);
        else
            throw new AOSClientException((new StringBuilder("Unknown HTTP method name: ")).append(request.getHttpMethod()).toString());
        configureHeaders(httpRequest, request, context, clientConfiguration);
        return httpRequest;
    }

    private void configureHeaders(HttpRequestBase httpRequest, Request request, ExecutionContext context, ClientConfiguration clientConfiguration)
    {
        URI endpoint = request.getEndpoint();
        String hostHeader = endpoint.getHost();
        if(HttpUtils.isUsingNonDefaultPort(endpoint))
            hostHeader = (new StringBuilder(String.valueOf(hostHeader))).append(":").append(endpoint.getPort()).toString();
        httpRequest.addHeader("Host", hostHeader);
        for(Iterator iterator = request.getHeaders().entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            if(!((String)entry.getKey()).equalsIgnoreCase("Content-Length") && !((String)entry.getKey()).equalsIgnoreCase("Host"))
                httpRequest.addHeader((String)entry.getKey(), (String)entry.getValue());
        }

        if(httpRequest.getHeaders("Content-Type") == null || httpRequest.getHeaders("Content-Type").length == 0)
            httpRequest.addHeader("Content-Type", (new StringBuilder("application/x-www-form-urlencoded; charset=")).append("UTF-8".toLowerCase()).toString());
        if(context != null && context.getContextUserAgent() != null)
            httpRequest.addHeader("User-Agent", createUserAgentString(clientConfiguration, context.getContextUserAgent()));
    }

    private String encodeParameters(Request request)
    {
        List nameValuePairs = null;
        if(request.getParameters().size() > 0)
        {
            nameValuePairs = new ArrayList(request.getParameters().size());
            java.util.Map.Entry entry;
            for(Iterator iterator = request.getParameters().entrySet().iterator(); iterator.hasNext(); nameValuePairs.add(new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue())))
                entry = (java.util.Map.Entry)iterator.next();

        }
        String encodedParams = null;
        if(nameValuePairs != null)
            encodedParams = URLEncodedUtils.format(nameValuePairs, "UTF-8");
        if(encodedParams != null && encodedParams.endsWith("="))
            encodedParams = encodedParams.substring(0, encodedParams.length() - 1);
        return encodedParams;
    }

    private String createUserAgentString(ClientConfiguration clientConfiguration, String contextUserAgent)
    {
        if(clientConfiguration.getUserAgent().contains(contextUserAgent))
            return clientConfiguration.getUserAgent();
        else
            return (new StringBuilder(String.valueOf(clientConfiguration.getUserAgent()))).append(" ").append(contextUserAgent).toString();
    }

    private HttpEntity newStringEntity(String s)
    {
        try
        {
            StringEntity entity = new StringEntity(s);
            entity.setChunked(false);
            return entity;
        }
        catch(UnsupportedEncodingException e)
        {
            throw new AOSClientException((new StringBuilder("Unable to create HTTP entity: ")).append(e.getMessage()).toString(), e);
        }
    }

    private HttpEntity newBufferedHttpEntity(HttpEntity entity)
    {
        try
        {
            return new BufferedHttpEntity(entity);
        }
        catch(IOException e)
        {
            throw new AOSClientException((new StringBuilder("Unable to create HTTP entity: ")).append(e.getMessage()).toString(), e);
        }
    }

    private static final String DEFAULT_ENCODING = "UTF-8";
}
