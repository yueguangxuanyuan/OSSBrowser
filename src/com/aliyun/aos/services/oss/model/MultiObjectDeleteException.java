// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MultiObjectDeleteException.java

package com.aliyun.aos.services.oss.model;

import java.util.*;

// Referenced classes of package com.aliyun.aos.services.oss.model:
//            AOSOSSException

public class MultiObjectDeleteException extends AOSOSSException
{
    public static class DeleteError
    {

        public String getKey()
        {
            return key;
        }

        public void setKey(String key)
        {
            this.key = key;
        }

        public String getCode()
        {
            return code;
        }

        public void setCode(String code)
        {
            this.code = code;
        }

        public String getMessage()
        {
            return message;
        }

        public void setMessage(String message)
        {
            this.message = message;
        }

        private String key;
        private String code;
        private String message;

        public DeleteError()
        {
        }
    }


    public MultiObjectDeleteException(Collection errors, Collection deletedObjects)
    {
        super("One or more objects could not be deleted");
        this.deletedObjects.addAll(deletedObjects);
        this.errors.addAll(errors);
    }

    public List getDeletedObjects()
    {
        return deletedObjects;
    }

    public List getErrors()
    {
        return errors;
    }

    private static final long serialVersionUID = 0xe42f9a6498fd26eeL;
    private final List errors = new ArrayList();
    private final List deletedObjects = new ArrayList();
}
