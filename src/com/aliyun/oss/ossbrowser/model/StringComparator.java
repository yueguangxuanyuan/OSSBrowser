// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StringComparator.java

package com.aliyun.oss.ossbrowser.model;

import java.util.Comparator;

public class StringComparator
    implements Comparator
{

    public StringComparator()
    {
    }

    public int compare(Object o1, Object o2)
    {
        String s1 = (String)o1;
        String s2 = (String)o2;
        return s1.compareTo(s2);
    }
}
