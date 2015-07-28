// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AOSWebServiceClient.java

package com.aliyun.aos;

import com.aliyun.aos.handlers.RequestHandler;
import com.aliyun.aos.http.AOSHttpClient;
import com.aliyun.aos.http.ExecutionContext;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

// Referenced classes of package com.aliyun.aos:
//            ClientConfiguration, Protocol

public abstract class AOSWebServiceClient
{

    public AOSWebServiceClient(ClientConfiguration clientConfiguration)
    {
        this.clientConfiguration = clientConfiguration;
        client = new AOSHttpClient(clientConfiguration);
    }

    public void setEndpoint(String endpoint)
        throws IllegalArgumentException
    {
        if(!endpoint.contains("://"))
            endpoint = (new StringBuilder(String.valueOf(clientConfiguration.getProtocol().toString()))).append("://").append(endpoint).toString();
        try
        {
            this.endpoint = new URI(endpoint);
        }
        catch(URISyntaxException e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    public void shutdown()
    {
        client.shutdown();
    }

    public void addRequestHandler(RequestHandler requestHandler)
    {
        requestHandlers.add(requestHandler);
    }

    public void removeRequestHandler(RequestHandler requestHandler)
    {
        requestHandlers.remove(requestHandler);
    }

    protected ExecutionContext createExecutionContext()
    {
        ExecutionContext executionContext = new ExecutionContext(requestHandlers);
        return executionContext;
    }

    protected URI endpoint;
    protected final ClientConfiguration clientConfiguration;
    protected final AOSHttpClient client;
    protected final List requestHandlers = Collections.synchronizedList(new LinkedList());
}
