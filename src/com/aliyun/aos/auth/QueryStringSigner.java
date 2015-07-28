// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QueryStringSigner.java

package com.aliyun.aos.auth;

import com.aliyun.aos.AOSClientException;
import com.aliyun.aos.Request;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package com.aliyun.aos.auth:
//            AbstractAOSSigner, Signer, AOSCredentials, SigningAlgorithm

public class QueryStringSigner extends AbstractAOSSigner
    implements Signer
{

    public QueryStringSigner()
    {
    }

    public void sign(Request request1, AOSCredentials aoscredentials)
        throws AOSClientException
    {
    }

    public void sign(Request request1, SigningAlgorithm signingalgorithm, AOSCredentials aoscredentials)
        throws AOSClientException
    {
    }

    private String getFormattedTimestamp()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        return df.format(new Date());
    }

    private String calculateStringSign(URI endpoint, Map parameters)
    {
        StringBuilder data = new StringBuilder();
        data.append("POST").append("\n");
        data.append(getCanonicalizedEndpoint(endpoint)).append("\n");
        data.append(getCanonicalizedResourcePath(endpoint)).append("\n");
        data.append(getCanonicalizedQueryString(parameters));
        return data.toString();
    }
}
