// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BucketNameUtils.java

package com.aliyun.aos.services.oss.internal;


public class BucketNameUtils
{

    public BucketNameUtils()
    {
    }

    public void validateBucketName(String bucketName)
        throws IllegalArgumentException
    {
        if(bucketName == null)
            throw new IllegalArgumentException("Bucket name cannot be null");
        if(!bucketName.toLowerCase().equals(bucketName))
            throw new IllegalArgumentException("Bucket name should not contain uppercase characters");
        if(bucketName.contains("_"))
            throw new IllegalArgumentException("Bucket name should not contain '_'");
        if(bucketName.contains("!") || bucketName.contains("@") || bucketName.contains("#"))
            throw new IllegalArgumentException("Bucket name contains illegal characters");
        if(bucketName.length() < 3 || bucketName.length() > 63)
            throw new IllegalArgumentException("Bucket name should be between 3 and 63 characters long");
        if(bucketName.endsWith("-") || bucketName.endsWith("."))
            throw new IllegalArgumentException("Bucket name should not end with '-' or '.'");
        if(bucketName.contains(".."))
            throw new IllegalArgumentException("Bucket name should not contain two adjacent periods");
        if(bucketName.contains("-.") || bucketName.contains(".-"))
            throw new IllegalArgumentException("Bucket name should not contain dashes next to periods");
        else
            return;
    }

    public boolean isValidV2BucketName(String bucketName)
    {
        if(bucketName == null)
            return false;
        try
        {
            validateBucketName(bucketName);
        }
        catch(IllegalArgumentException e)
        {
            return false;
        }
        return true;
    }

    public boolean isDNSBucketName(String bucketName)
    {
        return isValidV2BucketName(bucketName);
    }
}
