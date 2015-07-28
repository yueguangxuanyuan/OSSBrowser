// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Signer.java

package com.aliyun.aos.auth;

import com.aliyun.aos.AOSClientException;
import com.aliyun.aos.Request;

// Referenced classes of package com.aliyun.aos.auth:
//            AOSCredentials

public interface Signer
{

    public abstract void sign(Request request, AOSCredentials aoscredentials)
        throws AOSClientException;
}
