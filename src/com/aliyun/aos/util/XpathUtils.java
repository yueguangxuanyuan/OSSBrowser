// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XpathUtils.java

package com.aliyun.aos.util;

import com.aliyun.aos.AOSClientException;
import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.util.Date;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

// Referenced classes of package com.aliyun.aos.util:
//            DateUtils, NamespaceRemovingInputStream

public class XpathUtils
{

    public XpathUtils()
    {
    }

    public static Document documentFrom(InputStream is)
        throws SAXException, IOException, ParserConfigurationException
    {
        is = new NamespaceRemovingInputStream(is);
        Document doc = factory.newDocumentBuilder().parse(is);
        is.close();
        return doc;
    }

    public static Document documentFrom(String xml)
        throws SAXException, IOException, ParserConfigurationException
    {
        return documentFrom(((InputStream) (new ByteArrayInputStream(xml.getBytes()))));
    }

    public static Document documentFrom(URL url)
        throws SAXException, IOException, ParserConfigurationException
    {
        return documentFrom(url.openStream());
    }

    public static Double asDouble(String expression, Node node)
        throws XPathExpressionException
    {
        String doubleString = evaluateAsString(expression, node);
        return isEmptyString(doubleString) ? null : Double.valueOf(Double.parseDouble(doubleString));
    }

    public static String asString(String expression, Node node)
        throws XPathExpressionException
    {
        return evaluateAsString(expression, node);
    }

    public static Integer asInteger(String expression, Node node)
        throws XPathExpressionException
    {
        String intString = evaluateAsString(expression, node);
        return isEmptyString(intString) ? null : Integer.valueOf(Integer.parseInt(intString));
    }

    public static Boolean asBoolean(String expression, Node node)
        throws XPathExpressionException
    {
        String booleanString = evaluateAsString(expression, node);
        return isEmptyString(booleanString) ? null : Boolean.valueOf(Boolean.parseBoolean(booleanString));
    }

    public static Float asFloat(String expression, Node node)
        throws XPathExpressionException
    {
        String floatString = evaluateAsString(expression, node);
        return isEmptyString(floatString) ? null : Float.valueOf(floatString);
    }

    public static Long asLong(String expression, Node node)
        throws XPathExpressionException
    {
        String longString = evaluateAsString(expression, node);
        return isEmptyString(longString) ? null : Long.valueOf(Long.parseLong(longString));
    }

    public static Byte asByte(String expression, Node node)
        throws XPathExpressionException
    {
        String byteString = evaluateAsString(expression, node);
        return isEmptyString(byteString) ? null : Byte.valueOf(byteString);
    }

    public static Date asDate(String expression, Node node)
        throws XPathExpressionException
    {
        String dateString = evaluateAsString(expression, node);
        if(isEmptyString(dateString))
            return null;
        try
        {
            return dateUtils.parseIso8601Date(dateString);
        }
        catch(ParseException e)
        {
            log.error((new StringBuilder("Unable to parse date '")).append(dateString).append("':  ").append(e.getMessage()).toString(), e);
        }
        return null;
    }

    public static ByteBuffer asByteBuffer(String expression, Node node)
        throws XPathExpressionException
    {
        String base64EncodedString = evaluateAsString(expression, node);
        if(isEmptyString(base64EncodedString))
            return null;
        if(!isEmpty(node))
            try
            {
                byte base64EncodedBytes[] = base64EncodedString.getBytes("UTF-8");
                byte decodedBytes[] = Base64.decodeBase64(base64EncodedBytes);
                return ByteBuffer.wrap(decodedBytes);
            }
            catch(UnsupportedEncodingException e)
            {
                throw new AOSClientException("Unable to unmarshall XML data into a ByteBuffer", e);
            }
        else
            return null;
    }

    public static boolean isEmpty(Node node)
    {
        return node == null;
    }

    public static Node asNode(String nodeName, Node node)
        throws XPathExpressionException
    {
        if(node == null)
            return null;
        else
            return (Node)xpath.evaluate(nodeName, node, XPathConstants.NODE);
    }

    public static int nodeLength(NodeList list)
    {
        return list != null ? list.getLength() : 0;
    }

    private static String evaluateAsString(String expression, Node node)
        throws XPathExpressionException
    {
        if(isEmpty(node))
            return null;
        if(expression != "." && asNode(expression, node) == null)
        {
            return null;
        } else
        {
            String s = xpath.evaluate(expression, node);
            return s.trim();
        }
    }

    private static boolean isEmptyString(String s)
    {
        if(s == null)
            return true;
        return s.trim().equals("");
    }

    private static XPathFactory xpathFactory;
    private static XPath xpath;
    private static DateUtils dateUtils = new DateUtils();
    private static Log log = LogFactory.getLog(com/aliyun/aos/util/XpathUtils);
    private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    static 
    {
        xpathFactory = XPathFactory.newInstance();
        xpath = xpathFactory.newXPath();
    }
}
