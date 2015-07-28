// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RestUtils.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.Request;
import java.util.*;

public class RestUtils
{

    public RestUtils()
    {
    }

    public static String makeOSSCanonicalString(String method, String resource, Request request, String expires)
    {
        StringBuilder buf = new StringBuilder();
        buf.append((new StringBuilder(String.valueOf(method))).append("\n").toString());
        Map headersMap = request.getHeaders();
        SortedMap interestingHeaders = new TreeMap();
        if(headersMap != null && headersMap.size() > 0)
        {
            for(Iterator headerIter = headersMap.entrySet().iterator(); headerIter.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)headerIter.next();
                String key = (String)entry.getKey();
                String value = (String)entry.getValue();
                if(key != null)
                {
                    String lk = key.toString().toLowerCase(Locale.getDefault());
                    if(lk.equals("content-type") || lk.equals("content-md5") || lk.equals("date") || lk.startsWith("x-oss-"))
                        interestingHeaders.put(lk, value);
                }
            }

        }
        if(expires != null)
            interestingHeaders.put("date", expires);
        if(!interestingHeaders.containsKey("content-type"))
            interestingHeaders.put("content-type", "");
        if(!interestingHeaders.containsKey("content-md5"))
            interestingHeaders.put("content-md5", "");
        for(Iterator iterator = request.getParameters().entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry parameter = (java.util.Map.Entry)iterator.next();
            if(((String)parameter.getKey()).startsWith("x-oss-"))
                interestingHeaders.put((String)parameter.getKey(), (String)parameter.getValue());
        }

        for(Iterator i = interestingHeaders.entrySet().iterator(); i.hasNext(); buf.append("\n"))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)i.next();
            String key = (String)entry.getKey();
            Object value = entry.getValue();
            if(key.startsWith("x-oss-"))
                buf.append(key).append(':').append(value);
            else
                buf.append(value);
        }

        buf.append(resource);
        String parameterNames[] = (String[])request.getParameters().keySet().toArray(new String[request.getParameters().size()]);
        Arrays.sort(parameterNames);
        char separator = '?';
        String as[];
        int k = (as = parameterNames).length;
        for(int j = 0; j < k; j++)
        {
            String parameterName = as[j];
            if(SIGNED_PARAMETERS.contains(parameterName))
            {
                buf.append(separator);
                buf.append(parameterName);
                String parameterValue = (String)request.getParameters().get(parameterName);
                if(parameterValue != null)
                    buf.append("=").append(parameterValue);
                separator = '&';
            }
        }

        return buf.toString();
    }

    private static final List SIGNED_PARAMETERS = Arrays.asList(new String[] {
        "acl", "uploadId", "uploads", "partNumber", "group", "delete", "response-cache-control", "response-content-disposition", "response-content-encoding", "response-content-language", 
        "response-content-type", "response-expires"
    });

}
