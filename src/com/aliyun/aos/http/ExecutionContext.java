// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExecutionContext.java

package com.aliyun.aos.http;

import com.aliyun.aos.auth.AOSCredentials;
import com.aliyun.aos.auth.Signer;
import com.aliyun.aos.util.TimingInfo;
import java.util.List;

public class ExecutionContext
{

    public String getContextUserAgent()
    {
        return contextUserAgent;
    }

    public void setContextUserAgent(String contextUserAgent)
    {
        this.contextUserAgent = contextUserAgent;
    }

    public ExecutionContext()
    {
    }

    public ExecutionContext(List requestHandlers)
    {
        this.requestHandlers = requestHandlers;
    }

    public List getRequestHandlers()
    {
        return requestHandlers;
    }

    public TimingInfo getTimingInfo()
    {
        return timingInfo;
    }

    public void setTimingInfo(TimingInfo timingInfo)
    {
        this.timingInfo = timingInfo;
    }

    public Signer getSigner()
    {
        return signer;
    }

    public void setSigner(Signer signer)
    {
        this.signer = signer;
    }

    public AOSCredentials getCredentials()
    {
        return credentials;
    }

    public void setCredentials(AOSCredentials credentials)
    {
        this.credentials = credentials;
    }

    private List requestHandlers;
    private String contextUserAgent;
    private TimingInfo timingInfo;
    private Signer signer;
    private AOSCredentials credentials;
}
