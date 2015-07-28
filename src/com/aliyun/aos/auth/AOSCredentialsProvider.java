// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AOSCredentialsProvider.java

package com.aliyun.aos.auth;


// Referenced classes of package com.aliyun.aos.auth:
//            AOSCredentials

public interface AOSCredentialsProvider
{

    public abstract AOSCredentials getCredentials();

    public abstract void refresh();
}
