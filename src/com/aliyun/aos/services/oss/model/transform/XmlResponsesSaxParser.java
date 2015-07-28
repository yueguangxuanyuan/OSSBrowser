// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlResponsesSaxParser.java

package com.aliyun.aos.services.oss.model.transform;

import com.aliyun.aos.AOSClientException;
import com.aliyun.aos.AOSServiceException;
import com.aliyun.aos.services.oss.internal.*;
import com.aliyun.aos.services.oss.model.*;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlResponsesSaxParser
{
    public class CannedAccessControlListHandler extends DefaultHandler
    {

        public GetBucketAclResult getGetBucketAclResult()
        {
            return getBucketAclResult;
        }

        public void startDocument()
        {
        }

        public void endDocument()
        {
        }

        public void startElement(String uri, String name, String qName, Attributes attrs)
        {
            if(name.equals("Owner"))
                owner = new Owner();
            else
                currText = new StringBuilder();
        }

        public void endElement(String uri, String name, String qName)
        {
            String elementText = currText.toString().trim();
            if(name.equals("ID"))
                owner.setId(elementText);
            else
            if(name.equals("DisplayName"))
                owner.setDisplayName(elementText);
            else
            if(name.equals("Grant"))
            {
                accessControlList = CannedAccessControlList.valueOfAccessControlList(elementText);
                getBucketAclResult = new GetBucketAclResult();
                getBucketAclResult.setCannedAccessControlList(accessControlList);
            }
        }

        public void characters(char ch[], int start, int length)
        {
            currText.append(ch, start, length);
        }

        private CannedAccessControlList accessControlList;
        private GetBucketAclResult getBucketAclResult;
        private Owner owner;
        private StringBuilder currText;
        final XmlResponsesSaxParser this$0;

        public CannedAccessControlListHandler()
        {
            this$0 = XmlResponsesSaxParser.this;
            super();
            accessControlList = null;
            owner = null;
            currText = null;
            currText = new StringBuilder();
        }
    }

    public class CompleteMultipartUploadHandler extends DefaultHandler
    {

        public CompleteMultipartUploadResult getCompleteMultipartUploadResult()
        {
            return result;
        }

        public AOSOSSException getAOSOSSException()
        {
            return aoe;
        }

        public void startDocument()
        {
            text = new StringBuilder();
        }

        public void endDocument()
        {
            if(aoe != null)
                throw aoe;
            else
                return;
        }

        public void startElement(String uri, String name, String qName, Attributes attrs)
        {
            if(name.equals("CompleteMultipartUploadResult"))
                result = new CompleteMultipartUploadResult();
            else
            if(!name.equals("Location") && !name.equals("Bucket") && !name.equals("Key"))
                name.equals("ETag");
            if(!name.equals("Error") && !name.equals("Code") && !name.equals("Message"))
                name.equals("RequestId");
            text.setLength(0);
        }

        public void endElement(String uri, String name, String qName)
            throws SAXException
        {
            if(result != null)
            {
                String val = text.toString().trim();
                if(!name.equals("CompleteMultipartUploadResult"))
                    if(name.equals("Location"))
                        result.setLocation(val);
                    else
                    if(name.equals("Bucket"))
                        result.setBucketName(val);
                    else
                    if(name.equals("Key"))
                        result.setKey(val);
                    else
                    if(name.equals("ETag"))
                        result.setETag(ServiceUtils.removeQuotes(val));
            } else
            if(name.equals("Error"))
            {
                aoe.setErrorCode(errorCode);
                aoe.setRequestId(requestId);
            } else
            if(name.equals("Code"))
                errorCode = text.toString().trim();
            else
            if(name.equals("Message"))
                aoe = new AOSOSSException(text.toString());
            else
            if(name.equals("RequestId"))
                requestId = text.toString().trim();
        }

        public void characters(char ch[], int start, int length)
        {
            text.append(ch, start, length);
        }

        private StringBuilder text;
        private CompleteMultipartUploadResult result;
        private AOSOSSException aoe;
        private String requestId;
        private String errorCode;
        final XmlResponsesSaxParser this$0;

        public CompleteMultipartUploadHandler()
        {
            this$0 = XmlResponsesSaxParser.this;
            super();
        }
    }

    public class CopyObjectResultHandler extends DefaultHandler
    {

        public void startDocument()
        {
        }

        public CopyObjectResult getCopyObjectResult()
        {
            return copyObjectResult;
        }

        public void endDocument()
        {
            if(receivedErrorResponse)
            {
                exception = new AOSServiceException("");
                exception.setErrorCode(errorCode);
                exception.setErrorMessage(errorMessage);
                exception.setRequestId(errorRequestId);
                throw exception;
            } else
            {
                copyObjectResult = new CopyObjectResult();
                copyObjectResult.setLastModifiedDate(lastModified);
                copyObjectResult.setETag(etag);
                return;
            }
        }

        public void startElement(String uri, String name, String qName, Attributes attrs)
        {
            if(name.equals("CopyObjectResult"))
                receivedErrorResponse = false;
            else
            if(name.equals("Error"))
                receivedErrorResponse = true;
        }

        public void endElement(String uri, String name, String qName)
        {
            String elementText = currText.toString().trim();
            if(name.equals("LastModified"))
                try
                {
                    lastModified = ServiceUtils.parseIso8601Date(elementText);
                }
                catch(ParseException e)
                {
                    throw new RuntimeException((new StringBuilder("Non-ISO8601 date for LastModified in copy object output: ")).append(elementText).toString(), e);
                }
            else
            if(name.equals("ETag"))
                etag = ServiceUtils.removeQuotes(elementText);
            else
            if(name.equals("Code"))
                errorCode = elementText;
            else
            if(name.equals("Message"))
                errorMessage = elementText;
            else
            if(name.equals("RequestId"))
                errorRequestId = elementText;
            currText = new StringBuilder();
        }

        public void characters(char ch[], int start, int length)
        {
            currText.append(ch, start, length);
        }

        private CopyObjectResult copyObjectResult;
        private String etag;
        private Date lastModified;
        AOSServiceException exception;
        private String errorCode;
        private String errorMessage;
        private String errorRequestId;
        private boolean receivedErrorResponse;
        private StringBuilder currText;
        final XmlResponsesSaxParser this$0;

        public CopyObjectResultHandler()
        {
            this$0 = XmlResponsesSaxParser.this;
            super();
            copyObjectResult = null;
            etag = null;
            lastModified = null;
            exception = null;
            errorCode = null;
            errorMessage = null;
            errorRequestId = null;
            receivedErrorResponse = false;
            currText = null;
            currText = new StringBuilder();
        }
    }

    public class DeleteObjectsHandler extends DefaultHandler
    {

        public DeleteObjectsResponse getDeleteObjectResult()
        {
            return new DeleteObjectsResponse(deletedObjects, deleteErrors);
        }

        public void startDocument()
        {
            text = new StringBuilder();
        }

        public void startElement(String uri, String name, String qName, Attributes attrs)
        {
            if(name.equals("Deleted"))
                deletedObject = "";
            else
            if(name.equals("Error"))
                error = new com.aliyun.aos.services.oss.model.MultiObjectDeleteException.DeleteError();
            else
            if(!name.equals("Key") && !name.equals("Code") && !name.equals("Message") && !name.equals("DeletedResult"))
                XmlResponsesSaxParser.log.warn((new StringBuilder("Unexpected tag: ")).append(name).toString());
            text.setLength(0);
        }

        public void endElement(String uri, String name, String qName)
            throws SAXException
        {
            if(name.equals("Deleted"))
            {
                deletedObjects.add(deletedObject);
                deletedObject = null;
            } else
            if(name.equals("Error"))
            {
                deleteErrors.add(error);
                error = null;
            } else
            if(name.equals("Key"))
            {
                if(deletedObject != null)
                    deletedObject = text.toString();
                else
                if(error != null)
                    error.setKey(text.toString());
            } else
            if(name.equals("Code"))
            {
                if(error != null)
                    error.setCode(text.toString());
            } else
            if(name.equals("Message") && error != null)
                error.setMessage(text.toString());
        }

        public void characters(char ch[], int start, int length)
        {
            text.append(ch, start, length);
        }

        private StringBuilder text;
        private String deletedObject;
        private com.aliyun.aos.services.oss.model.MultiObjectDeleteException.DeleteError error;
        private List deletedObjects;
        private List deleteErrors;
        final XmlResponsesSaxParser this$0;

        public DeleteObjectsHandler()
        {
            this$0 = XmlResponsesSaxParser.this;
            super();
            deletedObject = null;
            error = null;
            deletedObjects = new LinkedList();
            deleteErrors = new LinkedList();
        }
    }

    public class InitiateMultipartUploadHandler extends DefaultHandler
    {

        public InitiateMultipartUploadResult getInitiateMultipartUploadResult()
        {
            return result;
        }

        public void startDocument()
        {
            text = new StringBuilder();
        }

        public void startElement(String uri, String name, String qName, Attributes attrs)
        {
            if(name.equals("InitiateMultipartUploadResult"))
                result = new InitiateMultipartUploadResult();
            else
            if(!name.equals("Bucket") && !name.equals("Key"))
                name.equals("UploadId");
            text.setLength(0);
        }

        public void endElement(String uri, String name, String qName)
            throws SAXException
        {
            String val = text.toString().trim();
            if(!name.equals("InitiateMultipartUploadResult"))
                if(name.equals("Bucket"))
                    result.setBucketName(val);
                else
                if(name.equals("Key"))
                    result.setKey(val);
                else
                if(name.equals("UploadId"))
                    result.setUploadId(val);
        }

        public void characters(char ch[], int start, int length)
        {
            text.append(ch, start, length);
        }

        private StringBuilder text;
        private InitiateMultipartUploadResult result;
        final XmlResponsesSaxParser this$0;

        public InitiateMultipartUploadHandler()
        {
            this$0 = XmlResponsesSaxParser.this;
            super();
        }
    }

    public class ListAllMyBucketsHandler extends DefaultHandler
    {

        public ListBucketResult getListBucketResult()
        {
            return result;
        }

        public Owner getOwner()
        {
            return bucketsOwner;
        }

        public void startDocument()
        {
        }

        public void endDocument()
        {
            result.setBuckets(buckets);
        }

        public void startElement(String uri, String name, String qName, Attributes attrs)
        {
            if(name.equals("Bucket"))
                currentBucket = new Bucket();
            else
            if(name.equals("Owner"))
                bucketsOwner = new Owner();
        }

        public void endElement(String uri, String name, String qName)
        {
            String elementText = currText.toString().trim();
            if(name.equals("ID"))
                bucketsOwner.setId(elementText);
            else
            if(name.equals("DisplayName"))
                bucketsOwner.setDisplayName(elementText);
            else
            if(name.equals("Bucket"))
            {
                currentBucket.setOwner(bucketsOwner);
                buckets.add(currentBucket);
            } else
            if(name.equals("Name"))
                currentBucket.setName(elementText);
            else
            if(name.equals("CreationDate"))
                try
                {
                    currentBucket.setCreationDate(ServiceUtils.parseIso8601Date(elementText));
                }
                catch(ParseException e)
                {
                    throw new RuntimeException((new StringBuilder("Non-ISO8601 date for CreationDate in list buckets output: ")).append(elementText).toString(), e);
                }
            currText = new StringBuilder();
        }

        public void characters(char ch[], int start, int length)
        {
            currText.append(ch, start, length);
        }

        private Owner bucketsOwner;
        private Bucket currentBucket;
        private StringBuilder currText;
        private List buckets;
        private ListBucketResult result;
        final XmlResponsesSaxParser this$0;

        public ListAllMyBucketsHandler()
        {
            this$0 = XmlResponsesSaxParser.this;
            super();
            bucketsOwner = null;
            currentBucket = null;
            currText = null;
            buckets = null;
            result = null;
            buckets = new ArrayList();
            currText = new StringBuilder();
            result = new ListBucketResult();
        }
    }

    public class ListBucketHandler extends DefaultHandler
    {

        public ObjectListing getObjectListing()
        {
            objectListing.setBucketName(bucketName);
            objectListing.setCommonPrefixes(commonPrefixes);
            objectListing.setDelimiter(requestDelimiter);
            objectListing.setMarker(requestMarker);
            objectListing.setMaxKeys(requestMaxKeys);
            objectListing.setPrefix(requestPrefix);
            objectListing.setTruncated(listingTruncated);
            if(this.nextMarker != null)
                objectListing.setNextMarker(this.nextMarker);
            else
            if(listingTruncated)
            {
                String nextMarker = null;
                if(!objectListing.getObjectSummaries().isEmpty())
                    nextMarker = ((OSSObjectSummary)objectListing.getObjectSummaries().get(objectListing.getObjectSummaries().size() - 1)).getKey();
                else
                if(!objectListing.getCommonPrefixes().isEmpty())
                    nextMarker = (String)objectListing.getCommonPrefixes().get(objectListing.getCommonPrefixes().size() - 1);
                else
                    XmlResponsesSaxParser.log.error("S3 response indicates truncated results, but contains no object summaries or common prefixes.");
                objectListing.setNextMarker(nextMarker);
            }
            return objectListing;
        }

        public String getMarkerForNextListing()
        {
            if(listingTruncated)
            {
                if(nextMarker != null)
                    return nextMarker;
                if(lastKey != null)
                    return lastKey;
                if(XmlResponsesSaxParser.log.isWarnEnabled())
                    XmlResponsesSaxParser.log.warn("Unable to find Next Marker or Last Key for truncated listing");
                return null;
            } else
            {
                return null;
            }
        }

        public boolean isListingTruncated()
        {
            return listingTruncated;
        }

        public List getObjects()
        {
            return objectListing.getObjectSummaries();
        }

        public String[] getCommonPrefixes()
        {
            return (String[])commonPrefixes.toArray(new String[commonPrefixes.size()]);
        }

        public String getRequestPrefix()
        {
            return requestPrefix;
        }

        public String getRequestMarker()
        {
            return requestMarker;
        }

        public String getNextMarker()
        {
            return nextMarker;
        }

        public long getRequestMaxKeys()
        {
            return (long)requestMaxKeys;
        }

        public void startDocument()
        {
        }

        public void endDocument()
        {
        }

        public void startElement(String uri, String name, String qName, Attributes attrs)
        {
            if(name.equals("Contents"))
            {
                currentObject = new OSSObjectSummary();
                currentObject.setBucketName(bucketName);
            } else
            if(name.equals("Owner"))
            {
                currentOwner = new Owner();
                currentObject.setOwner(currentOwner);
            } else
            if(name.equals("CommonPrefixes"))
                insideCommonPrefixes = true;
        }

        public void endElement(String uri, String name, String qName)
        {
            String elementText = currText.toString().trim();
            if(name.equals("Name"))
            {
                bucketName = elementText;
                if(XmlResponsesSaxParser.log.isDebugEnabled())
                    XmlResponsesSaxParser.log.debug((new StringBuilder("Examining listing for bucket: ")).append(bucketName).toString());
            } else
            if(!insideCommonPrefixes && name.equals("Prefix"))
                requestPrefix = checkForEmptyString(elementText);
            else
            if(name.equals("Marker"))
                requestMarker = checkForEmptyString(elementText);
            else
            if(name.equals("NextMarker"))
                nextMarker = elementText;
            else
            if(name.equals("MaxKeys"))
                requestMaxKeys = parseInt(elementText);
            else
            if(name.equals("Delimiter"))
                requestDelimiter = checkForEmptyString(elementText);
            else
            if(name.equals("IsTruncated"))
            {
                String isTruncatedStr = elementText.toLowerCase(Locale.getDefault());
                if(isTruncatedStr.startsWith("false"))
                    listingTruncated = false;
                else
                if(isTruncatedStr.startsWith("true"))
                    listingTruncated = true;
                else
                    throw new RuntimeException((new StringBuilder("Invalid value for IsTruncated field: ")).append(isTruncatedStr).toString());
            } else
            if(name.equals("Contents"))
                objectListing.getObjectSummaries().add(currentObject);
            else
            if(name.equals("Key"))
            {
                currentObject.setKey(elementText);
                lastKey = elementText;
            } else
            if(name.equals("LastModified"))
                try
                {
                    currentObject.setLastModified(ServiceUtils.parseIso8601Date(elementText));
                }
                catch(ParseException e)
                {
                    throw new RuntimeException((new StringBuilder("Non-ISO8601 date for LastModified in bucket's object listing output: ")).append(elementText).toString(), e);
                }
            else
            if(name.equals("ETag"))
                currentObject.setETag(ServiceUtils.removeQuotes(elementText));
            else
            if(name.equals("Size"))
                currentObject.setSize(parseLong(elementText));
            else
            if(name.equals("StorageClass"))
                currentObject.setStorageClass(elementText);
            else
            if(name.equals("Type"))
                currentObject.setType(elementText);
            else
            if(name.equals("ID"))
            {
                if(currentOwner == null)
                {
                    currentOwner = new Owner();
                    currentObject.setOwner(currentOwner);
                }
                currentOwner.setId(elementText);
            } else
            if(name.equals("DisplayName"))
                currentOwner.setDisplayName(elementText);
            else
            if(insideCommonPrefixes && name.equals("Prefix"))
                commonPrefixes.add(elementText);
            else
            if(name.equals("CommonPrefixes"))
                insideCommonPrefixes = false;
            currText = new StringBuilder();
        }

        public void characters(char ch[], int start, int length)
        {
            currText.append(ch, start, length);
        }

        private OSSObjectSummary currentObject;
        private Owner currentOwner;
        private StringBuilder currText;
        private boolean insideCommonPrefixes;
        private ObjectListing objectListing;
        private List commonPrefixes;
        private String bucketName;
        private String requestPrefix;
        private String requestMarker;
        private int requestMaxKeys;
        private String requestDelimiter;
        private boolean listingTruncated;
        private String lastKey;
        private String nextMarker;
        final XmlResponsesSaxParser this$0;

        public ListBucketHandler()
        {
            this$0 = XmlResponsesSaxParser.this;
            super();
            currentObject = null;
            currentOwner = null;
            currText = null;
            insideCommonPrefixes = false;
            objectListing = new ObjectListing();
            commonPrefixes = new ArrayList();
            bucketName = null;
            requestPrefix = null;
            requestMarker = null;
            requestMaxKeys = 0;
            requestDelimiter = null;
            listingTruncated = false;
            lastKey = null;
            nextMarker = null;
            currText = new StringBuilder();
        }
    }

    public class ListFileGroupHandler extends DefaultHandler
    {

        public ListFileGroupResult getListFileGroupResult()
        {
            return listFileGroupResult;
        }

        public void startElement(String uri, String name, String qName, Attributes attrs)
        {
            if(name.equals("FileGroup"))
                listFileGroupResult = new ListFileGroupResult();
            else
            if(name.equals("FilePart"))
            {
                List parts = new ArrayList();
                insideFilePart = true;
                listFileGroupResult.setParts(parts);
            } else
            if(name.equals("Part"))
                curFilePart = new FilePartSummary();
        }

        public void endElement(String uri, String name, String qName)
        {
            String elementText = currText.toString().trim();
            if(name.equals("Bucket"))
                listFileGroupResult.setBucketName(elementText);
            else
            if(name.equals("Key"))
                listFileGroupResult.setKey(elementText);
            else
            if(name.equals("FileLength"))
                listFileGroupResult.setSize(Long.valueOf(elementText).longValue());
            else
            if(name.equals("ETag"))
            {
                if(!insideFilePart)
                    listFileGroupResult.setETag(ServiceUtils.removeQuotes(elementText));
                else
                    curFilePart.setETag(ServiceUtils.removeQuotes(elementText));
            } else
            if(name.equals("Part"))
                listFileGroupResult.getParts().add(curFilePart);
            else
            if(name.equals("PartNumber"))
                curFilePart.setPartNumber(Integer.valueOf(elementText).intValue());
            else
            if(name.equals("PartName"))
                curFilePart.setPartName(elementText);
            else
            if(name.equals("PartSize"))
                curFilePart.setPartSize(Long.valueOf(elementText).longValue());
            currText = new StringBuilder();
        }

        public void characters(char ch[], int start, int length)
        {
            currText.append(ch, start, length);
        }

        private ListFileGroupResult listFileGroupResult;
        private StringBuilder currText;
        private FilePartSummary curFilePart;
        boolean insideFilePart;
        final XmlResponsesSaxParser this$0;

        public ListFileGroupHandler()
        {
            this$0 = XmlResponsesSaxParser.this;
            super();
            listFileGroupResult = null;
            currText = new StringBuilder();
            curFilePart = null;
            insideFilePart = false;
        }
    }

    public class ListMultipartUploadsHandler extends DefaultHandler
    {

        public MultipartUploadListing getListMultipartUploadsResult()
        {
            return result;
        }

        public void startDocument()
        {
            text = new StringBuilder();
        }

        public void startElement(String uri, String name, String qName, Attributes attrs)
        {
            if(name.equals("ListMultipartUploadsResult"))
                result = new MultipartUploadListing();
            else
            if(!name.equals("Bucket") && !name.equals("KeyMarker") && !name.equals("Delimiter") && !name.equals("UploadIdMarker") && !name.equals("NextKeyMarker") && !name.equals("NextUploadIdMarker") && !name.equals("MaxUploads") && !name.equals("IsTruncated"))
                if(name.equals("Upload"))
                    currentMultipartUpload = new MultipartUpload();
                else
                if(!name.equals("Key") && !name.equals("UploadId"))
                    if(name.equals("Owner"))
                        currentOwner = new Owner();
                    else
                    if(name.equals("Initiator"))
                        currentInitiator = new Owner();
                    else
                    if(!name.equals("ID") && !name.equals("DisplayName") && !name.equals("StorageClass") && !name.equals("Initiated") && name.equals("CommonPrefixes"))
                        inCommonPrefixes = true;
            text.setLength(0);
        }

        public void endElement(String uri, String name, String qName)
            throws SAXException
        {
            String val = text.toString().trim();
            if(!name.equals("ListMultipartUploadsResult"))
                if(name.equals("Bucket"))
                    result.setBucketName(val);
                else
                if(name.equals("KeyMarker"))
                    result.setKeyMarker(checkForEmptyString(val));
                else
                if(name.equals("Delimiter"))
                    result.setDelimiter(checkForEmptyString(val));
                else
                if(name.equals("Prefix") && !inCommonPrefixes)
                    result.setPrefix(checkForEmptyString(val));
                else
                if(name.equals("Prefix") && inCommonPrefixes)
                    result.getCommonPrefixes().add(val);
                else
                if(name.equals("UploadIdMarker"))
                    result.setUploadIdMarker(checkForEmptyString(val));
                else
                if(name.equals("NextKeyMarker"))
                    result.setNextKeyMarker(checkForEmptyString(val));
                else
                if(name.equals("NextUploadIdMarker"))
                    result.setNextUploadIdMarker(checkForEmptyString(val));
                else
                if(name.equals("MaxUploads"))
                    result.setMaxUploads(Integer.parseInt(val));
                else
                if(name.equals("IsTruncated"))
                    result.setTruncated(Boolean.parseBoolean(val));
                else
                if(name.equals("Upload"))
                    result.getMultipartUploads().add(currentMultipartUpload);
                else
                if(name.equals("Key"))
                    currentMultipartUpload.setKey(val);
                else
                if(name.equals("UploadId"))
                    currentMultipartUpload.setUploadId(val);
                else
                if(name.equals("Owner"))
                {
                    currentMultipartUpload.setOwner(currentOwner);
                    currentOwner = null;
                } else
                if(name.equals("Initiator"))
                {
                    currentMultipartUpload.setInitiator(currentInitiator);
                    currentInitiator = null;
                } else
                if(name.equals("ID") && currentOwner != null)
                    currentOwner.setId(checkForEmptyString(val));
                else
                if(name.equals("DisplayName") && currentOwner != null)
                    currentOwner.setDisplayName(checkForEmptyString(val));
                else
                if(name.equals("ID") && currentInitiator != null)
                    currentInitiator.setId(checkForEmptyString(val));
                else
                if(name.equals("DisplayName") && currentInitiator != null)
                    currentInitiator.setDisplayName(checkForEmptyString(val));
                else
                if(name.equals("StorageClass"))
                    currentMultipartUpload.setStorageClass(val);
                else
                if(name.equals("Initiated"))
                    try
                    {
                        currentMultipartUpload.setInitiated(ServiceUtils.parseIso8601Date(val));
                    }
                    catch(ParseException e)
                    {
                        throw new SAXException((new StringBuilder("Non-ISO8601 date for Initiated in initiate multipart upload result: ")).append(val).toString(), e);
                    }
                else
                if(name.equals("CommonPrefixes"))
                    inCommonPrefixes = false;
        }

        public void characters(char ch[], int start, int length)
        {
            text.append(ch, start, length);
        }

        private StringBuilder text;
        private MultipartUploadListing result;
        private MultipartUpload currentMultipartUpload;
        private Owner currentOwner;
        private Owner currentInitiator;
        boolean inCommonPrefixes;
        final XmlResponsesSaxParser this$0;

        public ListMultipartUploadsHandler()
        {
            this$0 = XmlResponsesSaxParser.this;
            super();
            inCommonPrefixes = false;
        }
    }

    public class ListPartsHandler extends DefaultHandler
    {

        public PartListing getListPartsResult()
        {
            return result;
        }

        public void startDocument()
        {
            text = new StringBuilder();
        }

        public void startElement(String uri, String name, String qName, Attributes attrs)
        {
            if(name.equals("ListPartsResult"))
                result = new PartListing();
            else
            if(!name.equals("Bucket") && !name.equals("Key") && !name.equals("UploadId"))
                if(name.equals("Owner"))
                    currentOwner = new Owner();
                else
                if(name.equals("Initiator"))
                    currentInitiator = new Owner();
                else
                if(!name.equals("ID") && !name.equals("DisplayName") && !name.equals("StorageClass") && !name.equals("PartNumberMarker") && !name.equals("NextPartNumberMarker") && !name.equals("MaxParts") && !name.equals("IsTruncated"))
                    if(name.equals("Part"))
                        currentPart = new PartSummary();
                    else
                    if(!name.equals("PartNumber") && !name.equals("LastModified") && !name.equals("ETag"))
                        name.equals("Size");
            text.setLength(0);
        }

        private Integer parseInteger(String text)
        {
            text = checkForEmptyString(text.toString());
            if(text == null)
                return null;
            else
                return Integer.valueOf(Integer.parseInt(text));
        }

        public void endElement(String uri, String name, String qName)
            throws SAXException
        {
            String val = text.toString().trim();
            if(!name.equals("ListPartsResult"))
                if(name.equals("Bucket"))
                    result.setBucketName(val);
                else
                if(name.equals("Key"))
                    result.setKey(val);
                else
                if(name.equals("UploadId"))
                    result.setUploadId(val);
                else
                if(name.equals("Owner"))
                {
                    result.setOwner(currentOwner);
                    currentOwner = null;
                } else
                if(name.equals("Initiator"))
                {
                    result.setInitiator(currentInitiator);
                    currentInitiator = null;
                } else
                if(name.equals("ID") && currentOwner != null)
                    currentOwner.setId(checkForEmptyString(val));
                else
                if(name.equals("DisplayName") && currentOwner != null)
                    currentOwner.setDisplayName(checkForEmptyString(val));
                else
                if(name.equals("ID") && currentInitiator != null)
                    currentInitiator.setId(checkForEmptyString(val));
                else
                if(name.equals("DisplayName") && currentInitiator != null)
                    currentInitiator.setDisplayName(checkForEmptyString(val));
                else
                if(name.equals("StorageClass"))
                    result.setStorageClass(val);
                else
                if(name.equals("PartNumberMarker"))
                    result.setPartNumberMarker(parseInteger(val).intValue());
                else
                if(name.equals("NextPartNumberMarker"))
                    result.setNextPartNumberMarker(parseInteger(val).intValue());
                else
                if(name.equals("MaxParts"))
                    result.setMaxParts(parseInteger(val).intValue());
                else
                if(name.equals("IsTruncated"))
                    result.setTruncated(Boolean.parseBoolean(val));
                else
                if(name.equals("Part"))
                    result.getParts().add(currentPart);
                else
                if(name.equals("PartNumber"))
                    currentPart.setPartNumber(Integer.parseInt(val));
                else
                if(name.equals("LastModified"))
                    try
                    {
                        currentPart.setLastModified(ServiceUtils.parseIso8601Date(val));
                    }
                    catch(ParseException e)
                    {
                        throw new SAXException((new StringBuilder("Non-ISO8601 date for LastModified in list parts result: ")).append(val).toString(), e);
                    }
                else
                if(name.equals("ETag"))
                    currentPart.setETag(ServiceUtils.removeQuotes(val));
                else
                if(name.equals("Size"))
                    currentPart.setSize(Long.parseLong(val));
        }

        public void characters(char ch[], int start, int length)
        {
            text.append(ch, start, length);
        }

        private StringBuilder text;
        private PartListing result;
        private Owner currentOwner;
        private Owner currentInitiator;
        private PartSummary currentPart;
        final XmlResponsesSaxParser this$0;

        public ListPartsHandler()
        {
            this$0 = XmlResponsesSaxParser.this;
            super();
        }
    }

    public class PostFileGroupHandler extends DefaultHandler
    {

        public PostFileGroupResult getPostFileGroupResult()
        {
            return postFileGroupResult;
        }

        public void startElement(String uri, String name, String qName, Attributes attrs)
        {
            if(name.equals("CompleteFileGroup"))
                postFileGroupResult = new PostFileGroupResult();
        }

        public void endElement(String uri, String name, String qName)
        {
            String elementText = currText.toString().trim();
            if(name.equals("Bucket"))
                postFileGroupResult.setBucket(elementText);
            else
            if(name.equals("Key"))
                postFileGroupResult.setKey(elementText);
            else
            if(name.equals("Size"))
                postFileGroupResult.setSize(Long.valueOf(elementText).longValue());
            else
            if(name.equals("ETag"))
                postFileGroupResult.seteTag(ServiceUtils.removeQuotes(elementText));
            currText = new StringBuilder();
        }

        public void characters(char ch[], int start, int length)
        {
            currText.append(ch, start, length);
        }

        private PostFileGroupResult postFileGroupResult;
        private StringBuilder currText;
        final XmlResponsesSaxParser this$0;

        public PostFileGroupHandler()
        {
            this$0 = XmlResponsesSaxParser.this;
            super();
            postFileGroupResult = null;
            currText = new StringBuilder();
        }
    }


    public XmlResponsesSaxParser()
        throws AOSClientException
    {
        xr = null;
        sanitizeXmlDocument = true;
        try
        {
            xr = XMLReaderFactory.createXMLReader();
        }
        catch(SAXException e)
        {
            System.setProperty("org.xml.sax.driver", "org.apache.crimson.parser.XMLReaderImpl");
            try
            {
                xr = XMLReaderFactory.createXMLReader();
            }
            catch(SAXException e2)
            {
                throw new AOSClientException("Couldn't initialize a sax driver for the XMLReader");
            }
        }
    }

    private int parseInt(String s)
    {
        try
        {
            return Integer.parseInt(s);
        }
        catch(NumberFormatException nfe)
        {
            log.error((new StringBuilder("Unable to parse integer value '")).append(s).append("'").toString(), nfe);
        }
        return -1;
    }

    private String checkForEmptyString(String s)
    {
        if(s == null)
            return null;
        if(s.length() == 0)
            return null;
        else
            return s;
    }

    private long parseLong(String s)
    {
        try
        {
            return Long.parseLong(s);
        }
        catch(NumberFormatException nfe)
        {
            log.error((new StringBuilder("Unable to parse long value '")).append(s).append("'").toString(), nfe);
        }
        return -1L;
    }

    public CopyObjectResultHandler parseCopyObjectResponse(InputStream inputStream)
        throws AOSClientException
    {
        CopyObjectResultHandler handler = new CopyObjectResultHandler();
        parseXmlInputStream(handler, inputStream);
        return handler;
    }

    public ListAllMyBucketsHandler parseListMyBucketsResponse(InputStream inputStream)
        throws AOSClientException
    {
        ListAllMyBucketsHandler handler = new ListAllMyBucketsHandler();
        parseXmlInputStream(handler, sanitizeXmlDocument(handler, inputStream));
        return handler;
    }

    public ListBucketHandler parseListBucketObjectsResponse(InputStream inputSteam)
        throws AOSClientException
    {
        ListBucketHandler handler = new ListBucketHandler();
        parseXmlInputStream(handler, sanitizeXmlDocument(handler, inputSteam));
        return handler;
    }

    public DeleteObjectsHandler parseDeletedObjectsResult(InputStream inputStream)
    {
        DeleteObjectsHandler handler = new DeleteObjectsHandler();
        parseXmlInputStream(handler, inputStream);
        return handler;
    }

    public CannedAccessControlListHandler parseGetBucketAclResponse(InputStream inputStream)
        throws AOSClientException
    {
        CannedAccessControlListHandler handler = new CannedAccessControlListHandler();
        parseXmlInputStream(handler, sanitizeXmlDocument(handler, inputStream));
        return handler;
    }

    public PostFileGroupHandler parsePostFileGroupResult(InputStream inputStream)
        throws AOSClientException
    {
        PostFileGroupHandler handler = new PostFileGroupHandler();
        parseXmlInputStream(handler, sanitizeXmlDocument(handler, inputStream));
        return handler;
    }

    public ListFileGroupHandler parseListFileGroupResult(InputStream inputStream)
        throws AOSClientException
    {
        ListFileGroupHandler handler = new ListFileGroupHandler();
        parseXmlInputStream(handler, sanitizeXmlDocument(handler, inputStream));
        return handler;
    }

    public CompleteMultipartUploadHandler parseCompleteMultipartUploadResponse(InputStream inputStream)
        throws AOSClientException
    {
        CompleteMultipartUploadHandler handler = new CompleteMultipartUploadHandler();
        parseXmlInputStream(handler, inputStream);
        return handler;
    }

    protected void parseXmlInputStream(DefaultHandler handler, InputStream inputStream)
        throws AOSClientException
    {
        try
        {
            if(log.isDebugEnabled())
                log.debug((new StringBuilder("Parsing XML response document with handler: ")).append(handler.getClass()).toString());
            BufferedReader breader = new BufferedReader(new InputStreamReader(inputStream, Constants.DEFAULT_ENCODING));
            xr.setContentHandler(handler);
            xr.setErrorHandler(handler);
            xr.parse(new InputSource(breader));
        }
        catch(Throwable t)
        {
            try
            {
                inputStream.close();
            }
            catch(IOException e)
            {
                if(log.isErrorEnabled())
                    log.error("Unable to close response InputStream up after XML parse failure", e);
            }
            throw new AOSClientException((new StringBuilder("Failed to parse XML document with handler ")).append(handler.getClass()).toString(), t);
        }
    }

    protected InputStream sanitizeXmlDocument(DefaultHandler handler, InputStream inputStream)
        throws AOSClientException
    {
        if(!sanitizeXmlDocument)
            return inputStream;
        if(log.isDebugEnabled())
            log.debug((new StringBuilder("Sanitizing XML document destined for handler ")).append(handler.getClass()).toString());
        InputStream sanitizedInputStream = null;
        try
        {
            StringBuilder listingDocBuffer = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Constants.DEFAULT_ENCODING));
            char buf[] = new char[8192];
            for(int read = -1; (read = br.read(buf)) != -1;)
                listingDocBuffer.append(buf, 0, read);

            br.close();
            String listingDoc = listingDocBuffer.toString().replaceAll("\r", "&#013;");
            sanitizedInputStream = new ByteArrayInputStream(listingDoc.getBytes(Constants.DEFAULT_ENCODING));
        }
        catch(Throwable t)
        {
            try
            {
                inputStream.close();
            }
            catch(IOException e)
            {
                if(log.isErrorEnabled())
                    log.error("Unable to close response InputStream after failure sanitizing XML document", e);
            }
            throw new AOSClientException((new StringBuilder("Failed to sanitize XML document destined for handler ")).append(handler.getClass()).toString(), t);
        }
        return sanitizedInputStream;
    }

    public InitiateMultipartUploadHandler parseInitiateMultipartUploadResponse(InputStream inputStream)
        throws AOSClientException
    {
        InitiateMultipartUploadHandler handler = new InitiateMultipartUploadHandler();
        parseXmlInputStream(handler, inputStream);
        return handler;
    }

    public ListPartsHandler parseListPartsResponse(InputStream inputStream)
        throws AOSClientException
    {
        ListPartsHandler handler = new ListPartsHandler();
        parseXmlInputStream(handler, inputStream);
        return handler;
    }

    public ListMultipartUploadsHandler parseListMultipartUploadsResponse(InputStream inputStream)
        throws AOSClientException
    {
        ListMultipartUploadsHandler handler = new ListMultipartUploadsHandler();
        parseXmlInputStream(handler, inputStream);
        return handler;
    }

    private static final Log log = LogFactory.getLog(com/aliyun/aos/services/oss/model/transform/XmlResponsesSaxParser);
    private XMLReader xr;
    private boolean sanitizeXmlDocument;





}
