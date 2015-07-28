// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileNameComparator.java

package com.aliyun.oss.ossbrowser.model;

import com.aliyun.aos.services.oss.model.OSSObjectSummary;
import java.util.Comparator;

// Referenced classes of package com.aliyun.oss.ossbrowser.model:
//            IconAndObjectSummary, IconType

public class FileNameComparator
    implements Comparator
{

    public FileNameComparator()
    {
    }

    public int compare(Object o1, Object o2)
    {
        if(((IconAndObjectSummary)o1).getIconType() == IconType.ANIMATED)
            return -1;
        if(((IconAndObjectSummary)o2).getIconType() == IconType.ANIMATED)
            return 1;
        String s1 = ((IconAndObjectSummary)o1).getSummary().getKey();
        String s2 = ((IconAndObjectSummary)o2).getSummary().getKey();
        String ss[] = s1.split("/");
        if(ss[ss.length - 1].equals(".."))
            return -1;
        if(isFolderPath(s1))
            s1 = (new StringBuilder(String.valueOf(ss[ss.length - 1]))).append("/").toString();
        else
            s1 = ss[ss.length - 1];
        ss = s2.split("/");
        if(ss[ss.length - 1].equals(".."))
            return 1;
        if(isFolderPath(s2))
            s2 = (new StringBuilder(String.valueOf(ss[ss.length - 1]))).append("/").toString();
        else
            s2 = ss[ss.length - 1];
        if(s1.endsWith("/") == s2.endsWith("/"))
            return s1.compareTo(s2);
        else
            return s1.endsWith("/") ? -1 : 1;
    }

    private static boolean isFolderPath(String s)
    {
        return s.lastIndexOf('/') == s.length() - 1;
    }
}
