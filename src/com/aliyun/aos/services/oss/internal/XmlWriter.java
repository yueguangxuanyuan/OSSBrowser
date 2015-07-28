// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlWriter.java

package com.aliyun.aos.services.oss.internal;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.aliyun.aos.services.oss.internal:
//            Constants

public class XmlWriter
{

    public XmlWriter()
    {
        tags = new ArrayList();
        sb = new StringBuilder();
    }

    public XmlWriter start(String name)
    {
        sb.append("<").append(name).append(">");
        tags.add(name);
        return this;
    }

    public XmlWriter start(String name, String attr, String value)
    {
        sb.append("<").append(name);
        writeAttr(attr, value);
        sb.append(">");
        tags.add(name);
        return this;
    }

    public XmlWriter start(String name, String attrs[], String values[])
    {
        sb.append("<").append(name);
        for(int i = 0; i < Math.min(attrs.length, values.length); i++)
            writeAttr(attrs[i], values[i]);

        sb.append(">");
        tags.add(name);
        return this;
    }

    public XmlWriter end()
    {
        if(!$assertionsDisabled && tags.size() <= 0)
        {
            throw new AssertionError();
        } else
        {
            String name = (String)tags.remove(tags.size() - 1);
            sb.append("</").append(name).append(">");
            return this;
        }
    }

    public byte[] getBytes()
    {
        if(!$assertionsDisabled && tags.size() != 0)
            throw new AssertionError();
        try
        {
            return toString().getBytes(Constants.DEFAULT_ENCODING);
        }
        catch(UnsupportedEncodingException e)
        {
            return toString().toString().getBytes();
        }
    }

    public String toString()
    {
        return sb.toString();
    }

    public XmlWriter value(String value)
    {
        appendEscapedString(value, sb);
        return this;
    }

    private void writeAttr(String name, String value)
    {
        sb.append(' ').append(name).append("=\"");
        appendEscapedString(value, sb);
        sb.append("\"");
    }

    private void appendEscapedString(String s, StringBuilder builder)
    {
        int start = 0;
        int len = s.length();
        int pos;
        for(pos = 0; pos < len; pos++)
        {
            char ch = s.charAt(pos);
            String escape;
            switch(ch)
            {
            case 38: // '&'
                escape = "&amp;";
                break;

            case 34: // '"'
                escape = "&quote;";
                break;

            case 60: // '<'
                escape = "&lt;";
                break;

            case 62: // '>'
                escape = "&gt;";
                break;

            default:
                escape = null;
                break;
            }
            if(escape != null)
            {
                if(start < pos)
                    builder.append(s, start, pos);
                sb.append(escape);
                start = pos + 1;
            }
        }

        if(start < pos)
            sb.append(s, start, pos);
    }

    List tags;
    StringBuilder sb;
    static final boolean $assertionsDisabled = true;

}
