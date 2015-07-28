// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractAOSSigner.java

package com.aliyun.aos.auth;

import com.aliyun.aos.AOSClientException;
import com.aliyun.aos.util.HttpUtils;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

// Referenced classes of package com.aliyun.aos.auth:
//            Signer, SigningAlgorithm

public abstract class AbstractAOSSigner
    implements Signer
{

    public AbstractAOSSigner()
    {
    }

    protected String sign(String data, String key, SigningAlgorithm algorithm)
        throws AOSClientException
    {
        try
        {
            return sign(data.getBytes("UTF-8"), key, algorithm);
        }
        catch(UnsupportedEncodingException e)
        {
            throw new AOSClientException((new StringBuilder("Unable to calculate a request signature: ")).append(e.getMessage()).toString(), e);
        }
    }

    protected String getCanonicalizedResourcePath(URI endpoint)
    {
        String uri = endpoint.getPath();
        if(uri == null || uri.length() == 0)
            return "/";
        else
            return HttpUtils.urlEncode(uri, true);
    }

    protected String getCanonicalizedEndpoint(URI endpoint)
    {
        String endpointForStringToSign = endpoint.getHost().toLowerCase();
        if(HttpUtils.isUsingNonDefaultPort(endpoint))
            endpointForStringToSign = (new StringBuilder(String.valueOf(endpointForStringToSign))).append(":").append(endpoint.getPort()).toString();
        return endpointForStringToSign;
    }

    protected String getCanonicalizedQueryString(Map parameters)
    {
        SortedMap sorted = new TreeMap();
        sorted.putAll(parameters);
        StringBuilder builder = new StringBuilder();
        for(Iterator pairs = sorted.entrySet().iterator(); pairs.hasNext();)
        {
            java.util.Map.Entry pair = (java.util.Map.Entry)pairs.next();
            String key = (String)pair.getKey();
            String value = (String)pair.getValue();
            builder.append(HttpUtils.urlEncode(key, false));
            builder.append("=");
            builder.append(HttpUtils.urlEncode(value, false));
            if(pairs.hasNext())
                builder.append("&");
        }

        return builder.toString();
    }

    protected String sign(byte data[], String key, SigningAlgorithm algorithm)
    {
        try
        {
            Mac mac = Mac.getInstance(algorithm.toString());
            mac.init(new SecretKeySpec(key.getBytes(), algorithm.toString()));
            byte signature[] = Base64.encodeBase64(mac.doFinal(data));
            return new String(signature);
        }
        catch(Exception e)
        {
            throw new AOSClientException((new StringBuilder("Unable to calculate a request signature: ")).append(e.getMessage()).toString(), e);
        }
    }

    private static final String DEFAULT_ENCODING = "UTF-8";
}
