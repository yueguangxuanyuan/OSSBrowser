// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HttpMethod.java

package com.aliyun.aos;


public final class HttpMethod extends Enum
{

    private HttpMethod(String s, int i)
    {
        super(s, i);
    }

    public static HttpMethod[] values()
    {
        HttpMethod ahttpmethod[];
        int i;
        HttpMethod ahttpmethod1[];
        System.arraycopy(ahttpmethod = ENUM$VALUES, 0, ahttpmethod1 = new HttpMethod[i = ahttpmethod.length], 0, i);
        return ahttpmethod1;
    }

    public static HttpMethod valueOf(String s)
    {
        return (HttpMethod)Enum.valueOf(com.aliyun.aos.HttpMethod.class, s);
    }

    public static final HttpMethod HEAD;
    public static final HttpMethod GET;
    public static final HttpMethod PUT;
    public static final HttpMethod POST;
    public static final HttpMethod DELETE;
    private static final HttpMethod ENUM$VALUES[];

    static 
    {
        HEAD = new HttpMethod("HEAD", 0);
        GET = new HttpMethod("GET", 1);
        PUT = new HttpMethod("PUT", 2);
        POST = new HttpMethod("POST", 3);
        DELETE = new HttpMethod("DELETE", 4);
        ENUM$VALUES = (new HttpMethod[] {
            HEAD, GET, PUT, POST, DELETE
        });
    }
}
