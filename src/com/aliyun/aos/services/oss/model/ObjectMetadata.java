// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ObjectMetadata.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.http.AOSResponseResult;
import java.util.*;

public class ObjectMetadata extends AOSResponseResult
{

    public ObjectMetadata()
    {
        userMetadata = new HashMap();
        metadata = new HashMap();
    }

    public Map getUserMetadata()
    {
        return userMetadata;
    }

    public void setUserMetadata(Map userMetadata)
    {
        this.userMetadata = userMetadata;
    }

    public void setHeader(String key, Object value)
    {
        metadata.put(key, value);
    }

    public void addUserMetadata(String key, String value)
    {
        userMetadata.put(key, value);
    }

    public Map getRawMetadata()
    {
        return Collections.unmodifiableMap(metadata);
    }

    public Date getLastModified()
    {
        return (Date)metadata.get("Last-Modified");
    }

    public void setLastModified(Date lastModified)
    {
        metadata.put("Last-Modified", lastModified);
    }

    public long getContentLength()
    {
        Long contentLength = (Long)metadata.get("Content-Length");
        if(contentLength == null)
            return 0L;
        else
            return contentLength.longValue();
    }

    public void setContentLength(long contentLength)
    {
        metadata.put("Content-Length", Long.valueOf(contentLength));
    }

    public String getContentType()
    {
        return (String)metadata.get("Content-Type");
    }

    public void setContentType(String contentType)
    {
        metadata.put("Content-Type", contentType);
    }

    public String getContentEncoding()
    {
        return (String)metadata.get("Content-Encoding");
    }

    public void setContentEncoding(String encoding)
    {
        metadata.put("Content-Encoding", encoding);
    }

    public String getCacheControl()
    {
        return (String)metadata.get("Cache-Control");
    }

    public void setCacheControl(String cacheControl)
    {
        metadata.put("Cache-Control", cacheControl);
    }

    public void setContentMD5(String md5Base64)
    {
        metadata.put("Content-MD5", md5Base64);
    }

    public String getContentMD5()
    {
        return (String)metadata.get("Content-MD5");
    }

    public void setContentDisposition(String disposition)
    {
        metadata.put("Content-Disposition", disposition);
    }

    public String getContentDisposition()
    {
        return (String)metadata.get("Content-Disposition");
    }

    public String getContentLanguage()
    {
        return (String)metadata.get("Content-Language");
    }

    public void SetContentLanguage(String contentLanguage)
    {
        metadata.put("Content-Language", contentLanguage);
    }

    public String getETag()
    {
        return (String)metadata.get("ETag");
    }

    public String getBucketName()
    {
        return bucketName;
    }

    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Meta:[");
        Iterator it = metadata.entrySet().iterator();
        boolean first = true;
        java.util.Map.Entry entry;
        for(; it.hasNext(); sb.append((String)entry.getKey()).append(":").append(entry.getValue()))
        {
            if(!first)
                sb.append(", ");
            else
                first = false;
            entry = (java.util.Map.Entry)it.next();
        }

        sb.append("]\n");
        sb.append("UserMeta:[");
        java.util.Map.Entry entry;
        for(Iterator it1 = userMetadata.entrySet().iterator(); it1.hasNext(); sb.append((String)entry.getKey()).append(":").append((String)entry.getValue()))
        {
            if(!first)
                sb.append(", ");
            else
                first = false;
            entry = (java.util.Map.Entry)it1.next();
        }

        sb.append("]");
        return sb.toString();
    }

    private String bucketName;
    private String key;
    private Map userMetadata;
    private Map metadata;
}
