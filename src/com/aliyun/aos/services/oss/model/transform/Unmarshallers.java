// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Unmarshallers.java

package com.aliyun.aos.services.oss.model.transform;

import com.aliyun.aos.http.AOSResponseResult;
import com.aliyun.aos.services.oss.internal.DeleteObjectsResponse;
import com.aliyun.aos.services.oss.internal.ListFileGroupResult;
import com.aliyun.aos.services.oss.model.*;
import com.aliyun.aos.transform.Unmarshaller;
import java.io.InputStream;

// Referenced classes of package com.aliyun.aos.services.oss.model.transform:
//            XmlResponsesSaxParser

public class Unmarshallers
{
    public static final class CannedAccessControlUnmarshaller
        implements Unmarshaller
    {

        public GetBucketAclResult unmarshall(InputStream in)
            throws Exception
        {
            return (new XmlResponsesSaxParser()).parseGetBucketAclResponse(in).getGetBucketAclResult();
        }

        public volatile Object unmarshall(Object obj)
            throws Exception
        {
            return unmarshall((InputStream)obj);
        }

        public CannedAccessControlUnmarshaller()
        {
        }
    }

    public static final class CompleteMultipartUploadResultUnmarshaller
        implements Unmarshaller
    {

        public CompleteMultipartUploadResult unmarshall(InputStream in)
            throws Exception
        {
            return (new XmlResponsesSaxParser()).parseCompleteMultipartUploadResponse(in).getCompleteMultipartUploadResult();
        }

        public volatile Object unmarshall(Object obj)
            throws Exception
        {
            return unmarshall((InputStream)obj);
        }

        public CompleteMultipartUploadResultUnmarshaller()
        {
        }
    }

    public static final class CopyObjectUnmarshaller
        implements Unmarshaller
    {

        public CopyObjectResult unmarshall(InputStream in)
            throws Exception
        {
            return (new XmlResponsesSaxParser()).parseCopyObjectResponse(in).getCopyObjectResult();
        }

        public volatile Object unmarshall(Object obj)
            throws Exception
        {
            return unmarshall((InputStream)obj);
        }

        public CopyObjectUnmarshaller()
        {
        }
    }

    public static final class DeleteObjectsResultUnmarshaller
        implements Unmarshaller
    {

        public DeleteObjectsResponse unmarshall(InputStream in)
            throws Exception
        {
            return (new XmlResponsesSaxParser()).parseDeletedObjectsResult(in).getDeleteObjectResult();
        }

        public volatile Object unmarshall(Object obj)
            throws Exception
        {
            return unmarshall((InputStream)obj);
        }

        public DeleteObjectsResultUnmarshaller()
        {
        }
    }

    public static final class EmptyResponseUnmarshaller
        implements Unmarshaller
    {

        public AOSResponseResult unmarshall(InputStream in)
            throws Exception
        {
            return new AOSResponseResult() {

                final EmptyResponseUnmarshaller this$1;

                
                {
                    this$1 = EmptyResponseUnmarshaller.this;
                    super();
                }
            }
;
        }

        public volatile Object unmarshall(Object obj)
            throws Exception
        {
            return unmarshall((InputStream)obj);
        }

        public EmptyResponseUnmarshaller()
        {
        }
    }

    public static final class InitiateMultipartUploadResultUnmarshaller
        implements Unmarshaller
    {

        public InitiateMultipartUploadResult unmarshall(InputStream in)
            throws Exception
        {
            return (new XmlResponsesSaxParser()).parseInitiateMultipartUploadResponse(in).getInitiateMultipartUploadResult();
        }

        public volatile Object unmarshall(Object obj)
            throws Exception
        {
            return unmarshall((InputStream)obj);
        }

        public InitiateMultipartUploadResultUnmarshaller()
        {
        }
    }

    public static final class ListBucketsOwnerUnmarshaller
        implements Unmarshaller
    {

        public Owner unmarshall(InputStream in)
            throws Exception
        {
            return (new XmlResponsesSaxParser()).parseListMyBucketsResponse(in).getOwner();
        }

        public volatile Object unmarshall(Object obj)
            throws Exception
        {
            return unmarshall((InputStream)obj);
        }

        public ListBucketsOwnerUnmarshaller()
        {
        }
    }

    public static final class ListBucketsUnmarshaller
        implements Unmarshaller
    {

        public ListBucketResult unmarshall(InputStream in)
            throws Exception
        {
            return (new XmlResponsesSaxParser()).parseListMyBucketsResponse(in).getListBucketResult();
        }

        public volatile Object unmarshall(Object obj)
            throws Exception
        {
            return unmarshall((InputStream)obj);
        }

        public ListBucketsUnmarshaller()
        {
        }
    }

    public static final class ListFileGroupUnmarshaller
        implements Unmarshaller
    {

        public ListFileGroupResult unmarshall(InputStream in)
            throws Exception
        {
            return (new XmlResponsesSaxParser()).parseListFileGroupResult(in).getListFileGroupResult();
        }

        public volatile Object unmarshall(Object obj)
            throws Exception
        {
            return unmarshall((InputStream)obj);
        }

        public ListFileGroupUnmarshaller()
        {
        }
    }

    public static final class ListMultipartUploadsResultUnmarshaller
        implements Unmarshaller
    {

        public MultipartUploadListing unmarshall(InputStream in)
            throws Exception
        {
            return (new XmlResponsesSaxParser()).parseListMultipartUploadsResponse(in).getListMultipartUploadsResult();
        }

        public volatile Object unmarshall(Object obj)
            throws Exception
        {
            return unmarshall((InputStream)obj);
        }

        public ListMultipartUploadsResultUnmarshaller()
        {
        }
    }

    public static final class ListObjectsUnmarshaller
        implements Unmarshaller
    {

        public ObjectListing unmarshall(InputStream in)
            throws Exception
        {
            return (new XmlResponsesSaxParser()).parseListBucketObjectsResponse(in).getObjectListing();
        }

        public volatile Object unmarshall(Object obj)
            throws Exception
        {
            return unmarshall((InputStream)obj);
        }

        public ListObjectsUnmarshaller()
        {
        }
    }

    public static final class ListPartsResultUnmarshaller
        implements Unmarshaller
    {

        public PartListing unmarshall(InputStream in)
            throws Exception
        {
            return (new XmlResponsesSaxParser()).parseListPartsResponse(in).getListPartsResult();
        }

        public volatile Object unmarshall(Object obj)
            throws Exception
        {
            return unmarshall((InputStream)obj);
        }

        public ListPartsResultUnmarshaller()
        {
        }
    }

    public static final class PostFileGroupResultUnmarshaller
        implements Unmarshaller
    {

        public PostFileGroupResult unmarshall(InputStream in)
            throws Exception
        {
            return (new XmlResponsesSaxParser()).parsePostFileGroupResult(in).getPostFileGroupResult();
        }

        public volatile Object unmarshall(Object obj)
            throws Exception
        {
            return unmarshall((InputStream)obj);
        }

        public PostFileGroupResultUnmarshaller()
        {
        }
    }


    public Unmarshallers()
    {
    }
}
