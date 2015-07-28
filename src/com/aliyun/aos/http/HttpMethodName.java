// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HttpMethodName.java

package com.aliyun.aos.http;


public final class HttpMethodName extends Enum
{

    private HttpMethodName(String s, int i)
    {
        super(s, i);
    }

    public static HttpMethodName[] values()
    {
        HttpMethodName ahttpmethodname[];
        int i;
        HttpMethodName ahttpmethodname1[];
        System.arraycopy(ahttpmethodname = ENUM$VALUES, 0, ahttpmethodname1 = new HttpMethodName[i = ahttpmethodname.length], 0, i);
        return ahttpmethodname1;
    }

    public static HttpMethodName valueOf(String s)
    {
        return (HttpMethodName)Enum.valueOf(com/aliyun/aos/http/HttpMethodName, s);
    }

    public static final HttpMethodName GET;
    public static final HttpMethodName POST;
    public static final HttpMethodName PUT;
    public static final HttpMethodName DELETE;
    public static final HttpMethodName HEAD;
    private static final HttpMethodName ENUM$VALUES[];

    static 
    {
        GET = new HttpMethodName("GET", 0);
        POST = new HttpMethodName("POST", 1);
        PUT = new HttpMethodName("PUT", 2);
        DELETE = new HttpMethodName("DELETE", 3);
        HEAD = new HttpMethodName("HEAD", 4);
        ENUM$VALUES = (new HttpMethodName[] {
            GET, POST, PUT, DELETE, HEAD
        });
    }
}
