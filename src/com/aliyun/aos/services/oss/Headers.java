// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Headers.java

package com.aliyun.aos.services.oss;


public interface Headers
{

    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_MD5 = "Content-MD5";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_LANGUAGE = "Content-Language";
    public static final String DATE = "Date";
    public static final String ETAG = "ETag";
    public static final String LAST_MODIFIED = "Last-Modified";
    public static final String SERVER = "Server";
    public static final String OSS_PREFIX = "x-oss-";
    public static final String OSS_CANNED_ACL = "x-oss-acl";
    public static final String OSS_ALTERNATE_DATE = "x-oss-date";
    public static final String OSS_USER_METADATA_PREFIX = "x-oss-meta-";
    public static final String REQUEST_ID = "x-oss-request-id";
    public static final String METADATA_DIRECTIVE = "x-oss-metadata-directive";
    public static final String COPY_SOURCE_IF_MATCH = "x-oss-copy-source-if-match";
    public static final String COPY_SOURCE_IF_NO_MATCH = "x-oss-copy-source-if-none-match";
    public static final String COPY_SOURCE_IF_UNMODIFIED_SINCE = "x-oss-copy-source-if-unmodified-since";
    public static final String COPY_SOURCE_IF_MODIFIED_SINCE = "x-oss-copy-source-if-modified-since";
    public static final String RANGE = "Range";
    public static final String COPY_PART_RANGE = "x-oss-copy-source-range";
    public static final String GET_OBJECT_IF_MODIFIED_SINCE = "If-Modified-Since";
    public static final String GET_OBJECT_IF_UNMODIFIED_SINCE = "If-Unmodified-Since";
    public static final String GET_OBJECT_IF_MATCH = "If-Match";
    public static final String GET_OBJECT_IF_NONE_MATCH = "If-None-Match";
}
