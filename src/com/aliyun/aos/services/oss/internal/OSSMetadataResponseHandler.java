// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OSSMetadataResponseHandler.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.AOSWebServiceResponse;
import com.aliyun.aos.http.HttpResponse;
import com.aliyun.aos.services.oss.model.ObjectMetadata;

// Referenced classes of package com.aliyun.aos.services.oss.internal:
//            AbstractOSSResponseHandler

public class OSSMetadataResponseHandler extends AbstractOSSResponseHandler
{

    public OSSMetadataResponseHandler()
    {
    }

    public AOSWebServiceResponse handle(HttpResponse response)
        throws Exception
    {
        ObjectMetadata metadata = new ObjectMetadata();
        populateObjectMetadata(response, metadata);
        AOSWebServiceResponse awsResponse = parseResponseMetadata(response);
        awsResponse.setResult(metadata);
        return awsResponse;
    }

}
