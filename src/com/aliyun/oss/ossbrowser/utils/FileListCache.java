// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileListCache.java

package com.aliyun.oss.ossbrowser.utils;

import java.util.*;

public class FileListCache
{

    public FileListCache(int maxCacheSize)
    {
        lock = new Object();
        this.maxCacheSize = maxCacheSize;
        pos = 0;
        fileMap = new HashMap();
        posMap = new HashMap();
    }

    public void add(String bucket, String prefix, List fileList)
    {
        String path = (new StringBuilder(String.valueOf(bucket))).append("H!a@R#d$C%o^D&e*()_+-<>?").append(prefix).toString();
        synchronized(lock)
        {
            if(fileMap.size() < maxCacheSize || posMap.containsKey(path))
            {
                fileMap.put(path, fileList);
                posMap.put(path, Integer.valueOf(pos++));
            } else
            {
                String oldKey = null;
                int min = 0x40000000;
                for(Iterator iterator = posMap.entrySet().iterator(); iterator.hasNext();)
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                    if(oldKey == null || min > ((Integer)entry.getValue()).intValue())
                    {
                        min = ((Integer)entry.getValue()).intValue();
                        oldKey = (String)entry.getKey();
                    }
                }

                fileMap.remove(oldKey);
                posMap.remove(oldKey);
                fileMap.put(path, fileList);
                posMap.put(path, Integer.valueOf(pos++));
            }
        }
    }

    public List get(String bucket, String prefix)
    {
        String path = (new StringBuilder(String.valueOf(bucket))).append("H!a@R#d$C%o^D&e*()_+-<>?").append(prefix).toString();
        Object obj = lock;
        JVM INSTR monitorenter ;
        if(fileMap.containsKey(path))
        {
            posMap.put(path, Integer.valueOf(pos++));
            return (List)fileMap.get(path);
        }
        obj;
        JVM INSTR monitorexit ;
        return null;
        obj;
        JVM INSTR monitorexit ;
        throw ;
    }

    private final String hardcode = "H!a@R#d$C%o^D&e*()_+-<>?";
    private int maxCacheSize;
    private Map fileMap;
    private Map posMap;
    private int pos;
    private Object lock;
}
