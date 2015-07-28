// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MultiObjectDeleteXmlFactory.java

package com.aliyun.aos.services.oss.model.transform;

import com.aliyun.aos.AOSClientException;
import com.aliyun.aos.services.oss.internal.XmlWriter;
import com.aliyun.aos.services.oss.model.DeleteObjectsRequest;
import java.util.Iterator;
import java.util.List;

public class MultiObjectDeleteXmlFactory
{

    public MultiObjectDeleteXmlFactory()
    {
    }

    public byte[] convertToXmlByteArray(DeleteObjectsRequest rq)
        throws AOSClientException
    {
        XmlWriter xml = new XmlWriter();
        xml.start("Delete");
        if(rq.getQuiet())
            xml.start("Quiet").value("true").end();
        String key;
        for(Iterator iterator = rq.getKeys().iterator(); iterator.hasNext(); writeKeyVersion(xml, key))
            key = (String)iterator.next();

        xml.end();
        return xml.getBytes();
    }

    private void writeKeyVersion(XmlWriter xml, String key)
    {
        xml.start("Object");
        xml.start("Key").value(key).end();
        xml.end();
    }
}
