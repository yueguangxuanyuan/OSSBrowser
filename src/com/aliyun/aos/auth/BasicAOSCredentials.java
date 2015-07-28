// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasicAOSCredentials.java

package com.aliyun.aos.auth;


// Referenced classes of package com.aliyun.aos.auth:
//            AOSCredentials

public class BasicAOSCredentials
    implements AOSCredentials
{

    public BasicAOSCredentials(String accessId, String secretKey)
    {
        this.accessId = accessId;
        this.secretKey = secretKey;
    }

    public String getAOSAccessKeyId()
    {
        return accessId;
    }

    public String getAOSSecretKey()
    {
        return secretKey;
    }

    private final String accessId;
    private final String secretKey;
}
