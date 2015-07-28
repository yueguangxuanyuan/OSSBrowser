// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OSSClientImpl.java

package com.aliyun.aos.services.oss;

import com.aliyun.aos.*;
import com.aliyun.aos.auth.*;
import com.aliyun.aos.handlers.HandlerChainFactory;
import com.aliyun.aos.handlers.RequestHandler;
import com.aliyun.aos.http.*;
import com.aliyun.aos.services.oss.internal.BucketNameUtils;
import com.aliyun.aos.services.oss.internal.Constants;
import com.aliyun.aos.services.oss.internal.DeleteObjectsResponse;
import com.aliyun.aos.services.oss.internal.InputSubstream;
import com.aliyun.aos.services.oss.internal.ListFileGroupRequest;
import com.aliyun.aos.services.oss.internal.ListFileGroupResult;
import com.aliyun.aos.services.oss.internal.MD5DigestCalculatingInputStream;
import com.aliyun.aos.services.oss.internal.Mimetypes;
import com.aliyun.aos.services.oss.internal.OSSErrorResponseHandler;
import com.aliyun.aos.services.oss.internal.OSSMetadataResponseHandler;
import com.aliyun.aos.services.oss.internal.OSSObjectResponseHandler;
import com.aliyun.aos.services.oss.internal.OSSQueryQuotaHandler;
import com.aliyun.aos.services.oss.internal.OSSXmlResponseHandler;
import com.aliyun.aos.services.oss.internal.ProgressReportingInputStream;
import com.aliyun.aos.services.oss.internal.QueryQuotaRequest;
import com.aliyun.aos.services.oss.internal.RepeatableFileInputStream;
import com.aliyun.aos.services.oss.internal.RepeatableInputStream;
import com.aliyun.aos.services.oss.internal.RequestQuota;
import com.aliyun.aos.services.oss.internal.ServiceUtils;
import com.aliyun.aos.services.oss.internal.StaticCredentialsProvider;
import com.aliyun.aos.services.oss.model.AOSOSSException;
import com.aliyun.aos.services.oss.model.AbortMultipartUploadRequest;
import com.aliyun.aos.services.oss.model.Bucket;
import com.aliyun.aos.services.oss.model.CannedAccessControlList;
import com.aliyun.aos.services.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.aos.services.oss.model.CompleteMultipartUploadResult;
import com.aliyun.aos.services.oss.model.CopyObjectRequest;
import com.aliyun.aos.services.oss.model.CopyObjectResult;
import com.aliyun.aos.services.oss.model.CreateBucketRequest;
import com.aliyun.aos.services.oss.model.DeleteBucketRequest;
import com.aliyun.aos.services.oss.model.DeleteObjectRequest;
import com.aliyun.aos.services.oss.model.DeleteObjectsRequest;
import com.aliyun.aos.services.oss.model.DeleteObjectsResult;
import com.aliyun.aos.services.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.aos.services.oss.model.GenericBucketRequest;
import com.aliyun.aos.services.oss.model.GetBucketAclResult;
import com.aliyun.aos.services.oss.model.GetObjectMetadataRequest;
import com.aliyun.aos.services.oss.model.GetObjectRequest;
import com.aliyun.aos.services.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.aos.services.oss.model.InitiateMultipartUploadResult;
import com.aliyun.aos.services.oss.model.ListBucketResult;
import com.aliyun.aos.services.oss.model.ListBucketsRequest;
import com.aliyun.aos.services.oss.model.ListMultipartUploadsRequest;
import com.aliyun.aos.services.oss.model.ListObjectsRequest;
import com.aliyun.aos.services.oss.model.ListPartsRequest;
import com.aliyun.aos.services.oss.model.MultiObjectDeleteException;
import com.aliyun.aos.services.oss.model.MultipartUploadListing;
import com.aliyun.aos.services.oss.model.OSSException;
import com.aliyun.aos.services.oss.model.OSSObject;
import com.aliyun.aos.services.oss.model.ObjectListing;
import com.aliyun.aos.services.oss.model.ObjectMetadata;
import com.aliyun.aos.services.oss.model.Owner;
import com.aliyun.aos.services.oss.model.PartListing;
import com.aliyun.aos.services.oss.model.PostFileGroupRequest;
import com.aliyun.aos.services.oss.model.PostFileGroupResult;
import com.aliyun.aos.services.oss.model.ProgressEvent;
import com.aliyun.aos.services.oss.model.ProgressListener;
import com.aliyun.aos.services.oss.model.PutObjectRequest;
import com.aliyun.aos.services.oss.model.PutObjectResult;
import com.aliyun.aos.services.oss.model.ResponseHeaderOverrides;
import com.aliyun.aos.services.oss.model.UploadPartRequest;
import com.aliyun.aos.services.oss.model.UploadPartResult;
import com.aliyun.aos.services.oss.model.transform.MultiObjectDeleteXmlFactory;
import com.aliyun.aos.services.oss.model.transform.RequestXmlFactory;
import com.aliyun.aos.services.oss.model.transform.Unmarshallers;
import com.aliyun.aos.transform.Unmarshaller;
import com.aliyun.aos.util.BinaryUtils;
import java.io.*;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package com.aliyun.aos.services.oss:
//            IOSSClient

