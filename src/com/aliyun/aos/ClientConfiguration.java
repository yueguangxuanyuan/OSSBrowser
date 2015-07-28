// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClientConfiguration.java

package com.aliyun.aos;

import com.aliyun.aos.util.VersionInfoUtils;

// Referenced classes of package com.aliyun.aos:
//            Protocol

public class ClientConfiguration
{

    public ClientConfiguration()
    {
        userAgent = DEFAULT_USER_AGENT;
        maxErrorRetry = 1;
        protocol = Protocol.HTTP;
        proxyHost = null;
        proxyPort = -1;
        proxyUsername = null;
        proxyPassword = null;
        proxyDomain = null;
        proxyWorkstation = null;
        maxConnections = 100;
        socketTimeout = 60000;
        connectionTimeout = socketTimeout;
        socketSendBufferSizeHint = 0;
        socketRecvBufferSizeHint = 0;
    }

    public String getUserAgent()
    {
        return userAgent;
    }

    public void setUserAgent(String userAgent)
    {
        this.userAgent = userAgent;
    }

    public int getMaxErrorRetry()
    {
        return maxErrorRetry;
    }

    public void setMaxErrorRetry(int maxErrorRetry)
    {
        this.maxErrorRetry = maxErrorRetry;
    }

    public Protocol getProtocol()
    {
        return protocol;
    }

    public void setProtocol(Protocol protocol)
    {
        this.protocol = protocol;
    }

    public String getProxyHost()
    {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost)
    {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort()
    {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort)
    {
        this.proxyPort = proxyPort;
    }

    public String getProxyUsername()
    {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername)
    {
        this.proxyUsername = proxyUsername;
    }

    public String getProxyPassword()
    {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword)
    {
        this.proxyPassword = proxyPassword;
    }

    public String getProxyDomain()
    {
        return proxyDomain;
    }

    public void setProxyDomain(String proxyDomain)
    {
        this.proxyDomain = proxyDomain;
    }

    public String getProxyWorkstation()
    {
        return proxyWorkstation;
    }

    public void setProxyWorkstation(String proxyWorkstation)
    {
        this.proxyWorkstation = proxyWorkstation;
    }

    public int getMaxConnections()
    {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections)
    {
        this.maxConnections = maxConnections;
    }

    public int getSocketTimeout()
    {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout)
    {
        this.socketTimeout = socketTimeout;
    }

    public int getConnectionTimeout()
    {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout)
    {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSocketSendBufferSizeHint()
    {
        return socketSendBufferSizeHint;
    }

    public void setSocketSendBufferSizeHint(int socketSendBufferSizeHint)
    {
        this.socketSendBufferSizeHint = socketSendBufferSizeHint;
    }

    public int getSocketRecvBufferSizeHint()
    {
        return socketRecvBufferSizeHint;
    }

    public void setSocketRecvBufferSizeHint(int socketRecvBufferSizeHint)
    {
        this.socketRecvBufferSizeHint = socketRecvBufferSizeHint;
    }

    public int[] getSocketBufferSizeHints()
    {
        return (new int[] {
            socketSendBufferSizeHint, socketRecvBufferSizeHint
        });
    }

    public static final String DEFAULT_USER_AGENT = VersionInfoUtils.getUserAgent();
    private String userAgent;
    private int maxErrorRetry;
    private Protocol protocol;
    private String proxyHost;
    private int proxyPort;
    private String proxyUsername;
    private String proxyPassword;
    private String proxyDomain;
    private String proxyWorkstation;
    private int maxConnections;
    private int socketTimeout;
    private int connectionTimeout;
    private int socketSendBufferSizeHint;
    private int socketRecvBufferSizeHint;

}
