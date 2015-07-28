// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IconType.java

package com.aliyun.oss.ossbrowser.model;


public final class IconType extends Enum
{

    private IconType(String s, int i)
    {
        super(s, i);
    }

    public static IconType[] values()
    {
        IconType aicontype[];
        int i;
        IconType aicontype1[];
        System.arraycopy(aicontype = ENUM$VALUES, 0, aicontype1 = new IconType[i = aicontype.length], 0, i);
        return aicontype1;
    }

    public static IconType valueOf(String s)
    {
        return (IconType)Enum.valueOf(com/aliyun/oss/ossbrowser/model/IconType, s);
    }

    public static final IconType STATIC;
    public static final IconType ANIMATED;
    private static final IconType ENUM$VALUES[];

    static 
    {
        STATIC = new IconType("STATIC", 0);
        ANIMATED = new IconType("ANIMATED", 1);
        ENUM$VALUES = (new IconType[] {
            STATIC, ANIMATED
        });
    }
}
