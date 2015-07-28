// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CannedAccessControlList.java

package com.aliyun.aos.services.oss.model;


public final class CannedAccessControlList extends Enum
{

    private CannedAccessControlList(String s, int i, String cannedAclHeader)
    {
        super(s, i);
        this.cannedAclHeader = cannedAclHeader;
    }

    public String toString()
    {
        return cannedAclHeader;
    }

    public static CannedAccessControlList valueOfAccessControlList(String val)
    {
        if(Private.cannedAclHeader.equals(val))
            return Private;
        if(PublicRead.cannedAclHeader.equals(val))
            return PublicRead;
        if(PublicReadWrite.cannedAclHeader.equals(val))
            return PublicReadWrite;
        else
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(val))).append(" is not acceptable").toString());
    }

    public static CannedAccessControlList[] values()
    {
        CannedAccessControlList acannedaccesscontrollist[];
        int i;
        CannedAccessControlList acannedaccesscontrollist1[];
        System.arraycopy(acannedaccesscontrollist = ENUM$VALUES, 0, acannedaccesscontrollist1 = new CannedAccessControlList[i = acannedaccesscontrollist.length], 0, i);
        return acannedaccesscontrollist1;
    }

    public static CannedAccessControlList valueOf(String s)
    {
        return (CannedAccessControlList)Enum.valueOf(com/aliyun/aos/services/oss/model/CannedAccessControlList, s);
    }

    public static final CannedAccessControlList Private;
    public static final CannedAccessControlList PublicRead;
    public static final CannedAccessControlList PublicReadWrite;
    private final String cannedAclHeader;
    private static final CannedAccessControlList ENUM$VALUES[];

    static 
    {
        Private = new CannedAccessControlList("Private", 0, "private");
        PublicRead = new CannedAccessControlList("PublicRead", 1, "public-read");
        PublicReadWrite = new CannedAccessControlList("PublicReadWrite", 2, "public-read-write");
        ENUM$VALUES = (new CannedAccessControlList[] {
            Private, PublicRead, PublicReadWrite
        });
    }
}
