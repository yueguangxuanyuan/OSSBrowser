// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OSSQueryQuotaHandler.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.AOSWebServiceResponse;
import com.aliyun.aos.http.HttpResponse;
import java.util.*;

// Referenced classes of package com.aliyun.aos.services.oss.internal:
//            AbstractOSSResponseHandler, RequestQuota

public class OSSQueryQuotaHandler extends AbstractOSSResponseHandler
{

    public OSSQueryQuotaHandler()
    {
    }

    public AOSWebServiceResponse handle(HttpResponse response)
        throws Exception
    {
        RequestQuota quota = new RequestQuota();
        populateObjectMetadata(response, quota);
        AOSWebServiceResponse awsResponse = parseResponseMetadata(response);
        awsResponse.setResult(quota);
        return awsResponse;
    }

    private void populateObjectMetadata(HttpResponse response, RequestQuota quota)
    {
        Map param = new HashMap();
        param.put("User", "User");
        param.put("Bucket", "Bucket");
        param.put("TimeStamp", "User");
        param.put("User", "User");
        param.put("User", "User");
        param.put("User", "User");
        param.put("User", "User");
        String user = (String)response.getHeaders().get("User");
        String bucket = (String)response.getHeaders().get("Bucket");
        String timeStr = (String)response.getHeaders().get("TimeStamp");
        String dataSize = (String)response.getHeaders().get("DataSize");
        String qpsI = (String)response.getHeaders().get("QPSI");
        String qpsII = (String)response.getHeaders().get("QPSII");
        String send = (String)response.getHeaders().get("Send");
        String recv = (String)response.getHeaders().get("Recv");
        quota.setUser(user);
        quota.setBucket(bucket);
        quota.setTimeStamp(new Date(Long.valueOf(timeStr).longValue() * 1000L));
        quota.setDataSize(Long.valueOf(dataSize).longValue());
        quota.setQpsI(Integer.valueOf(qpsI).intValue());
        quota.setQpsII(Integer.valueOf(qpsII).intValue());
        quota.setSend(Long.valueOf(send).longValue());
        quota.setRecv(Long.valueOf(recv).longValue());
    }

    public static final String QUOTA_USER = "User";
    public static final String QUOTA_BUCKET = "Bucket";
    public static final String QUOTA_TIME_STAMP = "TimeStamp";
    public static final String QUOTA_DATA_SIZE = "DataSize";
    public static final String QUOTA_QPSI = "QPSI";
    public static final String QUOTA_QPSII = "QPSII";
    public static final String QUOTA_SEND = "Send";
    public static final String QUOTA_RECV = "Recv";
}
