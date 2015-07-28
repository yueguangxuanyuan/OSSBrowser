// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnectionManagerFactory.java

package com.aliyun.aos.http;

import com.aliyun.aos.ClientConfiguration;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;

class ConnectionManagerFactory
{

    ConnectionManagerFactory()
    {
    }

    public static ThreadSafeClientConnManager createThreadSafeClientConnManager(ClientConfiguration config, HttpParams httpClientParams)
    {
        ThreadSafeClientConnManager connectionManager = new ThreadSafeClientConnManager();
        connectionManager.setDefaultMaxPerRoute(config.getMaxConnections());
        connectionManager.setMaxTotal(config.getMaxConnections());
        return connectionManager;
    }
}
