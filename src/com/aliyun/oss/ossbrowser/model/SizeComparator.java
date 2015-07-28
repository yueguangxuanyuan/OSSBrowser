// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SizeComparator.java

package com.aliyun.oss.ossbrowser.model;

import java.util.Comparator;

public class SizeComparator
    implements Comparator
{

    public SizeComparator()
    {
    }

    public int compare(Object o1, Object o2)
    {
        int i = (Long)o1 != (Long)o2 ? ((int) (((Long)o1).longValue() <= ((Long)o2).longValue() ? -1 : 1)) : 0;
        return i;
    }
}
