// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bucket.java

package com.aliyun.aos.services.oss.model;

import java.util.Date;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            Owner

public class Bucket
{

    public Bucket()
    {
        name = null;
        owner = null;
        creationDate = null;
    }

    public Bucket(String name)
    {
        this.name = null;
        owner = null;
        creationDate = null;
        this.name = name;
    }

    public Owner getOwner()
    {
        return owner;
    }

    public void setOwner(Owner owner)
    {
        this.owner = owner;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return (new StringBuilder("OSSBucket [name=")).append(getName()).append(", creationDate=").append(getCreationDate()).append(", owner=").append(getOwner()).append("]").toString();
    }

    private String name;
    private Owner owner;
    private Date creationDate;
}
