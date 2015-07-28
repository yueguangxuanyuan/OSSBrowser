// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequestClientOptions.java

package com.aliyun.aos;


public final class RequestClientOptions
{

    public RequestClientOptions()
    {
    }

    public String getClientMarker()
    {
        return clientMarker;
    }

    public void addClientMarker(String clientMarker)
    {
        if(this.clientMarker == null)
            this.clientMarker = "";
        this.clientMarker = createClientMarkerString(clientMarker);
    }

    private String createClientMarkerString(String clientMarker)
    {
        if(this.clientMarker.contains(clientMarker))
            return this.clientMarker;
        else
            return (new StringBuilder(String.valueOf(this.clientMarker))).append(" ").append(clientMarker).toString();
    }

    private String clientMarker;
}
