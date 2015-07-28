// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AOSWebServiceRequest.java

package com.aliyun.aos;

import com.aliyun.aos.auth.AOSCredentials;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.aliyun.aos:
//            RequestClientOptions

public abstract class AOSWebServiceRequest
{

    public AOSWebServiceRequest()
    {
    }

    public void setRequestCredentials(AOSCredentials credentials)
    {
        this.credentials = credentials;
    }

    public AOSCredentials getRequestCredentials()
    {
        return credentials;
    }

    public Map copyPrivateRequestParameters()
    {
        return new HashMap();
    }

    public RequestClientOptions getRequestClientOptions()
    {
        return requestClientOptions;
    }

    private final RequestClientOptions requestClientOptions = new RequestClientOptions();
    private AOSCredentials credentials;
}
