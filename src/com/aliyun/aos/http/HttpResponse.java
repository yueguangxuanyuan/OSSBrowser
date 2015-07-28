// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HttpResponse.java

package com.aliyun.aos.http;

import com.aliyun.aos.Request;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse
{

    public HttpResponse(Request request)
    {
        headers = new HashMap();
        this.request = request;
    }

    public Request getRequest()
    {
        return request;
    }

    public Map getHeaders()
    {
        return headers;
    }

    public void addHeader(String name, String value)
    {
        headers.put(name, value);
    }

    public void setContent(InputStream content)
    {
        this.content = content;
    }

    public InputStream getContent()
    {
        return content;
    }

    public void setStatusText(String statusText)
    {
        this.statusText = statusText;
    }

    public String getStatusText()
    {
        return statusText;
    }

    public void setStatusCode(int statusCode)
    {
        this.statusCode = statusCode;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    private Request request;
    private String statusText;
    private int statusCode;
    private InputStream content;
    private Map headers;
}
