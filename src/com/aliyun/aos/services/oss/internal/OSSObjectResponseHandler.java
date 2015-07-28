// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OSSObjectResponseHandler.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.AOSWebServiceResponse;
import com.aliyun.aos.http.HttpResponse;
import com.aliyun.aos.services.oss.model.OSSObject;

// Referenced classes of package com.aliyun.aos.services.oss.internal:
//            AbstractOSSResponseHandler

public class OSSObjectResponseHandler extends AbstractOSSResponseHandler
{

    public OSSObjectResponseHandler()
    {
    }

    public AOSWebServiceResponse handle(HttpResponse response)
        throws Exception
    {
        OSSObject object = new OSSObject();
        object.setObjectContent(response.getContent());
        populateObjectMetadata(response, object.getObjectMetadata());
        AOSWebServiceResponse aosResponse = parseResponseMetadata(response);
        aosResponse.setResult(object);
        return aosResponse;
    }

    public boolean needsConnectionLeftOpen()
    {
        return true;
    }

}
