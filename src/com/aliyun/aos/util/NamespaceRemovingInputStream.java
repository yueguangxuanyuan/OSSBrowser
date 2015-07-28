// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NamespaceRemovingInputStream.java

package com.aliyun.aos.util;

import java.io.*;

class NamespaceRemovingInputStream extends FilterInputStream
{
    private static final class StringPrefixSlicer
    {

        public String getString()
        {
            return s;
        }

        public boolean removePrefix(String prefix)
        {
            if(!s.startsWith(prefix))
            {
                return false;
            } else
            {
                s = s.substring(prefix.length());
                return true;
            }
        }

        public boolean removeRepeatingPrefix(String prefix)
        {
            if(!s.startsWith(prefix))
                return false;
            for(; s.startsWith(prefix); s = s.substring(prefix.length()));
            return true;
        }

        public boolean removePrefixEndingWith(String marker)
        {
            int i = s.indexOf(marker);
            if(i < 0)
            {
                return false;
            } else
            {
                s = s.substring(i + marker.length());
                return true;
            }
        }

        private String s;

        public StringPrefixSlicer(String s)
        {
            this.s = s;
        }
    }


    public NamespaceRemovingInputStream(InputStream in)
    {
        super(new BufferedInputStream(in));
        lookAheadData = new byte[200];
        hasRemovedNamespace = false;
    }

    public int read()
        throws IOException
    {
        int b = in.read();
        if(b == 120 && !hasRemovedNamespace)
        {
            lookAheadData[0] = (byte)b;
            in.mark(lookAheadData.length);
            int bytesRead = in.read(lookAheadData, 1, lookAheadData.length - 1);
            in.reset();
            String string = new String(lookAheadData, 0, bytesRead + 1);
            int numberCharsMatched = matchXmlNamespaceAttribute(string);
            if(numberCharsMatched > 0)
            {
                for(int i = 0; i < numberCharsMatched - 1; i++)
                    in.read();

                b = in.read();
                hasRemovedNamespace = true;
            }
        }
        return b;
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        for(int i = 0; i < len; i++)
        {
            int j = read();
            if(j == -1)
                if(i == 0)
                    return -1;
                else
                    return i;
            b[i + off] = (byte)j;
        }

        return len;
    }

    public int read(byte b[])
        throws IOException
    {
        return read(b, 0, b.length);
    }

    private int matchXmlNamespaceAttribute(String s)
    {
        StringPrefixSlicer stringSlicer = new StringPrefixSlicer(s);
        if(!stringSlicer.removePrefix("xmlns"))
            return -1;
        stringSlicer.removeRepeatingPrefix(" ");
        if(!stringSlicer.removePrefix("="))
            return -1;
        stringSlicer.removeRepeatingPrefix(" ");
        if(!stringSlicer.removePrefix("\""))
            return -1;
        if(!stringSlicer.removePrefixEndingWith("\""))
            return -1;
        else
            return s.length() - stringSlicer.getString().length();
    }

    private byte lookAheadData[];
    private boolean hasRemovedNamespace;
}
