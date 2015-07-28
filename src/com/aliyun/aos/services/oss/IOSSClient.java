// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IOSSClient.java

package com.aliyun.aos.services.oss;

import com.aliyun.aos.*;
import com.aliyun.aos.services.oss.internal.ListFileGroupRequest;
import com.aliyun.aos.services.oss.internal.ListFileGroupResult;
import com.aliyun.aos.services.oss.internal.QueryQuotaRequest;
import com.aliyun.aos.services.oss.internal.RequestQuota;
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
import com.aliyun.aos.services.oss.model.MultipartUploadListing;
import com.aliyun.aos.services.oss.model.OSSObject;
import com.aliyun.aos.services.oss.model.ObjectListing;
import com.aliyun.aos.services.oss.model.ObjectMetadata;
import com.aliyun.aos.services.oss.model.Owner;
import com.aliyun.aos.services.oss.model.PartListing;
import com.aliyun.aos.services.oss.model.PostFileGroupRequest;
import com.aliyun.aos.services.oss.model.PostFileGroupResult;
import com.aliyun.aos.services.oss.model.PutObjectRequest;
import com.aliyun.aos.services.oss.model.PutObjectResult;
import com.aliyun.aos.services.oss.model.UploadPartRequest;
import com.aliyun.aos.services.oss.model.UploadPartResult;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public interface IOSSClient
{

    public abstract void setEndpoint(String s);

    public abstract ObjectListing listAllObjects(String s, String s1, String s2)
        throws AOSClientException, AOSServiceException;

    public abstract ObjectListing listObjects(String s)
        throws AOSClientException, AOSServiceException;

    public abstract ObjectListing listObjects(String s, String s1)
        throws AOSClientException, AOSServiceException;

    public abstract ObjectListing listObjects(ListObjectsRequest listobjectsrequest)
        throws AOSClientException, AOSServiceException;

    public abstract ObjectListing listNextBatchOfObjects(ObjectListing objectlisting)
        throws AOSClientException, AOSServiceException;

    public abstract Owner getOSSAccountOwner()
        throws AOSClientException, AOSServiceException;

    public abstract boolean doesBucketExist(String s)
        throws AOSClientException, AOSServiceException;

    public abstract ListBucketResult listBuckets()
        throws AOSClientException, AOSServiceException;

    public abstract ListBucketResult listBuckets(ListBucketsRequest listbucketsrequest)
        throws AOSClientException, AOSServiceException;

    public abstract Bucket createBucket(CreateBucketRequest createbucketrequest)
        throws AOSClientException, AOSServiceException;

    public abstract Bucket createBucket(String s)
        throws AOSClientException, AOSServiceException;

    public abstract void setBucketAcl(String s, CannedAccessControlList cannedaccesscontrollist)
        throws AOSClientException, AOSServiceException;

    public abstract GetBucketAclResult getBucketAcl(String s);

    public abstract ObjectMetadata getObjectMetadata(String s, String s1)
        throws AOSClientException, AOSServiceException;

    public abstract ObjectMetadata getObjectMetadata(GetObjectMetadataRequest getobjectmetadatarequest)
        throws AOSClientException, AOSServiceException;

    public abstract OSSObject getObject(String s, String s1)
        throws AOSClientException, AOSServiceException;

    public abstract OSSObject getObject(GetObjectRequest getobjectrequest)
        throws AOSClientException, AOSServiceException;

    public abstract ObjectMetadata getObject(GetObjectRequest getobjectrequest, File file)
        throws AOSClientException, AOSServiceException;

    public abstract void deleteBucket(DeleteBucketRequest deletebucketrequest)
        throws AOSClientException, AOSServiceException;

    public abstract void deleteBucket(String s)
        throws AOSClientException, AOSServiceException;

    public abstract PutObjectResult putObject(PutObjectRequest putobjectrequest)
        throws AOSClientException, AOSServiceException;

    public abstract PutObjectResult putObject(String s, String s1, File file)
        throws AOSClientException, AOSServiceException;

    public abstract PutObjectResult putObject(String s, String s1, InputStream inputstream, ObjectMetadata objectmetadata)
        throws AOSClientException, AOSServiceException;

    public abstract CopyObjectResult copyObject(String s, String s1, String s2, String s3)
        throws AOSClientException, AOSServiceException;

    public abstract CopyObjectResult copyObject(CopyObjectRequest copyobjectrequest)
        throws AOSClientException, AOSServiceException;

    public abstract void deleteObject(String s, String s1)
        throws AOSClientException, AOSServiceException;

    public abstract void deleteObject(DeleteObjectRequest deleteobjectrequest)
        throws AOSClientException, AOSServiceException;

    public abstract DeleteObjectsResult deleteObjects(DeleteObjectsRequest deleteobjectsrequest)
        throws AOSClientException, AOSServiceException;

    public abstract PostFileGroupResult postFileGroup(PostFileGroupRequest postfilegrouprequest)
        throws AOSClientException, AOSServiceException;

    public abstract ListFileGroupResult listFileGroup(ListFileGroupRequest listfilegrouprequest)
        throws AOSClientException, AOSServiceException;

    public abstract URL generatePresignedUrl(String s, String s1, Date date, HttpMethod httpmethod)
        throws AOSClientException, AOSServiceException;

    public abstract URL generatePresignedUrl(GeneratePresignedUrlRequest generatepresignedurlrequest)
        throws AOSClientException, AOSServiceException;

    public abstract InitiateMultipartUploadResult initiateMultipartUpload(InitiateMultipartUploadRequest initiatemultipartuploadrequest)
        throws AOSClientException, AOSServiceException;

    public abstract UploadPartResult uploadPart(UploadPartRequest uploadpartrequest)
        throws AOSClientException, AOSServiceException;

    public abstract PartListing listParts(ListPartsRequest listpartsrequest)
        throws AOSClientException, AOSServiceException;

    public abstract void abortMultipartUpload(AbortMultipartUploadRequest abortmultipartuploadrequest)
        throws AOSClientException, AOSServiceException;

    public abstract CompleteMultipartUploadResult completeMultipartUpload(CompleteMultipartUploadRequest completemultipartuploadrequest)
        throws AOSClientException, AOSServiceException;

    public abstract MultipartUploadListing listMultipartUploads(ListMultipartUploadsRequest listmultipartuploadsrequest)
        throws AOSClientException, AOSServiceException;

    public abstract RequestQuota queryQuota(String s, String s1)
        throws AOSClientException, AOSServiceException;

    public abstract RequestQuota queryQuota(QueryQuotaRequest queryquotarequest)
        throws AOSClientException, AOSServiceException;
}
