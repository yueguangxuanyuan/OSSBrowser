// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HttpUtils.java

package com.aliyun.aos.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

public class HttpUtils
{

    public HttpUtils()
    {
    }

    public static String urlEncode(String value, boolean path)
    {
        try
        {
            String encoded = URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
            if(path)
                encoded = encoded.replace("%2F", "/");
            return encoded;
        }
        catch(UnsupportedEncodingException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean isUsingNonDefaultPort(URI uri)
    {
        String scheme = uri.getScheme().toLowerCase();
        int port = uri.getPort();
        if(port <= 0)
            return false;
        if(scheme.equals("http") && port == 80)
            return false;
        return !scheme.equals("https") || port != 443;
    }

    private static final String DEFAULT_ENCODING = "UTF-8";
}
