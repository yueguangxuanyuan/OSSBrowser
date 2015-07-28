// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Request.java

package com.aliyun.aos;

import com.aliyun.aos.http.HttpMethodName;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;

// Referenced classes of package com.aliyun.aos:
//            AOSWebServiceRequest

public interface Request
{

    public abstract void addHeader(String s, String s1);

    public abstract Map getHeaders();

    public abstract void setHeaders(Map map);

    public abstract void setResourcePath(String s);

    public abstract String getResourcePath();

    public abstract void addParameter(String s, String s1);

    public abstract Request withParameter(String s, String s1);

    public abstract Map getParameters();

    public abstract void setParameters(Map map);

    public abstract URI getEndpoint();

    public abstract void setEndpoint(URI uri);

    public abstract HttpMethodName getHttpMethod();

    public abstract void setHttpMethod(HttpMethodName httpmethodname);

    public abstract InputStream getContent();

    public abstract void setContent(InputStream inputstream);

    public abstract String getServiceName();

    public abstract AOSWebServiceRequest getOriginalRequest();
}
