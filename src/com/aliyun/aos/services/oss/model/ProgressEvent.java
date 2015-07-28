// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProgressEvent.java

package com.aliyun.aos.services.oss.model;


public class ProgressEvent
{

    public ProgressEvent(int bytesTransfered)
    {
        this.bytesTransfered = bytesTransfered;
    }

    public void setBytesTransfered(int bytesTransfered)
    {
        this.bytesTransfered = bytesTransfered;
    }

    public int getBytesTransfered()
    {
        return bytesTransfered;
    }

    public int getEventCode()
    {
        return eventCode;
    }

    public void setEventCode(int eventType)
    {
        eventCode = eventType;
    }

    public static final int DEFAULT_EVENT_CODE = 0;
    public static final int STARTED_EVENT_CODE = 1;
    public static final int COMPLETED_EVENT_CODE = 2;
    public static final int FAILED_EVENT_CODE = 4;
    public static final int CANCELED_EVENT_CODE = 8;
    public static final int PART_STARTED_EVENT_CODE = 1024;
    public static final int PART_COMPLETED_EVENT_CODE = 2048;
    public static final int PART_FAILED_EVENT_CODE = 4096;
    private int bytesTransfered;
    private int eventCode;
}
