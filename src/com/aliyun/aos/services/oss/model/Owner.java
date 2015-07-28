// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Owner.java

package com.aliyun.aos.services.oss.model;

import com.aliyun.aos.http.AOSResponseResult;
import java.io.Serializable;

public class Owner extends AOSResponseResult
    implements Serializable
{

    public Owner()
    {
    }

    public Owner(String id, String displayName)
    {
        this.id = id;
        this.displayName = displayName;
    }

    public String toString()
    {
        return (new StringBuilder("OSSOwner [name=")).append(getDisplayName()).append(",id=").append(getId()).append("]").toString();
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String name)
    {
        displayName = name;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Owner))
            return false;
        Owner otherOwner = (Owner)obj;
        String otherOwnerId = otherOwner.getId();
        String otherOwnerName = otherOwner.getDisplayName();
        String thisOwnerId = getId();
        String thisOwnerName = getDisplayName();
        if(otherOwnerId == null)
            otherOwnerId = "";
        if(otherOwnerName == null)
            otherOwnerName = "";
        if(thisOwnerId == null)
            thisOwnerId = "";
        if(thisOwnerName == null)
            thisOwnerName = "";
        return otherOwnerId.equals(thisOwnerId) && otherOwnerName.equals(thisOwnerName);
    }

    public int hashCode()
    {
        if(id != null)
            return id.hashCode();
        else
            return 0;
    }

    private static final long serialVersionUID = 0x844167fb974484e5L;
    private String displayName;
    private String id;
}
