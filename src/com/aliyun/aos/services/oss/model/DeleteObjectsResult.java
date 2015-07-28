// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteObjectsResult.java

package com.aliyun.aos.services.oss.model;

import java.util.ArrayList;
import java.util.List;

public class DeleteObjectsResult
{

    public DeleteObjectsResult(List deletedObjects)
    {
        this.deletedObjects.addAll(deletedObjects);
    }

    public List getDeletedObjects()
    {
        return deletedObjects;
    }

    private final List deletedObjects = new ArrayList();
}
