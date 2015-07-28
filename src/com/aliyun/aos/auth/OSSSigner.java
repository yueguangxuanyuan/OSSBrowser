// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OSSSigner.java

package com.aliyun.aos.auth;

import com.aliyun.aos.AOSClientException;
import com.aliyun.aos.Request;
import com.aliyun.aos.services.oss.internal.RestUtils;
import com.aliyun.aos.services.oss.internal.ServiceUtils;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package com.aliyun.aos.auth:
//            AbstractAOSSigner, AOSCredentials, SigningAlgorithm

public class OSSSigner extends AbstractAOSSigner
{

    public OSSSigner(String httpVerb, String resourcePath)
    {
        this(httpVerb, resourcePath, null);
    }

    public OSSSigner(String httpVerb, String resourcePath, Date expiration)
    {
        this.httpVerb = httpVerb;
        this.resourcePath = resourcePath;
        this.expiration = expiration;
        if(resourcePath == null)
            throw new IllegalArgumentException("Parameter resourcePath is empty");
        else
            return;
    }

    public void sign(Request request, AOSCredentials credentials)
        throws AOSClientException
    {
        if(credentials == null)
        {
            log.debug("Canonical string will not be signed, as no AOS Secret Key was provided");
            return;
        }
        String expirationInSeconds = null;
        if(expiration != null)
            expirationInSeconds = Long.toString(expiration.getTime() / 1000L);
        request.addHeader("Date", ServiceUtils.formatRfc822Date(new Date()));
        String canonicalString = RestUtils.makeOSSCanonicalString(httpVerb, resourcePath, request, expirationInSeconds);
        log.info((new StringBuilder("Calculated string to sign:\n\"")).append(canonicalString).append("\"").toString());
        String signature = super.sign(canonicalString, credentials.getAOSSecretKey(), SigningAlgorithm.HmacSHA1);
        request.addHeader("Authorization", (new StringBuilder("OSS ")).append(credentials.getAOSAccessKeyId()).append(":").append(signature).toString());
    }

    public void signForUrl(Request request, AOSCredentials credentials)
        throws AOSClientException
    {
        String expirationInSeconds = Long.toString(expiration.getTime() / 1000L);
        request.addHeader("Date", expirationInSeconds);
        String canonicalString = RestUtils.makeOSSCanonicalString(httpVerb, resourcePath, request, null);
        String signature = super.sign(canonicalString, credentials.getAOSSecretKey(), SigningAlgorithm.HmacSHA1);
        request.addParameter("OSSAccessKeyId", credentials.getAOSAccessKeyId());
        request.addParameter("Expires", expirationInSeconds);
        request.addParameter("Signature", signature);
    }

    private static final Log log = LogFactory.getLog("com.aliyun.aos.auth.OSSSigner");
    private final String httpVerb;
    private final String resourcePath;
    private final Date expiration;

}
