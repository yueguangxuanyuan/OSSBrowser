// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServiceUtils.java

package com.aliyun.aos.services.oss.internal;

import com.aliyun.aos.AOSClientException;
import com.aliyun.aos.Request;
import com.aliyun.aos.util.DateUtils;
import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package com.aliyun.aos.services.oss.internal:
//            Constants, StringInputStream

public class ServiceUtils {

	public ServiceUtils() {
	}

	public static Date parseIso8601Date(String dateString)
			throws ParseException {
		return dateUtils.parseIso8601Date(dateString);
	}

	public static String formatIso8601Date(Date date) {
		return dateUtils.formatIso8601Date(date);
	}

	public static Date parseRfc822Date(String dateString) throws ParseException {
		return dateUtils.parseRfc822Date(dateString);
	}

	public static String formatRfc822Date(Date date) {
		return dateUtils.formatRfc822Date(date);
	}

	public static String toHex(byte data[]) {
		StringBuilder sb = new StringBuilder(data.length * 2);
		for (int i = 0; i < data.length; i++) {
			String hex = Integer.toHexString(data[i]);
			if (hex.length() == 1)
				sb.append("0");
			else if (hex.length() == 8)
				hex = hex.substring(6);
			sb.append(hex);
		}

		return sb.toString().toLowerCase(Locale.getDefault());
	}

	public static byte[] fromHex(String hexData) {
		byte result[] = new byte[(hexData.length() + 1) / 2];
		String hexNumber = null;
		int stringOffset = 0;
		int byteOffset = 0;
		while (stringOffset < hexData.length()) {
			hexNumber = hexData.substring(stringOffset, stringOffset + 2);
			stringOffset += 2;
			result[byteOffset++] = (byte) Integer.parseInt(hexNumber, 16);
		}
		return result;
	}

	public static String toBase64(byte data[]) {
		byte b64[] = Base64.encodeBase64(data);
		return new String(b64);
	}

	public static byte[] fromBase64(String b64Data) {
		byte decoded[];
		try {
			decoded = Base64.decodeBase64(b64Data
					.getBytes(Constants.DEFAULT_ENCODING));
		} catch (UnsupportedEncodingException uee) {
			log.warn(
					"Tried to Base64-decode a String with the wrong encoding: ",
					uee);
			decoded = Base64.decodeBase64(b64Data.getBytes());
		}
		return decoded;
	}

	public static byte[] toByteArray(String s) {
		try {
			return s.getBytes(Constants.DEFAULT_ENCODING);
		} catch (UnsupportedEncodingException e) {
			log.warn(
					(new StringBuilder("Encoding "))
							.append(Constants.DEFAULT_ENCODING)
							.append(" is not supported").toString(), e);
		}
		return s.getBytes();
	}

	public static String computeMD5ETag(InputStream is)
			throws NoSuchAlgorithmException, IOException {
		try {
			BufferedInputStream bis = new BufferedInputStream(is);
			String s;
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte buffer[] = new byte[16384];
			for (int bytesRead = -1; (bytesRead = bis.read(buffer, 0,
					buffer.length)) != -1;)
				messageDigest.update(buffer, 0, bytesRead);

			s = toHex(messageDigest.digest()).toUpperCase();
			try {
				bis.close();
			} catch (Exception e) {
				System.err.println((new StringBuilder(
						"Unable to close input stream of hash candidate: "))
						.append(e).toString());
			}
			return s;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static String computeMD5ETag(String str)
			throws NoSuchAlgorithmException, IOException {
		return computeMD5ETag(((InputStream) (new StringInputStream(str))));
	}

	public static byte[] computeMD5Hash(InputStream is)
			throws NoSuchAlgorithmException, IOException {
		try {
			BufferedInputStream bis = new BufferedInputStream(is);
			byte abyte0[];
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte buffer[] = new byte[16384];
			for (int bytesRead = -1; (bytesRead = bis.read(buffer, 0,
					buffer.length)) != -1;)
				messageDigest.update(buffer, 0, bytesRead);

			abyte0 = messageDigest.digest();
			try {
				bis.close();
			} catch (Exception e) {
				System.err.println((new StringBuilder(
						"Unable to close input stream of hash candidate: "))
						.append(e).toString());
			}
			return abyte0;
		} catch (Exception exception) {

			throw exception;
		}
	}

	public static byte[] computeMD5Hash(byte data[])
			throws NoSuchAlgorithmException, IOException {
		return computeMD5Hash(((InputStream) (new ByteArrayInputStream(data))));
	}

	public static String removeQuotes(String s) {
		if (s == null)
			return null;
		s = s.trim();
		if (s.startsWith("\""))
			s = s.substring(1);
		if (s.endsWith("\""))
			s = s.substring(0, s.length() - 1);
		return s;
	}

	public static String urlEncode(String s) {
		if (s == null)
			return null;
		try {
			String encodedString = URLEncoder.encode(s,
					Constants.DEFAULT_ENCODING);
			return encodedString.replaceAll("\\+", "%20")
					.replaceAll("%2F", "/");
		} catch (UnsupportedEncodingException e) {
			throw new AOSClientException((new StringBuilder(
					"Unable to encode path: ")).append(s).toString(), e);
		}
	}

	public static URL convertRequestToUrl(Request request) {
		String urlString = (new StringBuilder()).append(request.getEndpoint())
				.append("/").append(request.getResourcePath()).toString();
		boolean firstParam = true;
		for (Iterator iterator = request.getParameters().keySet().iterator(); iterator
				.hasNext();) {
			String param = (String) iterator.next();
			if (firstParam) {
				urlString = (new StringBuilder(String.valueOf(urlString)))
						.append("?").toString();
				firstParam = false;
			} else {
				urlString = (new StringBuilder(String.valueOf(urlString)))
						.append("&").toString();
			}
			String value = (String) request.getParameters().get(param);
			urlString = (new StringBuilder(String.valueOf(urlString)))
					.append(param).append("=").append(urlEncode(value))
					.toString();
		}

		try {
			return new URL(urlString);
		} catch (MalformedURLException e) {
			throw new AOSClientException((new StringBuilder(
					"Unable to convert request to well formed URL: ")).append(
					e.getMessage()).toString(), e);
		}
	}

	public static String join(List strings) {
		String result = "";
		boolean first = true;
		for (Iterator iterator = strings.iterator(); iterator.hasNext();) {
			String s = (String) iterator.next();
			if (!first)
				result = (new StringBuilder(String.valueOf(result))).append(
						", ").toString();
			result = (new StringBuilder(String.valueOf(result))).append(s)
					.toString();
			first = false;
		}

		return result;
	}

	private static final Log log = LogFactory
			.getLog("com.aliyun.aos.services.oss.internal.ServiceUtils");
	protected static final DateUtils dateUtils = new DateUtils();

}