public class OSSClientImpl extends AOSWebServiceClient
    implements IOSSClient
{

    public OSSClientImpl()
    {
        this(null);
    }

    public OSSClientImpl(AOSCredentials aosCredentials)
    {
        this(aosCredentials, new ClientConfiguration());
    }

    public OSSClientImpl(AOSCredentials aosCredentials, ClientConfiguration clientConfiguration)
    {
        super(clientConfiguration);
        errorResponseHandler = new OSSErrorResponseHandler();
        defaultUnmarshaller = new com.aliyun.aos.services.oss.model.transform.Unmarshallers.EmptyResponseUnmarshaller();
        voidResponseHandler = new OSSXmlResponseHandler(defaultUnmarshaller);
        bucketNameUtils = new BucketNameUtils();
        aosCredentialsProvider = new StaticCredentialsProvider(aosCredentials);
        init();
    }

    private void init()
    {
        setEndpoint(Constants.OSS_HOSTNAME);
        HandlerChainFactory chainFactory = new HandlerChainFactory();
        requestHandlers.addAll(chainFactory.newRequestHandlerChain("/com//aliyun/aos/servoces/oss/request.handlers"));
    }

    public ObjectListing listObjects(String bucketName)
        throws AOSClientException, AOSServiceException
    {
        return listObjects(new ListObjectsRequest(bucketName, null, null, null, null));
    }

    public ObjectListing listAllObjects(String bucketName, String prefix, String delimiter)
        throws AOSClientException, AOSServiceException
    {
        ObjectListing list = null;
        Set commonPrefix = new HashSet();
        Set objectSummaries = new HashSet();
        list = listObjects(new ListObjectsRequest(bucketName, prefix, null, delimiter, Integer.valueOf(1000)));
        commonPrefix.addAll(list.getCommonPrefixes());
        objectSummaries.addAll(list.getObjectSummaries());
        for(; list.isTruncated(); objectSummaries.addAll(list.getObjectSummaries()))
        {
            list = listNextBatchOfObjects(list);
            commonPrefix.addAll(list.getCommonPrefixes());
        }

        List summaries = new ArrayList();
        List prefixList = new ArrayList();
        summaries.addAll(objectSummaries);
        prefixList.addAll(commonPrefix);
        list.setCommonPrefixes(prefixList);
        list.setObjectSummaries(summaries);
        return list;
    }

    public ObjectListing listObjects(String bucketName, String prefix)
        throws AOSClientException, AOSServiceException
    {
        return listObjects(new ListObjectsRequest(bucketName, prefix, null, null, null));
    }

    public ObjectListing listObjects(ListObjectsRequest listObjectsRequest)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(listObjectsRequest.getBucketName(), "The bucket name parameter must be specified when listing objects in a bucket");
        Request request = createRequest(listObjectsRequest.getBucketName(), null, listObjectsRequest, HttpMethodName.GET);
        if(listObjectsRequest.getPrefix() != null)
            request.addParameter("prefix", listObjectsRequest.getPrefix());
        if(listObjectsRequest.getMarker() != null)
            request.addParameter("marker", listObjectsRequest.getMarker());
        if(listObjectsRequest.getDelimiter() != null)
            request.addParameter("delimiter", listObjectsRequest.getDelimiter());
        if(listObjectsRequest.getMaxKeys() != null && listObjectsRequest.getMaxKeys().intValue() > 0)
            request.addParameter("max-keys", listObjectsRequest.getMaxKeys().toString());
        return (ObjectListing)invoke(request, new com.aliyun.aos.services.oss.model.transform.Unmarshallers.ListObjectsUnmarshaller(), listObjectsRequest.getBucketName(), null);
    }

    public ObjectListing listNextBatchOfObjects(ObjectListing previousObjectListing)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(previousObjectListing, "The previous object listing parameter must be specified when listing the next batch of objects in a bucket");
        if(!previousObjectListing.isTruncated())
        {
            ObjectListing emptyListing = new ObjectListing();
            emptyListing.setBucketName(previousObjectListing.getBucketName());
            emptyListing.setDelimiter(previousObjectListing.getDelimiter());
            emptyListing.setMarker(previousObjectListing.getNextMarker());
            emptyListing.setMaxKeys(previousObjectListing.getMaxKeys());
            emptyListing.setPrefix(previousObjectListing.getPrefix());
            emptyListing.setTruncated(false);
            return emptyListing;
        } else
        {
            return listObjects(new ListObjectsRequest(previousObjectListing.getBucketName(), previousObjectListing.getPrefix(), previousObjectListing.getNextMarker(), previousObjectListing.getDelimiter(), new Integer(previousObjectListing.getMaxKeys())));
        }
    }

    public Owner getOSSAccountOwner()
        throws AOSClientException, AOSServiceException
    {
        Request request = createRequest(null, null, new ListBucketsRequest(), HttpMethodName.GET);
        return (Owner)invoke(request, new com.aliyun.aos.services.oss.model.transform.Unmarshallers.ListBucketsOwnerUnmarshaller(), null, null);
    }

    public boolean doesBucketExist(String bucketName)
        throws AOSClientException, AOSServiceException
    {
        try
        {
            listObjects(new ListObjectsRequest(bucketName, null, null, null, Integer.valueOf(0)));
        }
        catch(AOSServiceException ase)
        {
            if(aosCredentialsProvider.getCredentials() == null)
                throw ase;
            if(ase.getErrorCode().equalsIgnoreCase("InvalidAccessKeyId") || ase.getErrorCode().equalsIgnoreCase("SignatureDoesNotMatch"))
                throw ase;
            switch(ase.getStatusCode())
            {
            case 403: 
                return true;

            case 404: 
                return false;
            }
            throw ase;
        }
        return true;
    }

    public ListBucketResult listBuckets()
        throws AOSClientException, AOSServiceException
    {
        return listBuckets(new ListBucketsRequest());
    }

    public ListBucketResult listBuckets(ListBucketsRequest listBucketsRequest)
        throws AOSClientException, AOSServiceException
    {
        Request request = createRequest(null, null, listBucketsRequest, HttpMethodName.GET);
        return (ListBucketResult)invoke(request, new com.aliyun.aos.services.oss.model.transform.Unmarshallers.ListBucketsUnmarshaller(), null, null);
    }

    public Bucket createBucket(CreateBucketRequest createBucketRequest)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(createBucketRequest, "The CreateBucketRequest parameter must be specified when creating a bucket");
        String bucketName = createBucketRequest.getBucketName();
        CannedAccessControlList acl = createBucketRequest.getCannedAcl();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when creating a bucket");
        if(bucketName != null)
            bucketName = bucketName.trim();
        bucketNameUtils.validateBucketName(bucketName);
        Request request = createRequest(bucketName, null, createBucketRequest, HttpMethodName.PUT);
        if(acl != null)
            request.addHeader("x-oss-acl", acl.toString());
        invoke(request, voidResponseHandler, bucketName, null);
        return new Bucket(bucketName);
    }

    public Bucket createBucket(String bucketName)
        throws AOSClientException, AOSServiceException
    {
        return createBucket(new CreateBucketRequest(bucketName));
    }

    public void setBucketAcl(String bucketName, CannedAccessControlList acl)
        throws AOSClientException, AOSServiceException
    {
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
        createBucketRequest.setCannedAcl(acl);
        createBucket(createBucketRequest);
    }

    public GetBucketAclResult getBucketAcl(String bucketName)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when requesting a bucket's ACL");
        return getAcl(bucketName, null, null);
    }

    public ObjectMetadata getObjectMetadata(String bucketName, String key)
        throws AOSClientException, AOSServiceException
    {
        return getObjectMetadata(new GetObjectMetadataRequest(bucketName, key));
    }

    public ObjectMetadata getObjectMetadata(GetObjectMetadataRequest getObjectMetadataRequest)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(getObjectMetadataRequest, "The GetObjectMetadataRequest parameter must be specified when requesting an object's metadata");
        String bucketName = getObjectMetadataRequest.getBucketName();
        String key = getObjectMetadataRequest.getKey();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when requesting an object's metadata");
        assertParameterNotNull(key, "The key parameter must be specified when requesting an object's metadata");
        Request request = createRequest(bucketName, key, getObjectMetadataRequest, HttpMethodName.HEAD);
        ObjectMetadata metadata = (ObjectMetadata)invoke(request, new OSSMetadataResponseHandler(), bucketName, key);
        if(metadata != null)
        {
            metadata.setBucketName(getObjectMetadataRequest.getBucketName());
            metadata.setKey(getObjectMetadataRequest.getKey());
        }
        return metadata;
    }

    public OSSObject getObject(String bucketName, String key)
        throws AOSClientException, AOSServiceException
    {
        return getObject(new GetObjectRequest(bucketName, key));
    }

    public OSSObject getObject(GetObjectRequest getObjectRequest)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(getObjectRequest, "The GetObjectRequest parameter must be specified when requesting an object");
        assertParameterNotNull(getObjectRequest.getBucketName(), "The bucket name parameter must be specified when requesting an object");
        assertParameterNotNull(getObjectRequest.getKey(), "The key parameter must be specified when requesting an object");
        Request request = createRequest(getObjectRequest.getBucketName(), getObjectRequest.getKey(), getObjectRequest, HttpMethodName.GET);
        if(getObjectRequest.getRange() != null)
        {
            long range[] = getObjectRequest.getRange();
            request.addHeader("Range", (new StringBuilder("bytes=")).append(Long.toString(range[0])).append("-").append(Long.toString(range[1])).toString());
        }
        addResponseHeaderParameters(request, getObjectRequest.getResponseHeaders());
        addDateHeader(request, "If-Modified-Since", getObjectRequest.getModifiedSinceConstraint());
        addDateHeader(request, "If-Unmodified-Since", getObjectRequest.getUnmodifiedSinceConstraint());
        addStringListHeader(request, "If-Match", getObjectRequest.getMatchingETagConstraints());
        addStringListHeader(request, "If-None-Match", getObjectRequest.getNonmatchingETagConstraints());
        try
        {
            OSSObject ossObject = (OSSObject)invoke(request, new OSSObjectResponseHandler(), getObjectRequest.getBucketName(), getObjectRequest.getKey());
            ossObject.setBucketName(getObjectRequest.getBucketName());
            ossObject.setKey(getObjectRequest.getKey());
            ossObject.getObjectMetadata().setBucketName(getObjectRequest.getBucketName());
            ossObject.setKey(getObjectRequest.getKey());
            return ossObject;
        }
        catch(OSSException ose)
        {
            if(ose.getStatusCode() == 412 || ose.getStatusCode() == 304)
                return null;
            else
                throw ose;
        }
    }

    public ObjectMetadata getObject(GetObjectRequest getObjectRequest, File destinationFile)
        throws AOSClientException, AOSServiceException
    {
        OSSObject ossObject;
        OutputStream outputStream;
        assertParameterNotNull(destinationFile, "The destination file parameter must be specified when downloading an object directly to a file");
        ossObject = getObject(getObjectRequest);
        if(ossObject == null)
            return null;
        outputStream = null;
        try
        {
            outputStream = new BufferedOutputStream(new FileOutputStream(destinationFile));
            byte buffer[] = new byte[10240];
            int bytesRead;
            while((bytesRead = ossObject.getObjectContent().read(buffer)) > -1) 
                outputStream.write(buffer, 0, bytesRead);
        }
        catch(IOException e)
        {
            throw new AOSClientException((new StringBuilder("Unable to store object contents to disk: ")).append(e.getMessage()).toString(), e);
        }
        break MISSING_BLOCK_LABEL_139;
        Exception exception;
        exception;
        try
        {
            outputStream.close();
        }
        catch(Exception exception1) { }
        try
        {
            ossObject.getObjectContent().close();
        }
        catch(Exception exception2) { }
        throw exception;
        try
        {
            outputStream.close();
        }
        catch(Exception exception3) { }
        try
        {
            ossObject.getObjectContent().close();
        }
        catch(Exception exception4) { }
        try
        {
            byte clientSideHash[] = ServiceUtils.computeMD5Hash(new FileInputStream(destinationFile));
            byte serverSideHash[] = ServiceUtils.fromHex(ossObject.getObjectMetadata().getETag());
            if(!Arrays.equals(clientSideHash, serverSideHash))
                throw new AOSClientException((new StringBuilder("Unable to verify integrity of data download.  Client calculated content hash didn't match hash calculated by OSS.  The data stored in '")).append(destinationFile.getAbsolutePath()).append("' may be corrupt.").toString());
        }
        catch(Exception e)
        {
            log.warn((new StringBuilder("Unable to calculate MD5 hash to validate download: ")).append(e.getMessage()).toString(), e);
        }
        return ossObject.getObjectMetadata();
    }

    public void deleteBucket(DeleteBucketRequest deleteBucketRequest)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(deleteBucketRequest, "The DeleteBucketRequest parameter must be specified when deleting a bucket");
        String bucketName = deleteBucketRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when deleting a bucket");
        Request request = createRequest(bucketName, null, deleteBucketRequest, HttpMethodName.DELETE);
        invoke(request, voidResponseHandler, bucketName, null);
    }

    public void deleteBucket(String bucketName)
        throws AOSClientException, AOSServiceException
    {
        deleteBucket(new DeleteBucketRequest(bucketName));
    }

    public PutObjectResult putObject(PutObjectRequest putObjectRequest)
        throws AOSClientException, AOSServiceException
    {
        String bucketName;
        String key;
        ObjectMetadata metadata;
        InputStream input;
        ProgressListener progressListener;
        Request request;
        ObjectMetadata returnedMetadata;
        assertParameterNotNull(putObjectRequest, "The PutObjectRequest parameter must be specified when uploading an object");
        bucketName = putObjectRequest.getBucketName();
        key = putObjectRequest.getKey();
        metadata = putObjectRequest.getMetadata();
        input = putObjectRequest.getInputStream();
        progressListener = putObjectRequest.getProgressListener();
        if(metadata == null)
            metadata = new ObjectMetadata();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when uploading an object");
        assertParameterNotNull(key, "The key parameter must be specified when uploading an object");
        if(putObjectRequest.getFile() != null)
        {
            File file = putObjectRequest.getFile();
            metadata.setContentLength(file.length());
            if(metadata.getContentType() == null)
                metadata.setContentType(Mimetypes.getInstance().getMimetype(file));
            try
            {
                input = new RepeatableFileInputStream(file);
            }
            catch(FileNotFoundException fnfe)
            {
                throw new AOSClientException("Unable to find file to upload", fnfe);
            }
        }
        request = createRequest(bucketName, key, putObjectRequest, HttpMethodName.PUT);
        if(metadata.getRawMetadata().get("Content-Length") == null)
            log.warn("No content length specified for stream data.  Stream contents will be buffered in memory and could result in out of memory errors.");
        if(progressListener != null)
        {
            input = new ProgressReportingInputStream(input, progressListener);
            fireProgressEvent(progressListener, 1);
        }
        if(!input.markSupported())
            input = new RepeatableInputStream(input, 0x20000);
        MD5DigestCalculatingInputStream md5DigestStream = null;
        if(metadata.getContentMD5() == null)
            try
            {
                md5DigestStream = new MD5DigestCalculatingInputStream(input);
                input = md5DigestStream;
            }
            catch(NoSuchAlgorithmException e)
            {
                log.warn("No MD5 digest algorithm available.  Unable to calculate checksum and verify data integrity.", e);
            }
        populateRequestMetadata(request, metadata);
        request.setContent(input);
        returnedMetadata = null;
        try
        {
            returnedMetadata = (ObjectMetadata)invoke(request, new OSSMetadataResponseHandler(), bucketName, key);
        }
        catch(AOSClientException ace)
        {
            fireProgressEvent(progressListener, 4);
            throw ace;
        }
        break MISSING_BLOCK_LABEL_369;
        Exception exception;
        exception;
        try
        {
            input.close();
        }
        catch(Exception e)
        {
            log.warn((new StringBuilder("Unable to cleanly close input stream: ")).append(e.getMessage()).toString(), e);
        }
        throw exception;
        try
        {
            input.close();
        }
        catch(Exception e)
        {
            log.warn((new StringBuilder("Unable to cleanly close input stream: ")).append(e.getMessage()).toString(), e);
        }
        String contentMd5 = metadata.getContentMD5();
        if(returnedMetadata != null && contentMd5 != null)
        {
            byte clientSideHash[] = ServiceUtils.fromBase64(contentMd5);
            byte serverSideHash[] = ServiceUtils.fromHex(returnedMetadata.getETag());
            if(!Arrays.equals(clientSideHash, serverSideHash))
            {
                fireProgressEvent(progressListener, 4);
                throw new AOSClientException("Unable to verify integrity of data upload.  Client calculated content hash didn't match hash calculated by OSS.  You may need to delete the data stored in OSS.");
            }
        }
        fireProgressEvent(progressListener, 2);
        PutObjectResult result = new PutObjectResult();
        result.SetRequsetId(returnedMetadata.GetRequsetId());
        result.setETag(returnedMetadata.getETag());
        return result;
    }

    public PutObjectResult putObject(String bucketName, String key, File file)
        throws AOSClientException, AOSServiceException
    {
        return putObject((new PutObjectRequest(bucketName, key, file)).withMetadata(new ObjectMetadata()));
    }

    public PutObjectResult putObject(String bucketName, String key, InputStream input, ObjectMetadata metadata)
        throws AOSClientException, AOSServiceException
    {
        return putObject(new PutObjectRequest(bucketName, key, input, metadata));
    }

    public CopyObjectResult copyObject(String sourceBucketName, String sourceKey, String destinationBucketName, String destinationKey)
        throws AOSClientException, AOSServiceException
    {
        return copyObject(new CopyObjectRequest(sourceBucketName, sourceKey, destinationBucketName, destinationKey));
    }

    public CopyObjectResult copyObject(CopyObjectRequest copyObjectRequest)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(copyObjectRequest.getSourceBucketName(), "The source bucket name must be specified when copying an object");
        assertParameterNotNull(copyObjectRequest.getSourceKey(), "The source object key must be specified when copying an object");
        assertParameterNotNull(copyObjectRequest.getDestinationBucketName(), "The destination bucket name must be specified when copying an object");
        assertParameterNotNull(copyObjectRequest.getDestinationKey(), "The destination object key must be specified when copying an object");
        String destinationKey = copyObjectRequest.getDestinationKey();
        String destinationBucketName = copyObjectRequest.getDestinationBucketName();
        Request request = createRequest(destinationBucketName, destinationKey, copyObjectRequest, HttpMethodName.PUT);
        populateRequestWithCopyObjectParameters(request, copyObjectRequest);
        request.getHeaders().remove("Content-Length");
        CopyObjectResult result = null;
        try
        {
            result = (CopyObjectResult)invoke(request, new com.aliyun.aos.services.oss.model.transform.Unmarshallers.CopyObjectUnmarshaller(), destinationBucketName, destinationKey);
        }
        catch(AOSOSSException ase)
        {
            if(ase.getStatusCode() == 412)
                return null;
            else
                throw ase;
        }
        return result;
    }

    public void deleteObject(String bucketName, String key)
        throws AOSClientException, AOSServiceException
    {
        deleteObject(new DeleteObjectRequest(bucketName, key));
    }

    public void deleteObject(DeleteObjectRequest deleteObjectRequest)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(deleteObjectRequest, "The delete object request must be specified when deleting an object");
        assertParameterNotNull(deleteObjectRequest.getBucketName(), "The bucket name must be specified when deleting an object");
        assertParameterNotNull(deleteObjectRequest.getKey(), "The key must be specified when deleting an object");
        Request request = createRequest(deleteObjectRequest.getBucketName(), deleteObjectRequest.getKey(), deleteObjectRequest, HttpMethodName.DELETE);
        invoke(request, voidResponseHandler, deleteObjectRequest.getBucketName(), deleteObjectRequest.getKey());
    }

    public URL generatePresignedUrl(String bucketName, String key, Date expiration, HttpMethod method)
        throws AOSClientException
    {
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key, method);
        request.setExpiration(expiration);
        return generatePresignedUrl(request);
    }

    public URL generatePresignedUrl(GeneratePresignedUrlRequest generatePresignedUrlRequest)
        throws AOSClientException
    {
        assertParameterNotNull(generatePresignedUrlRequest, "The request parameter must be specified when generating a pre-signed URL");
        String bucketName = generatePresignedUrlRequest.getBucketName();
        String key = generatePresignedUrlRequest.getKey();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when generating a pre-signed URL");
        assertParameterNotNull(generatePresignedUrlRequest.getMethod(), "The HTTP method request parameter must be specified when generating a pre-signed URL");
        if(generatePresignedUrlRequest.getExpiration() == null)
            generatePresignedUrlRequest.setExpiration(new Date(System.currentTimeMillis() + 0xdbba0L));
        HttpMethodName httpMethod = HttpMethodName.valueOf(generatePresignedUrlRequest.getMethod().toString());
        Request request = createRequest(bucketName, key, generatePresignedUrlRequest, httpMethod);
        java.util.Map.Entry entry;
        for(Iterator iterator = generatePresignedUrlRequest.getRequestParameters().entrySet().iterator(); iterator.hasNext(); request.addParameter((String)entry.getKey(), (String)entry.getValue()))
            entry = (java.util.Map.Entry)iterator.next();

        presignRequest(request, generatePresignedUrlRequest.getMethod(), bucketName, key, generatePresignedUrlRequest.getExpiration(), null);
        return ServiceUtils.convertRequestToUrl(request);
    }

    private static void populateRequestWithCopyObjectParameters(Request request, CopyObjectRequest copyObjectRequest)
    {
        String copySourceHeader = (new StringBuilder("/")).append(ServiceUtils.urlEncode(copyObjectRequest.getSourceBucketName())).append("/").append(ServiceUtils.urlEncode(copyObjectRequest.getSourceKey())).toString();
        request.addHeader("x-oss-copy-source", copySourceHeader);
        addDateHeader(request, "x-oss-copy-source-if-modified-since", copyObjectRequest.getModifiedSinceConstraint());
        addDateHeader(request, "x-oss-copy-source-if-unmodified-since", copyObjectRequest.getUnmodifiedSinceConstraint());
        addStringListHeader(request, "x-oss-copy-source-if-match", copyObjectRequest.getMatchingETagConstraints());
        addStringListHeader(request, "x-oss-copy-source-if-none-match", copyObjectRequest.getNonmatchingETagConstraints());
        ObjectMetadata newObjectMetadata = copyObjectRequest.getNewObjectMetadata();
        if(newObjectMetadata != null)
        {
            request.addHeader("x-oss-metadata-directive", "REPLACE");
            populateRequestMetadata(request, newObjectMetadata);
        }
    }

    protected static void populateRequestMetadata(Request request, ObjectMetadata metadata)
    {
        Map rawMetadata = metadata.getRawMetadata();
        if(rawMetadata != null)
        {
            java.util.Map.Entry entry;
            for(Iterator iterator = rawMetadata.entrySet().iterator(); iterator.hasNext(); request.addHeader((String)entry.getKey(), entry.getValue().toString()))
                entry = (java.util.Map.Entry)iterator.next();

        }
        Map userMetadata = metadata.getUserMetadata();
        if(userMetadata != null)
        {
            for(Iterator iterator1 = userMetadata.entrySet().iterator(); iterator1.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
                if(!((String)entry.getKey()).startsWith("x-oss-meta-"))
                    request.addHeader((new StringBuilder("x-oss-meta-")).append((String)entry.getKey()).toString(), (String)entry.getValue());
                else
                    request.addHeader((String)entry.getKey(), (String)entry.getValue());
            }

        }
    }

    protected Request createRequest(String bucketName, String key, AOSWebServiceRequest originalRequest, HttpMethodName httpMethod)
    {
        Request request = new DefaultRequest(originalRequest, Constants.OSS_SERVICE_NAME);
        request.setHttpMethod(httpMethod);
        request.setEndpoint(endpoint);
        if(bucketName != null)
            request.setResourcePath((new StringBuilder(String.valueOf(bucketName))).append("/").append(key == null ? "" : ServiceUtils.urlEncode(key)).toString());
        return request;
    }

    protected ExecutionContext createExecutionContext()
    {
        ExecutionContext executionContext = new ExecutionContext(requestHandlers);
        return executionContext;
    }

    protected Signer createSigner(Request request, String bucketName, String key)
    {
        String resourcePath = (new StringBuilder("/")).append(bucketName == null ? "" : (new StringBuilder(String.valueOf(bucketName))).append("/").toString()).append(key == null ? "" : key).toString();
        return new OSSSigner(request.getHttpMethod().toString(), resourcePath);
    }

    private static void addDateHeader(Request request, String header, Date value)
    {
        if(value != null)
            request.addHeader(header, ServiceUtils.formatRfc822Date(value));
    }

    private static void addResponseHeaderParameters(Request request, ResponseHeaderOverrides responseHeaders)
    {
        if(responseHeaders != null)
        {
            if(responseHeaders.getCacheControl() != null)
                request.addParameter("response-cache-control", responseHeaders.getCacheControl());
            if(responseHeaders.getContentDisposition() != null)
                request.addParameter("response-content-disposition", responseHeaders.getContentDisposition());
            if(responseHeaders.getContentEncoding() != null)
                request.addParameter("response-content-encoding", responseHeaders.getContentEncoding());
            if(responseHeaders.getContentLanguage() != null)
                request.addParameter("response-content-language", responseHeaders.getContentLanguage());
            if(responseHeaders.getContentType() != null)
                request.addParameter("response-content-type", responseHeaders.getContentType());
            if(responseHeaders.getExpires() != null)
                request.addParameter("response-expires", responseHeaders.getExpires());
        }
    }

    private static void addStringListHeader(Request request, String header, List values)
    {
        if(values != null && !values.isEmpty())
            request.addHeader(header, ServiceUtils.join(values));
    }

    private GetBucketAclResult getAcl(String bucketName, String key, AOSWebServiceRequest originalRequest)
    {
        if(originalRequest == null)
            originalRequest = new GenericBucketRequest(bucketName);
        Request request = createRequest(bucketName, key, originalRequest, HttpMethodName.GET);
        request.addParameter("acl", null);
        return (GetBucketAclResult)invoke(request, new com.aliyun.aos.services.oss.model.transform.Unmarshallers.CannedAccessControlUnmarshaller(), bucketName, key);
    }

    private AOSResponseResult invoke(Request request, Unmarshaller unmarshaller, String bucketName, String key)
    {
        return invoke(request, ((HttpResponseHandler) (new OSSXmlResponseHandler(unmarshaller))), bucketName, key);
    }

    private void fireProgressEvent(ProgressListener listener, int eventType)
    {
        if(listener == null)
        {
            return;
        } else
        {
            ProgressEvent event = new ProgressEvent(0);
            event.setEventCode(eventType);
            listener.progressChanged(event);
            return;
        }
    }

    private AOSResponseResult invoke(Request request, HttpResponseHandler responseHandler, String bucket, String key)
    {
        java.util.Map.Entry entry;
        for(Iterator iterator = request.getOriginalRequest().copyPrivateRequestParameters().entrySet().iterator(); iterator.hasNext(); request.addParameter((String)entry.getKey(), (String)entry.getValue()))
            entry = (java.util.Map.Entry)iterator.next();

        if(request.getHeaders().get("Content-Type") == null)
            request.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        AOSCredentials credentials = aosCredentialsProvider.getCredentials();
        AOSWebServiceRequest originalRequest = request.getOriginalRequest();
        if(originalRequest != null && originalRequest.getRequestCredentials() != null)
            credentials = originalRequest.getRequestCredentials();
        ExecutionContext executionContext = createExecutionContext();
        executionContext.setSigner(createSigner(request, bucket, key));
        executionContext.setCredentials(credentials);
        return client.execute(request, responseHandler, errorResponseHandler, executionContext);
    }

    private void assertParameterNotNull(Object parameterValue, String errorMessage)
    {
        if(parameterValue == null)
            throw new IllegalArgumentException(errorMessage);
        else
            return;
    }

    public PostFileGroupResult postFileGroup(PostFileGroupRequest postRequest)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(postRequest, "The PostFileGroup object request must be specified when  posting a filegroup");
        assertParameterNotNull(postRequest.getBucketName(), "The bucket name must be specified when posting a fileobject");
        assertParameterNotNull(postRequest.getKey(), "The key name must be specified when posting a fileobject");
        Request request = createRequest(postRequest.getBucketName(), postRequest.getKey(), postRequest, HttpMethodName.POST);
        request.addParameter("group", "");
        populateRequestMetadata(request, postRequest.getMetadata());
        byte xml[] = RequestXmlFactory.convertToXmlByteArray(postRequest.getParts());
        if(postRequest.getMetadata().getContentType() == null)
            request.addHeader("Content-Type", "application/octet-stream");
        request.addHeader("Content-Length", String.valueOf(xml.length));
        request.setContent(new ByteArrayInputStream(xml));
        return (PostFileGroupResult)invoke(request, new com.aliyun.aos.services.oss.model.transform.Unmarshallers.PostFileGroupResultUnmarshaller(), postRequest.getBucketName(), postRequest.getKey());
    }

    public ListFileGroupResult listFileGroup(ListFileGroupRequest listFileGroupRequest)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(listFileGroupRequest, "The list file group request must be specified when getting a ListFileGroup");
        assertParameterNotNull(listFileGroupRequest.getBucketName(), "The bucket name must be specified when getting a ListFileGroup");
        assertParameterNotNull(listFileGroupRequest.getKey(), "The key name must be specified when getting a ListFileGroup");
        Request request = createRequest(listFileGroupRequest.getBucketName(), listFileGroupRequest.getKey(), listFileGroupRequest, HttpMethodName.GET);
        request.getHeaders().put("x-oss-file-group", null);
        return (ListFileGroupResult)invoke(request, new com.aliyun.aos.services.oss.model.transform.Unmarshallers.ListFileGroupUnmarshaller(), listFileGroupRequest.getBucketName(), listFileGroupRequest.getKey());
    }

    public InitiateMultipartUploadResult initiateMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(initiateMultipartUploadRequest, "The request parameter must be specified when initiating a multipart upload");
        assertParameterNotNull(initiateMultipartUploadRequest.getBucketName(), "The bucket name parameter must be specified when initiating a multipart upload");
        assertParameterNotNull(initiateMultipartUploadRequest.getKey(), "The key parameter must be specified when initiating a multipart upload");
        Request request = createRequest(initiateMultipartUploadRequest.getBucketName(), initiateMultipartUploadRequest.getKey(), initiateMultipartUploadRequest, HttpMethodName.POST);
        request.addParameter("uploads", null);
        if(initiateMultipartUploadRequest.objectMetadata != null)
            populateRequestMetadata(request, initiateMultipartUploadRequest.objectMetadata);
        request.getHeaders().remove("Content-Length");
        request.addHeader("Content-Length", "0");
        request.setContent(new ByteArrayInputStream(new byte[0]));
        return (InitiateMultipartUploadResult)invoke(request, new com.aliyun.aos.services.oss.model.transform.Unmarshallers.InitiateMultipartUploadResultUnmarshaller(), initiateMultipartUploadRequest.getBucketName(), initiateMultipartUploadRequest.getKey());
    }

    public UploadPartResult uploadPart(UploadPartRequest uploadPartRequest)
        throws AOSClientException, AOSServiceException
    {
        String bucketName;
        String key;
        int partNumber;
        Request request;
        InputStream inputStream;
        MD5DigestCalculatingInputStream md5DigestStream;
        ProgressListener progressListener;
        assertParameterNotNull(uploadPartRequest, "The request parameter must be specified when uploading a part");
        bucketName = uploadPartRequest.getBucketName();
        key = uploadPartRequest.getKey();
        String uploadId = uploadPartRequest.getUploadId();
        partNumber = uploadPartRequest.getPartNumber();
        long partSize = uploadPartRequest.getPartSize();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when uploading a part");
        assertParameterNotNull(key, "The key parameter must be specified when uploading a part");
        assertParameterNotNull(uploadId, "The upload ID parameter must be specified when uploading a part");
        assertParameterNotNull(Integer.valueOf(partNumber), "The part number parameter must be specified when uploading a part");
        assertParameterNotNull(Long.valueOf(partSize), "The part size parameter must be specified when uploading a part");
        request = createRequest(bucketName, key, uploadPartRequest, HttpMethodName.PUT);
        request.addParameter("uploadId", uploadId);
        request.addParameter("partNumber", Integer.toString(partNumber));
        if(uploadPartRequest.getMd5Digest() != null)
            request.addHeader("Content-MD5", uploadPartRequest.getMd5Digest());
        request.addHeader("Content-Length", Long.toString(partSize));
        inputStream = null;
        if(uploadPartRequest.getInputStream() != null)
            inputStream = uploadPartRequest.getInputStream();
        else
        if(uploadPartRequest.getFile() != null)
            try
            {
                inputStream = new InputSubstream(new RepeatableFileInputStream(uploadPartRequest.getFile()), uploadPartRequest.getFileOffset(), partSize, true);
            }
            catch(FileNotFoundException e)
            {
                throw new IllegalArgumentException("The specified file doesn't exist", e);
            }
        else
            throw new IllegalArgumentException("A File or InputStream must be specified when uploading part");
        md5DigestStream = null;
        if(uploadPartRequest.getMd5Digest() == null)
            try
            {
                md5DigestStream = new MD5DigestCalculatingInputStream(inputStream);
                inputStream = md5DigestStream;
            }
            catch(NoSuchAlgorithmException e)
            {
                log.warn("No MD5 digest algorithm available.  Unable to calculate checksum and verify data integrity.", e);
            }
        progressListener = uploadPartRequest.getProgressListener();
        if(progressListener != null)
        {
            inputStream = new ProgressReportingInputStream(inputStream, progressListener);
            fireProgressEvent(progressListener, 1024);
        }
        UploadPartResult uploadpartresult;
        try
        {
            request.setContent(inputStream);
            ObjectMetadata metadata = (ObjectMetadata)invoke(request, new OSSMetadataResponseHandler(), bucketName, key);
            if(metadata != null && md5DigestStream != null)
            {
                String contentMd5 = ServiceUtils.toBase64(md5DigestStream.getMd5Digest());
                byte clientSideHash[] = ServiceUtils.fromBase64(contentMd5);
                byte serverSideHash[] = ServiceUtils.fromHex(metadata.getETag());
                if(!Arrays.equals(clientSideHash, serverSideHash))
                {
                    fireProgressEvent(progressListener, 4);
                    throw new AOSClientException("Unable to verify integrity of data upload.  Client calculated content hash didn't match hash calculated by OSS.  You may need to delete the data stored in OSS.");
                }
            }
            fireProgressEvent(progressListener, 2048);
            UploadPartResult result = new UploadPartResult();
            result.setETag(metadata.getETag());
            result.setPartNumber(partNumber);
            uploadpartresult = result;
        }
        catch(AOSClientException ace)
        {
            fireProgressEvent(progressListener, 4096);
            throw ace;
        }
        if(inputStream != null)
            try
            {
                inputStream.close();
            }
            catch(Exception exception1) { }
        return uploadpartresult;
        Exception exception;
        exception;
        if(inputStream != null)
            try
            {
                inputStream.close();
            }
            catch(Exception exception2) { }
        throw exception;
    }

    public PartListing listParts(ListPartsRequest listPartsRequest)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(listPartsRequest, "The request parameter must be specified when listing parts");
        assertParameterNotNull(listPartsRequest.getBucketName(), "The bucket name parameter must be specified when listing parts");
        assertParameterNotNull(listPartsRequest.getKey(), "The key parameter must be specified when listing parts");
        assertParameterNotNull(listPartsRequest.getUploadId(), "The upload ID parameter must be specified when listing parts");
        Request request = createRequest(listPartsRequest.getBucketName(), listPartsRequest.getKey(), listPartsRequest, HttpMethodName.GET);
        request.addParameter("uploadId", listPartsRequest.getUploadId());
        if(listPartsRequest.getMaxParts() != null)
            request.addParameter("max-parts", listPartsRequest.getMaxParts().toString());
        if(listPartsRequest.getPartNumberMarker() != null)
            request.addParameter("part-number-marker", listPartsRequest.getPartNumberMarker().toString());
        return (PartListing)invoke(request, new com.aliyun.aos.services.oss.model.transform.Unmarshallers.ListPartsResultUnmarshaller(), listPartsRequest.getBucketName(), listPartsRequest.getKey());
    }

    public void abortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(abortMultipartUploadRequest, "The request parameter must be specified when aborting a multipart upload");
        assertParameterNotNull(abortMultipartUploadRequest.getBucketName(), "The bucket name parameter must be specified when aborting a multipart upload");
        assertParameterNotNull(abortMultipartUploadRequest.getKey(), "The key parameter must be specified when aborting a multipart upload");
        assertParameterNotNull(abortMultipartUploadRequest.getUploadId(), "The upload ID parameter must be specified when aborting a multipart upload");
        String bucketName = abortMultipartUploadRequest.getBucketName();
        String key = abortMultipartUploadRequest.getKey();
        Request request = createRequest(bucketName, key, abortMultipartUploadRequest, HttpMethodName.DELETE);
        request.addParameter("uploadId", abortMultipartUploadRequest.getUploadId());
        invoke(request, voidResponseHandler, bucketName, key);
    }

    public CompleteMultipartUploadResult completeMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(completeMultipartUploadRequest, "The request parameter must be specified when completing a multipart upload");
        String bucketName = completeMultipartUploadRequest.getBucketName();
        String key = completeMultipartUploadRequest.getKey();
        String uploadId = completeMultipartUploadRequest.getUploadId();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when completing a multipart upload");
        assertParameterNotNull(key, "The key parameter must be specified when completing a multipart upload");
        assertParameterNotNull(uploadId, "The upload ID parameter must be specified when completing a multipart upload");
        assertParameterNotNull(completeMultipartUploadRequest.getPartETags(), "The part ETags parameter must be specified when completing a multipart upload");
        Request request = createRequest(bucketName, key, completeMultipartUploadRequest, HttpMethodName.POST);
        request.addParameter("uploadId", uploadId);
        byte xml[] = RequestXmlFactory.convertToXmlByteArray(completeMultipartUploadRequest.getPartETags());
        request.addHeader("Content-Type", "text/plain");
        request.addHeader("Content-Length", String.valueOf(xml.length));
        request.setContent(new ByteArrayInputStream(xml));
        return (CompleteMultipartUploadResult)invoke(request, new com.aliyun.aos.services.oss.model.transform.Unmarshallers.CompleteMultipartUploadResultUnmarshaller(), bucketName, key);
    }

    public MultipartUploadListing listMultipartUploads(ListMultipartUploadsRequest listMultipartUploadsRequest)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(listMultipartUploadsRequest, "The request parameter must be specified when listing multipart uploads");
        assertParameterNotNull(listMultipartUploadsRequest.getBucketName(), "The bucket name parameter must be specified when listing multipart uploads");
        Request request = createRequest(listMultipartUploadsRequest.getBucketName(), null, listMultipartUploadsRequest, HttpMethodName.GET);
        request.addParameter("uploads", null);
        if(listMultipartUploadsRequest.getKeyMarker() != null)
            request.addParameter("key-marker", listMultipartUploadsRequest.getKeyMarker());
        if(listMultipartUploadsRequest.getMaxUploads() != null)
            request.addParameter("max-uploads", listMultipartUploadsRequest.getMaxUploads().toString());
        if(listMultipartUploadsRequest.getUploadIdMarker() != null)
            request.addParameter("upload-id-marker", listMultipartUploadsRequest.getUploadIdMarker());
        if(listMultipartUploadsRequest.getDelimiter() != null)
            request.addParameter("delimiter", listMultipartUploadsRequest.getDelimiter());
        if(listMultipartUploadsRequest.getPrefix() != null)
            request.addParameter("prefix", listMultipartUploadsRequest.getPrefix());
        return (MultipartUploadListing)invoke(request, new com.aliyun.aos.services.oss.model.transform.Unmarshallers.ListMultipartUploadsResultUnmarshaller(), listMultipartUploadsRequest.getBucketName(), null);
    }

    public DeleteObjectsResult deleteObjects(DeleteObjectsRequest deleteObjectsRequest)
        throws AOSClientException, AOSServiceException
    {
        Request request = createRequest(deleteObjectsRequest.getBucketName(), null, deleteObjectsRequest, HttpMethodName.POST);
        request.addParameter("delete", null);
        byte content[] = (new MultiObjectDeleteXmlFactory()).convertToXmlByteArray(deleteObjectsRequest);
        request.addHeader("Content-Length", String.valueOf(content.length));
        request.addHeader("Content-Type", "application/xml");
        request.setContent(new ByteArrayInputStream(content));
        try
        {
            byte md5[] = ServiceUtils.computeMD5Hash(content);
            String md5Base64 = BinaryUtils.toHex(md5);
            request.addHeader("Content-MD5", md5Base64);
        }
        catch(Exception e)
        {
            throw new AOSClientException("Couldn't compute md5 sum", e);
        }
        DeleteObjectsResponse response = (DeleteObjectsResponse)invoke(request, new com.aliyun.aos.services.oss.model.transform.Unmarshallers.DeleteObjectsResultUnmarshaller(), deleteObjectsRequest.getBucketName(), null);
        if(!response.getErrors().isEmpty())
            throw new MultiObjectDeleteException(response.getErrors(), response.getDeletedObjects());
        else
            return new DeleteObjectsResult(response.getDeletedObjects());
    }

    public RequestQuota queryQuota(QueryQuotaRequest queryQuotaRequest)
        throws AOSClientException, AOSServiceException
    {
        assertParameterNotNull(queryQuotaRequest, "The QueryQuotaRequest parameter must be specified when requesting quota.");
        String bucket = queryQuotaRequest.getBucket();
        String requestId = queryQuotaRequest.getQueryRequestId();
        assertParameterNotNull(bucket, "The request parameter must be specified when query quota.");
        assertParameterNotNull(requestId, "The request parameter must be specified when query quota.");
        Request request = createRequest(bucket, null, queryQuotaRequest, HttpMethodName.HEAD);
        request.addParameter("quota", requestId);
        RequestQuota requestQuota = (RequestQuota)invoke(request, new OSSQueryQuotaHandler(), bucket, null);
        return requestQuota;
    }

    public RequestQuota queryQuota(String bucket, String requestId)
        throws AOSClientException, AOSServiceException
    {
        return queryQuota(new QueryQuotaRequest(bucket, requestId));
    }

    protected void presignRequest(Request request, HttpMethod methodName, String bucketName, String key, Date expiration, String subResource)
    {
        if(requestHandlers != null)
        {
            RequestHandler requestHandler;
            for(Iterator iterator = requestHandlers.iterator(); iterator.hasNext(); requestHandler.beforeRequest(request))
                requestHandler = (RequestHandler)iterator.next();

        }
        String resourcePath = (new StringBuilder("/")).append(bucketName == null ? "" : (new StringBuilder(String.valueOf(bucketName))).append("/").toString()).append(key == null ? "" : key).append(subResource == null ? "" : (new StringBuilder("?")).append(subResource).toString()).toString();
        AOSCredentials credentials = aosCredentialsProvider.getCredentials();
        AOSWebServiceRequest originalRequest = request.getOriginalRequest();
        if(originalRequest != null && originalRequest.getRequestCredentials() != null)
            credentials = originalRequest.getRequestCredentials();
        (new OSSSigner(methodName.toString(), resourcePath, expiration)).signForUrl(request, credentials);
    }

    private static Log log = LogFactory.getLog(com/aliyun/aos/services/oss/OSSClientImpl);
    private OSSErrorResponseHandler errorResponseHandler;
    private Unmarshaller defaultUnmarshaller;
    private OSSXmlResponseHandler voidResponseHandler;
    private final BucketNameUtils bucketNameUtils;
    private AOSCredentialsProvider aosCredentialsProvider;

}
