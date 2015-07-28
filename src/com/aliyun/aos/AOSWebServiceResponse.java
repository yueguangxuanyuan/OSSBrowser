// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AOSWebServiceResponse.java

package com.aliyun.aos;


// Referenced classes of package com.aliyun.aos:
//            ResponseMetadata

public class AOSWebServiceResponse
{

    public AOSWebServiceResponse()
    {
    }

    public Object getResult()
    {
        return result;
    }

    public void setResult(Object result)
    {
        this.result = result;
    }

    public void setResponseMetadata(ResponseMetadata responseMetadata)
    {
        this.responseMetadata = responseMetadata;
    }

    public ResponseMetadata getResponseMetadata()
    {
        return responseMetadata;
    }

    public String getRequestId()
    {
        if(responseMetadata == null)
            return null;
        else
            return responseMetadata.getRequestId();
    }

    private Object result;
    private ResponseMetadata responseMetadata;
}
