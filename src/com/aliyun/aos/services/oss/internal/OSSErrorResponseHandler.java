// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OSSErrorResponseHandler.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.AOSServiceException;
import com.aliyun.aos.Request;
import com.aliyun.aos.http.*;
import com.aliyun.aos.services.oss.model.*;
import com.aliyun.aos.util.XpathUtils;
import java.util.Map;
import org.w3c.dom.Document;

public class OSSErrorResponseHandler implements HttpResponseHandler {

	public OSSErrorResponseHandler() {
	}

	public AOSServiceException handle(HttpResponse errorResponse)
			throws Exception {
		if (errorResponse.getContent() == null
				|| errorResponse.getRequest().getHttpMethod() == HttpMethodName.HEAD) {
			String requestId = (String) errorResponse.getHeaders().get(
					"x-oss-request-id");
			AOSOSSException ase = new AOSOSSException(
					errorResponse.getStatusText());
			ase.setStatusCode(errorResponse.getStatusCode());
			ase.setRequestId(requestId);
			fillInErrorType(ase, errorResponse);
			return ase;
		}
		Document document = XpathUtils.documentFrom(errorResponse.getContent());
		String errorCode = XpathUtils.asString("Error/Code", document);
		String message = XpathUtils.asString("Error/Message", document);
		String requestId = XpathUtils.asString("Error/RequestId", document);
		AOSOSSException ase = null;
		if (errorCode.equals("SignatureDoesNotMatch")) {
			String stringToSign = XpathUtils.asString("Error/StringToSign",
					document);
			String stringToBytes = XpathUtils.asString(
					"Error/StringToSignBytes", document);
			String accessKeyId = XpathUtils.asString("Error/OSSAccessKeyId",
					document);
			String signatureProvided = XpathUtils.asString(
					"Error/SignatureProvided", document);
			OSSSignatureNotMatchException ose = new OSSSignatureNotMatchException(
					message);
			ose.setAccessKeyId(accessKeyId);
			ose.setStringToBytes(stringToBytes);
			ose.setStringToSign(stringToSign);
			ose.setSignatureProvided(signatureProvided);
			ase = ose;
		} else if (errorCode.equals("NoSuchBucket")) {
			String bucket = XpathUtils.asString("Error/BucketName", document);
			ase = new OSSNoSuchBucketException(bucket, message);
		} else if (errorCode.equals("InvalidPart")) {
			String etag = XpathUtils.asString("Error/ETag", document);
			String uploadId = XpathUtils.asString("Error/UploadId", document);
			String partNumber = XpathUtils.asString("Error/PartNumber",
					document);
			ase = new InvalidPartException(etag, uploadId, Integer.valueOf(
					partNumber).intValue(), message);
		} else {
			ase = new AOSOSSException(message);
		}
		ase.setStatusCode(errorResponse.getStatusCode());
		ase.setErrorCode(errorCode);
		ase.setRequestId(requestId);
		ase.setErrorMessage(message);
		fillInErrorType(ase, errorResponse);
		return ase;
	}

	private void fillInErrorType(AOSServiceException ase,
			HttpResponse errorResponse) {
		if (errorResponse.getStatusCode() >= 500)
			ase.setErrorType(com.aliyun.aos.AOSServiceException.ErrorType.Service);
		else
			ase.setErrorType(com.aliyun.aos.AOSServiceException.ErrorType.Client);
	}

	public static void main(String args1[]) {
	}

	public boolean needsConnectionLeftOpen() {
		return false;
	}

}
