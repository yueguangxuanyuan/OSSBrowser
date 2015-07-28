// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequestXmlFactory.java

package com.aliyun.aos.services.oss.model.transform;

import com.aliyun.aos.services.oss.internal.XmlWriter;
import com.aliyun.aos.services.oss.model.FilePart;
import com.aliyun.aos.services.oss.model.PartETag;
import java.util.*;

public class RequestXmlFactory
{

    public RequestXmlFactory()
    {
    }

    public static byte[] convertToXmlByteArray(Set fileParts)
    {
        XmlWriter xml = new XmlWriter();
        xml.start("CreateFileGroup");
        if(fileParts != null)
        {
            for(Iterator iterator = fileParts.iterator(); iterator.hasNext(); xml.end())
            {
                FilePart part = (FilePart)iterator.next();
                xml.start("Part");
                xml.start("PartNumber").value(Integer.toString(part.getPartID())).end();
                xml.start("PartName").value(part.getPartName()).end();
                xml.start("ETag").value(part.getETag()).end();
            }

        }
        xml.end();
        return xml.getBytes();
    }

    public static byte[] convertToXmlByteArray(List partETags)
    {
        XmlWriter xml = new XmlWriter();
        xml.start("CompleteMultipartUpload");
        if(partETags != null)
        {
            for(Iterator iterator = partETags.iterator(); iterator.hasNext(); xml.end())
            {
                PartETag partEtag = (PartETag)iterator.next();
                xml.start("Part");
                xml.start("PartNumber").value(Integer.toString(partEtag.getPartNumber())).end();
                xml.start("ETag").value(partEtag.getETag()).end();
            }

        }
        xml.end();
        return xml.getBytes();
    }
}
