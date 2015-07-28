// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogLevel.java

package com.aliyun.oss.ossbrowser.model;


public final class LogLevel extends Enum
{

    private LogLevel(String s, int i)
    {
        super(s, i);
    }

    public static LogLevel[] values()
    {
        LogLevel aloglevel[];
        int i;
        LogLevel aloglevel1[];
        System.arraycopy(aloglevel = ENUM$VALUES, 0, aloglevel1 = new LogLevel[i = aloglevel.length], 0, i);
        return aloglevel1;
    }

    public static LogLevel valueOf(String s)
    {
        return (LogLevel)Enum.valueOf(com/aliyun/oss/ossbrowser/model/LogLevel, s);
    }

    public static final LogLevel INFO;
    public static final LogLevel WARNING;
    public static final LogLevel ERROR;
    private static final LogLevel ENUM$VALUES[];

    static 
    {
        INFO = new LogLevel("INFO", 0);
        WARNING = new LogLevel("WARNING", 1);
        ERROR = new LogLevel("ERROR", 2);
        ENUM$VALUES = (new LogLevel[] {
            INFO, WARNING, ERROR
        });
    }
}
