// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Protocol.java

package com.aliyun.aos;


public final class Protocol extends Enum
{

    private Protocol(String s, int i, String protocolName)
    {
        super(s, i);
        this.protocolName = protocolName;
    }

    public String toString()
    {
        return protocolName;
    }

    public static Protocol[] values()
    {
        Protocol aprotocol[];
        int i;
        Protocol aprotocol1[];
        System.arraycopy(aprotocol = ENUM$VALUES, 0, aprotocol1 = new Protocol[i = aprotocol.length], 0, i);
        return aprotocol1;
    }

    public static Protocol valueOf(String s)
    {
        return (Protocol)Enum.valueOf(com.aliyun.aos.Protocol.class, s);
    }

    public static final Protocol HTTP;
    public static final Protocol HTTPS;
    private final String protocolName;
    private static final Protocol ENUM$VALUES[];

    static 
    {
        HTTP = new Protocol("HTTP", 0, "http");
        HTTPS = new Protocol("HTTPS", 1, "https");
        ENUM$VALUES = (new Protocol[] {
            HTTP, HTTPS
        });
    }
}
