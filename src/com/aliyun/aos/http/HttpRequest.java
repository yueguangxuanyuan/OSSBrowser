// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HttpRequest.java

package com.aliyun.aos.http;

import com.aliyun.aos.AOSWebServiceClient;
import java.io.InputStream;
import java.net.URI;
import java.util.*;

// Referenced classes of package com.aliyun.aos.http:
//            HttpMethodName

public class HttpRequest
{

    public HttpRequest(HttpMethodName methodName)
    {
        parameters = new HashMap();
        headers = new HashMap();
        this.methodName = methodName;
    }

    public HttpMethodName getMethodName()
    {
        return methodName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public URI getEndpoint()
    {
        return endpoint;
    }

    public void setEndpoint(URI endpoint)
    {
        this.endpoint = endpoint;
    }

    public Map getParameters()
    {
        return parameters;
    }

    public Map getHeaders()
    {
        return headers;
    }

    public void addHeader(String name, String value)
    {
        headers.put(name, value);
    }

    public void removeHeader(String name)
    {
        headers.remove(name);
    }

    public void addParameter(String name, String value)
    {
        parameters.put(name, value);
    }

    public void setParameters(Map parameters)
    {
        this.parameters = parameters;
    }

    public HttpRequest withParameter(String name, String value)
    {
        addParameter(name, value);
        return this;
    }

    public String getResourcePath()
    {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath)
    {
        this.resourcePath = resourcePath;
    }

    public void setContent(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    public InputStream getContent()
    {
        return inputStream;
    }

    public void setOriginalRequest(AOSWebServiceClient request)
    {
        originalRequest = request;
    }

    public AOSWebServiceClient getOriginalRequest()
    {
        return originalRequest;
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append((new StringBuilder(String.valueOf(getMethodName().toString()))).append(" ").toString());
        builder.append((new StringBuilder(String.valueOf(getEndpoint().toString()))).append(" ").toString());
        builder.append((new StringBuilder("/")).append(getResourcePath() == null ? "" : getResourcePath()).append(" ").toString());
        if(!getParameters().isEmpty())
        {
            builder.append("Parameters: (");
            String key;
            String value;
            for(Iterator iterator = getParameters().keySet().iterator(); iterator.hasNext(); builder.append((new StringBuilder(String.valueOf(key))).append(": ").append(value).append(", ").toString()))
            {
                key = (String)iterator.next();
                value = (String)getParameters().get(key);
            }

            builder.append(") ");
        }
        if(!getHeaders().isEmpty())
        {
            builder.append("Headers: (");
            String key;
            String value;
            for(Iterator iterator1 = getHeaders().keySet().iterator(); iterator1.hasNext(); builder.append((new StringBuilder(String.valueOf(key))).append(": ").append(value).append(", ").toString()))
            {
                key = (String)iterator1.next();
                value = (String)getHeaders().get(key);
            }

            builder.append(") ");
        }
        return builder.toString();
    }

    private Map parameters;
    private Map headers;
    private HttpMethodName methodName;
    private String serviceName;
    private URI endpoint;
    private String resourcePath;
    private InputStream inputStream;
    private AOSWebServiceClient originalRequest;
}
