// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimingInfo.java

package com.aliyun.aos.util;

import java.util.HashMap;
import java.util.Map;

public class TimingInfo
{

    public TimingInfo()
    {
        this(System.currentTimeMillis(), -1L);
    }

    public TimingInfo(long startTime)
    {
        this(startTime, -1L);
    }

    public TimingInfo(long startTime, long endTime)
    {
        subMeasurementsByName = new HashMap();
        countersByName = new HashMap();
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public long getEndTime()
    {
        return endTime;
    }

    public void setEndTime(long endTime)
    {
        this.endTime = endTime;
    }

    public void addSubMeasurement(String subMeasurementName, TimingInfo timingInfo)
    {
        subMeasurementsByName.put(subMeasurementName, timingInfo);
    }

    public TimingInfo getSubMeasurement(String subMeasurementName)
    {
        return (TimingInfo)subMeasurementsByName.get(subMeasurementName);
    }

    public void addCounter(String key, Number value)
    {
        countersByName.put(key, value);
    }

    public Number getCounter(String key)
    {
        return (Number)countersByName.get(key);
    }

    private final long startTime;
    private long endTime;
    private Map subMeasurementsByName;
    private Map countersByName;
}
