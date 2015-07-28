// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultRequest.java

package com.aliyun.aos;

import com.aliyun.aos.http.HttpMethodName;
import java.io.InputStream;
import java.net.URI;
import java.util.*;

// Referenced classes of package com.aliyun.aos:
//            Request, AOSWebServiceRequest

public class DefaultRequest
    implements Request
{

    public DefaultRequest(AOSWebServiceRequest originalRequest, String serviceName)
    {
        parameters = new HashMap();
        headers = new HashMap();
        httpMethod = HttpMethodName.POST;
        this.serviceName = serviceName;
        this.originalRequest = originalRequest;
    }

    public DefaultRequest(String serviceName)
    {
        this(null, serviceName);
    }

    public AOSWebServiceRequest getOriginalRequest()
    {
        return originalRequest;
    }

    public void addHeader(String name, String value)
    {
        headers.put(name, value);
    }

    public Map getHeaders()
    {
        return headers;
    }

    public void setResourcePath(String resourcePath)
    {
        this.resourcePath = resourcePath;
    }

    public String getResourcePath()
    {
        return resourcePath;
    }

    public void addParameter(String name, String value)
    {
        parameters.put(name, value);
    }

    public Map getParameters()
    {
        return parameters;
    }

    public Request withParameter(String name, String value)
    {
        addParameter(name, value);
        return this;
    }

    public HttpMethodName getHttpMethod()
    {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethodName httpMethod)
    {
        this.httpMethod = httpMethod;
    }

    public void setEndpoint(URI endpoint)
    {
        this.endpoint = endpoint;
    }

    public URI getEndpoint()
    {
        return endpoint;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public InputStream getContent()
    {
        return content;
    }

    public void setContent(InputStream content)
    {
        this.content = content;
    }

    public void setHeaders(Map headers)
    {
        this.headers.clear();
        this.headers.putAll(headers);
    }

    public void setParameters(Map parameters)
    {
        this.parameters.clear();
        this.parameters.putAll(parameters);
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append((new StringBuilder(String.valueOf(getHttpMethod().toString()))).append(" ").toString());
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

    private String resourcePath;
    private Map parameters;
    private Map headers;
    private URI endpoint;
    private String serviceName;
    private final AOSWebServiceRequest originalRequest;
    private HttpMethodName httpMethod;
    private InputStream content;
}
