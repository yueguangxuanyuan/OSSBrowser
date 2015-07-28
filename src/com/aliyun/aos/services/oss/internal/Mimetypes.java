// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Mimetypes.java

package com.aliyun.aos.services.oss.internal;

import java.io.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Mimetypes
{

    private Mimetypes()
    {
        extensionToMimetypeMap = new HashMap();
    }

    public static synchronized Mimetypes getInstance()
    {
        if(mimetypes != null)
            return mimetypes;
        mimetypes = new Mimetypes();
        InputStream mimetypesFile = mimetypes.getClass().getResourceAsStream("/mime.types");
        if(mimetypesFile != null)
        {
            if(log.isDebugEnabled())
                log.debug("Loading mime types from file in the classpath: mime.types");
            try
            {
                mimetypes.loadAndReplaceMimetypes(mimetypesFile);
            }
            catch(IOException e)
            {
                if(log.isErrorEnabled())
                    log.error("Failed to load mime types from file in the classpath: mime.types", e);
            }
        } else
        if(log.isWarnEnabled())
            log.warn("Unable to find 'mime.types' file in classpath");
        return mimetypes;
    }

    public void loadAndReplaceMimetypes(InputStream is)
        throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        for(String line = null; (line = br.readLine()) != null;)
        {
            line = line.trim();
            if(!line.startsWith("#") && line.length() != 0)
            {
                StringTokenizer st = new StringTokenizer(line, " \t");
                if(st.countTokens() > 1)
                {
                    String mimetype = st.nextToken();
                    while(st.hasMoreTokens()) 
                    {
                        String extension = st.nextToken();
                        extensionToMimetypeMap.put(extension, mimetype);
                        if(log.isDebugEnabled())
                            log.debug((new StringBuilder("Setting mime type for extension '")).append(extension).append("' to '").append(mimetype).append("'").toString());
                    }
                } else
                if(log.isDebugEnabled())
                    log.debug((new StringBuilder("Ignoring mimetype with no associated file extensions: '")).append(line).append("'").toString());
            }
        }

    }

    public String getMimetype(String fileName)
    {
        int lastPeriodIndex = fileName.lastIndexOf(".");
        if(lastPeriodIndex > 0 && lastPeriodIndex + 1 < fileName.length())
        {
            String ext = fileName.substring(lastPeriodIndex + 1);
            if(extensionToMimetypeMap.keySet().contains(ext))
            {
                String mimetype = (String)extensionToMimetypeMap.get(ext);
                if(log.isDebugEnabled())
                    log.debug((new StringBuilder("Recognised extension '")).append(ext).append("', mimetype is: '").append(mimetype).append("'").toString());
                return mimetype;
            }
            if(log.isDebugEnabled())
                log.debug((new StringBuilder("Extension '")).append(ext).append("' is unrecognized in mime type listing").append(", using default mime type: '").append("application/octet-stream").append("'").toString());
        } else
        if(log.isDebugEnabled())
            log.debug((new StringBuilder("File name has no extension, mime type cannot be recognised for: ")).append(fileName).toString());
        return "application/octet-stream";
    }

    public String getMimetype(File file)
    {
        return getMimetype(file.getName());
    }

    private static final Log log = LogFactory.getLog("com.aliyun.aos.services.oss.internal.Mimetypes");
    public static final String MIMETYPE_XML = "application/xml";
    public static final String MIMETYPE_HTML = "text/html";
    public static final String MIMETYPE_OCTET_STREAM = "application/octet-stream";
    public static final String MIMETYPE_GZIP = "application/x-gzip";
    private static Mimetypes mimetypes = null;
    private HashMap extensionToMimetypeMap;

}
