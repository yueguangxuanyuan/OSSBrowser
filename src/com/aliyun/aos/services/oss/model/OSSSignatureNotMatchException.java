// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OSSSignatureNotMatchException.java

package com.aliyun.aos.services.oss.model;


// Referenced classes of package com.aliyun.aos.services.oss.model:
//            AOSOSSException

public class OSSSignatureNotMatchException extends AOSOSSException
{

    public OSSSignatureNotMatchException(String msg)
    {
        super(msg);
    }

    public String getStringToSign()
    {
        return stringToSign;
    }

    public void setStringToSign(String stringToSign)
    {
        this.stringToSign = stringToSign;
    }

    public String getStringToBytes()
    {
        return stringToBytes;
    }

    public void setStringToBytes(String stringToBytes)
    {
        this.stringToBytes = stringToBytes;
    }

    public String getAccessKeyId()
    {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId)
    {
        this.accessKeyId = accessKeyId;
    }

    public String getSignatureProvided()
    {
        return signatureProvided;
    }

    public void setSignatureProvided(String signatureProvided)
    {
        this.signatureProvided = signatureProvided;
    }

    public String toString()
    {
        return (new StringBuilder(String.valueOf(super.toString()))).append("\nAccessKeyID: ").append(accessKeyId).append(", StringToBytes: ").append(stringToBytes).append(", StringToSign: ").append(stringToSign).append(", SignatureProvided: ").append(signatureProvided).toString();
    }

    private static final long serialVersionUID = 0x146e7a70fb503bdcL;
    private String stringToSign;
    private String stringToBytes;
    private String accessKeyId;
    private String signatureProvided;
}
