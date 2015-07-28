// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HandlerChainFactory.java

package com.aliyun.aos.handlers;

import com.aliyun.aos.AOSClientException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.aliyun.aos.handlers:
//            RequestHandler

public class HandlerChainFactory {

	public HandlerChainFactory() {
	}

	public List newRequestHandlerChain(String resource) {
		List handlers;
		handlers = new ArrayList();
		InputStream input;
		BufferedReader reader;
		String requestHandlerClassName;
		Class requestHandlerClass;
		Object requestHandlerObject;
		try {
			input = getClass().getResourceAsStream(resource);
			if (input == null)
				return handlers;
		} catch (Exception e) {
			throw new AOSClientException(
					(new StringBuilder(
							"Unable to instantiate request handler chain for client: "))
							.append(e.getMessage()).toString(), e);
		}
		reader = new BufferedReader(new InputStreamReader(input));
		do {
			try {
				requestHandlerClassName = reader.readLine();

				if (requestHandlerClassName == null)
					break;
				requestHandlerClassName = requestHandlerClassName.trim();
				if (!requestHandlerClassName.equals("")) {
					requestHandlerClass = getClass().getClassLoader()
							.loadClass(requestHandlerClassName);
					requestHandlerObject = requestHandlerClass.newInstance();
					if (requestHandlerObject instanceof RequestHandler)
						handlers.add((RequestHandler) requestHandlerObject);
					else
						throw new AOSClientException(
								(new StringBuilder(
										"Unable to instantiate request handler chain for client.  Listed request handler ('"))
										.append(requestHandlerClassName)
										.append("') ")
										.append("does not implement the RequestHandler interface.")
										.toString());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (true);
		return handlers;
	}
}
