// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VersionInfoUtils.java

package com.aliyun.aos.util;

import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class VersionInfoUtils
{

    public VersionInfoUtils()
    {
    }

    public static String getVersion()
    {
        return version;
    }

    public static String getVendor()
    {
        return vendor;
    }

    public static String getPlatform()
    {
        return platform;
    }

    public static String getUserAgent()
    {
        return userAgent;
    }

    private static void initializeVersion()
    {
        InputStream inputStream = com/aliyun/aos/util/VersionInfoUtils.getClassLoader().getResourceAsStream(VERSION_INFO_FILE);
        Properties versionInfoProperties = new Properties();
        try
        {
            if(inputStream == null)
                throw new Exception((new StringBuilder(String.valueOf(VERSION_INFO_FILE))).append(" not found on classpath").toString());
            versionInfoProperties.load(inputStream);
            version = versionInfoProperties.getProperty("version");
            platform = versionInfoProperties.getProperty("platform");
            vendor = versionInfoProperties.getProperty("vendor");
        }
        catch(Exception e)
        {
            log.info((new StringBuilder("Can not found version info file in this sdk ")).append(e.getMessage()).toString());
            version = "unknown-version";
            platform = "java";
            vendor = "qqai";
        }
    }

    private static void initializeUserAgent()
    {
        StringBuilder buffer = new StringBuilder(1024);
        buffer.append((new StringBuilder("vendor-")).append(getVendor().toLowerCase()).append("/").toString());
        buffer.append((new StringBuilder("aos-sdk-")).append(getPlatform().toLowerCase()).append("/").toString());
        buffer.append(getVersion());
        buffer.append(" ");
        buffer.append((new StringBuilder(String.valueOf(System.getProperty("os.name").replace(' ', '_')))).append("/").append(System.getProperty("os.version").replace(' ', '_')).toString());
        buffer.append(" ");
        buffer.append((new StringBuilder(String.valueOf(System.getProperty("java.vm.name").replace(' ', '_')))).append("/").append(System.getProperty("java.vm.version").replace(' ', '_')).toString());
        String region = "";
        try
        {
            region = (new StringBuilder(" ")).append(System.getProperty("user.language").replace(' ', '_')).append("_").append(System.getProperty("user.region").replace(' ', '_')).toString();
        }
        catch(NullPointerException nullpointerexception) { }
        buffer.append(region);
        userAgent = buffer.toString();
    }

    private static String version;
    private static String platform;
    private static String userAgent;
    private static String vendor;
    private static Log log = LogFactory.getLog(com/aliyun/aos/util/VersionInfoUtils);
    private static String VERSION_INFO_FILE = "com/aliyun/aos/sdk/versionInfo.properties";

    static 
    {
        initializeVersion();
        initializeUserAgent();
    }
}
