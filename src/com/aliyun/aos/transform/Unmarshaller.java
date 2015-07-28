// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Unmarshaller.java

package com.aliyun.aos.transform;


public interface Unmarshaller
{

    public abstract Object unmarshall(Object obj)
        throws Exception;
}
