// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SyncPoint.java

package com.aliyun.oss.ossbrowser.model;

import java.io.Serializable;
import java.util.Map;

public class SyncPoint
    implements Serializable
{

    public SyncPoint()
    {
    }

    public Map getMap()
    {
        return map;
    }

    public void setMap(Map map)
    {
        this.map = map;
    }

    private Map map;
}
