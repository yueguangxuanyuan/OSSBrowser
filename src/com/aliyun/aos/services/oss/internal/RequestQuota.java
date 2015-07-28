// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequestQuota.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.http.AOSResponseResult;
import java.util.Date;

public class RequestQuota extends AOSResponseResult
{

    public RequestQuota()
    {
    }

    public RequestQuota(String bucket, String user, Date timeStamp, long dataSize, int qpsI, int qpsII, 
            long send, long recv)
    {
        this.bucket = bucket;
        this.user = user;
        this.timeStamp = timeStamp;
        this.dataSize = dataSize;
        this.qpsI = qpsI;
        this.qpsII = qpsII;
        this.send = send;
        this.recv = recv;
    }

    public String getBucket()
    {
        return bucket;
    }

    public void setBucket(String bucket)
    {
        this.bucket = bucket;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public Date getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public long getDataSize()
    {
        return dataSize;
    }

    public void setDataSize(long dataSize)
    {
        this.dataSize = dataSize;
    }

    public int getQpsI()
    {
        return qpsI;
    }

    public void setQpsI(int qpsI)
    {
        this.qpsI = qpsI;
    }

    public int getQpsII()
    {
        return qpsII;
    }

    public void setQpsII(int qpsII)
    {
        this.qpsII = qpsII;
    }

    public long getSend()
    {
        return send;
    }

    public void setSend(long send)
    {
        this.send = send;
    }

    public long getRecv()
    {
        return recv;
    }

    public void setRecv(long recv)
    {
        this.recv = recv;
    }

    public String toString()
    {
        return (new StringBuilder("RequestQuota [bucket=")).append(bucket).append(", user=").append(user).append(", timeStamp=").append(timeStamp).append(", dataSize=").append(dataSize).append(", qpsI=").append(qpsI).append(", qpsII=").append(qpsII).append(", send=").append(send).append(", recv=").append(recv).append("]").toString();
    }

    private String bucket;
    private String user;
    private Date timeStamp;
    private long dataSize;
    private int qpsI;
    private int qpsII;
    private long send;
    private long recv;
}
