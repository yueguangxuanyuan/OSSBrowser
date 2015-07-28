// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SigningAlgorithm.java

package com.aliyun.aos.auth;


public final class SigningAlgorithm extends Enum
{

    private SigningAlgorithm(String s, int i)
    {
        super(s, i);
    }

    public static SigningAlgorithm[] values()
    {
        SigningAlgorithm asigningalgorithm[];
        int i;
        SigningAlgorithm asigningalgorithm1[];
        System.arraycopy(asigningalgorithm = ENUM$VALUES, 0, asigningalgorithm1 = new SigningAlgorithm[i = asigningalgorithm.length], 0, i);
        return asigningalgorithm1;
    }

    public static SigningAlgorithm valueOf(String s)
    {
        return (SigningAlgorithm)Enum.valueOf(com/aliyun/aos/auth/SigningAlgorithm, s);
    }

    public static final SigningAlgorithm HmacSHA1;
    private static final SigningAlgorithm ENUM$VALUES[];

    static 
    {
        HmacSHA1 = new SigningAlgorithm("HmacSHA1", 0);
        ENUM$VALUES = (new SigningAlgorithm[] {
            HmacSHA1
        });
    }
}
