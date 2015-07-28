// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProgressListener.java

package com.aliyun.aos.services.oss.model;


// Referenced classes of package com.aliyun.aos.services.oss.model:
//            ProgressEvent

public interface ProgressListener
{

    public abstract void progressChanged(ProgressEvent progressevent);
}
