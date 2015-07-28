// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AOSCredentials.java

package com.aliyun.aos.auth;


public interface AOSCredentials
{

    public abstract String getAOSAccessKeyId();

    public abstract String getAOSSecretKey();
}
