// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HttpResponseHandler.java

package com.aliyun.aos.http;


// Referenced classes of package com.aliyun.aos.http:
//            HttpResponse

public interface HttpResponseHandler
{

    public abstract Object handle(HttpResponse httpresponse)
        throws Exception;

    public abstract boolean needsConnectionLeftOpen();
}
