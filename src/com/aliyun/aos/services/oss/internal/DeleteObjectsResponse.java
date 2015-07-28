// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteObjectsResponse.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.http.AOSResponseResult;
import com.aliyun.aos.services.oss.model.MultiObjectDeleteException;
import java.util.ArrayList;
import java.util.List;

public class DeleteObjectsResponse extends AOSResponseResult
{

    public DeleteObjectsResponse(List deletedObjects, List errors)
    {
        this.deletedObjects = new ArrayList();
        this.errors = new ArrayList();
        this.deletedObjects = deletedObjects;
        this.errors = errors;
    }

    public List getDeletedObjects()
    {
        return deletedObjects;
    }

    public void setDeletedObjects(List deletedObjects)
    {
        this.deletedObjects = deletedObjects;
    }

    public List getErrors()
    {
        return errors;
    }

    public void setErrors(List errors)
    {
        this.errors = errors;
    }

    private List deletedObjects;
    private List errors;
}
