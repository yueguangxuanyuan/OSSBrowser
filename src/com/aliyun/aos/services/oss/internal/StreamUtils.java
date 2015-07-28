// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StreamUtils.java

package com.aliyun.aos.services.oss.internal;

import java.io.*;

public class StreamUtils {

	public StreamUtils() {
	}

	public static void writeTo(InputStream input, OutputStream output)
			throws IOException {
		try {
			byte buf[] = new byte[1024];
			do {
				int i = input.read(buf);
				if (i <= 0)
					break;
				output.write(buf, 0, i);
			} while (true);
			return;
		} catch (Exception exception) {
			throw exception;
		} finally {
			input.close();
			output.flush();
		}

	}

	public static void writeTo(InputStream input, byte buffer[])
			throws IOException {
		try {

			int length;
			int offset;
			length = buffer.length;
			offset = 0;
			int i = input.read(buffer, offset, length);
			if (i == length) {
				input.close();
				return;
			}
			offset += i;
			length -= i;
		} catch (Exception exception) {

			throw exception;
		} finally {
			input.close();
		}
	}
}
