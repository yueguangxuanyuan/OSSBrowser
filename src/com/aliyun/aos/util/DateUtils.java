// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DateUtils.java

package com.aliyun.aos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils
{

    public DateUtils()
    {
        rfc822DateParser = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        iso8601DateParser.setTimeZone(new SimpleTimeZone(0, "GMT"));
        rfc822DateParser.setTimeZone(new SimpleTimeZone(0, "GMT"));
        alternateIso8601DateParser.setTimeZone(new SimpleTimeZone(0, "GMT"));
    }

    public Date parseIso8601Date(String dateString)
        throws ParseException
    {
        SimpleDateFormat simpledateformat = iso8601DateParser;
        JVM INSTR monitorenter ;
        return iso8601DateParser.parse(dateString);
        simpledateformat;
        JVM INSTR monitorexit ;
        throw ;
        ParseException e;
        e;
        SimpleDateFormat simpledateformat1 = alternateIso8601DateParser;
        JVM INSTR monitorenter ;
        return alternateIso8601DateParser.parse(dateString);
        simpledateformat1;
        JVM INSTR monitorexit ;
        throw ;
    }

    public String formatIso8601Date(Date date)
    {
        SimpleDateFormat simpledateformat = iso8601DateParser;
        JVM INSTR monitorenter ;
        return iso8601DateParser.format(date);
        simpledateformat;
        JVM INSTR monitorexit ;
        throw ;
    }

    public Date parseRfc822Date(String dateString)
        throws ParseException
    {
        SimpleDateFormat simpledateformat = rfc822DateParser;
        JVM INSTR monitorenter ;
        return rfc822DateParser.parse(dateString);
        simpledateformat;
        JVM INSTR monitorexit ;
        throw ;
    }

    public String formatRfc822Date(Date date)
    {
        SimpleDateFormat simpledateformat = rfc822DateParser;
        JVM INSTR monitorenter ;
        return rfc822DateParser.format(date);
        simpledateformat;
        JVM INSTR monitorexit ;
        throw ;
    }

    protected final SimpleDateFormat iso8601DateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    protected final SimpleDateFormat alternateIso8601DateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    protected final SimpleDateFormat rfc822DateParser;
}
