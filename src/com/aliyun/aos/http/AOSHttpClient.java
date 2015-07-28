// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AOSHttpClient.java

package com.aliyun.aos.http;

import com.aliyun.aos.*;
import com.aliyun.aos.auth.Signer;
import com.aliyun.aos.handlers.RequestHandler;
import com.aliyun.aos.util.TimingInfo;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;

// Referenced classes of package com.aliyun.aos.http:
//            HttpRequestFactory, HttpClientFactory, ExecutionContext, HttpResponseHandler, 
//            AOSResponseResult, HttpMethodReleaseInputStream, HttpResponse

public class AOSHttpClient
{

    public AOSHttpClient(ClientConfiguration clientConfiguration)
    {
        config = clientConfiguration;
        httpClient = httpClientFactory.createHttpClient(config);
    }

    public AOSResponseResult execute(Request request, HttpResponseHandler responseHandler, HttpResponseHandler errorResponseHandler, ExecutionContext executionContext)
        throws AOSClientException, AOSServiceException
    {
        long startTime = System.currentTimeMillis();
        if(executionContext == null)
            throw new AOSClientException("Internal SDK Error: No execution context parameter specified.");
        List requestHandlers = executionContext.getRequestHandlers();
        if(requestHandlers == null)
            requestHandlers = new ArrayList();
        RequestHandler requestHandler;
        for(Iterator iterator = requestHandlers.iterator(); iterator.hasNext(); requestHandler.beforeRequest(request))
            requestHandler = (RequestHandler)iterator.next();

        try
        {
            TimingInfo timingInfo = new TimingInfo(startTime);
            executionContext.setTimingInfo(timingInfo);
            AOSResponseResult t = executeHelper(request, responseHandler, errorResponseHandler, executionContext);
            timingInfo.setEndTime(System.currentTimeMillis());
            for(Iterator iterator2 = requestHandlers.iterator(); iterator2.hasNext();)
            {
                RequestHandler handler = (RequestHandler)iterator2.next();
                try
                {
                    handler.afterResponse(request, t, timingInfo);
                }
                catch(ClassCastException classcastexception) { }
            }

            return t;
        }
        catch(AOSClientException e)
        {
            RequestHandler handler;
            for(Iterator iterator1 = requestHandlers.iterator(); iterator1.hasNext(); handler.afterError(request, e))
                handler = (RequestHandler)iterator1.next();

            throw e;
        }
    }

    private AOSResponseResult executeHelper(Request request, HttpResponseHandler responseHandler, HttpResponseHandler errorResponseHandler, ExecutionContext executionContext)
        throws AOSClientException, AOSServiceException
    {
        boolean leaveHttpConnectionOpen;
        int retryCount;
        URI redirectedURI;
        HttpEntity entity;
        AOSServiceException exception;
        Map originalParameters;
        Map originalHeaders;
        leaveHttpConnectionOpen = false;
        httpClient.getConnectionManager().closeIdleConnections(30L, TimeUnit.SECONDS);
        requestLog.info((new StringBuilder("Sending Request: ")).append(request.toString()).toString());
        applyRequestData(request);
        retryCount = 0;
        redirectedURI = null;
        entity = null;
        exception = null;
        originalParameters = new HashMap();
        originalParameters.putAll(request.getParameters());
        originalHeaders = new HashMap();
        originalHeaders.putAll(request.getHeaders());
_L2:
        HttpRequestBase httpRequest;
        HttpResponse response;
        if(retryCount > 0)
        {
            request.setParameters(originalParameters);
            request.setHeaders(originalHeaders);
        }
        if(executionContext.getSigner() != null && executionContext.getCredentials() != null)
            executionContext.getSigner().sign(request, executionContext.getCredentials());
        httpRequest = httpRequestFactory.createHttpRequest(request, config, entity, executionContext);
        if(httpRequest instanceof HttpEntityEnclosingRequest)
            entity = ((HttpEntityEnclosingRequest)httpRequest).getEntity();
        if(redirectedURI != null)
            httpRequest.setURI(redirectedURI);
        response = null;
        AOSResponseResult aosresponseresult;
        if(retryCount > 0)
        {
            pauseExponentially(retryCount, exception);
            if(entity != null && entity.getContent().markSupported())
                entity.getContent().reset();
        }
        exception = null;
        retryCount++;
        response = httpClient.execute(httpRequest);
        if(!isRequestSuccessful(response))
            break MISSING_BLOCK_LABEL_335;
        leaveHttpConnectionOpen = responseHandler.needsConnectionLeftOpen();
        aosresponseresult = handleResponse(request, responseHandler, httpRequest, response, executionContext);
        if(!leaveHttpConnectionOpen)
            try
            {
                response.getEntity().getContent().close();
            }
            catch(Throwable throwable) { }
        return aosresponseresult;
        try
        {
            if(isTemporaryRedirect(response))
            {
                Header locationHeaders[] = response.getHeaders("location");
                String redirectedLocation = locationHeaders[0].getValue();
                log.debug((new StringBuilder("Redirecting to: ")).append(redirectedLocation).toString());
                redirectedURI = URI.create(redirectedLocation);
                httpRequest.setURI(redirectedURI);
            } else
            {
                leaveHttpConnectionOpen = errorResponseHandler.needsConnectionLeftOpen();
                exception = handleErrorResponse(request, errorResponseHandler, httpRequest, response);
                if(!shouldRetry(httpRequest, exception, retryCount))
                    throw exception;
            }
            break MISSING_BLOCK_LABEL_579;
        }
        catch(IOException ioe)
        {
            log.warn((new StringBuilder("Unable to execute HTTP request: ")).append(ioe.getMessage()).toString());
            if(!shouldRetry(httpRequest, ioe, retryCount))
                throw new AOSClientException((new StringBuilder("Unable to execute HTTP request: ")).append(ioe.getMessage()).toString(), ioe);
        }
        if(!leaveHttpConnectionOpen)
            try
            {
                response.getEntity().getContent().close();
            }
            catch(Throwable throwable1) { }
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        if(!leaveHttpConnectionOpen)
            try
            {
                response.getEntity().getContent().close();
            }
            catch(Throwable throwable2) { }
        throw exception1;
        if(!leaveHttpConnectionOpen)
            try
            {
                response.getEntity().getContent().close();
            }
            catch(Throwable throwable3) { }
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void applyRequestData(Request request)
    {
        if(request.getOriginalRequest() != null && request.getOriginalRequest().getRequestClientOptions() != null && request.getOriginalRequest().getRequestClientOptions().getClientMarker() != null)
            request.addHeader("User-Agent", createUserAgentString(config.getUserAgent(), request.getOriginalRequest().getRequestClientOptions().getClientMarker()));
    }

    private static String createUserAgentString(String existingUserAgentString, String userAgent)
    {
        if(existingUserAgentString.contains(userAgent))
            return existingUserAgentString;
        else
            return (new StringBuilder(String.valueOf(existingUserAgentString))).append(" ").append(userAgent).toString();
    }

    public void shutdown()
    {
        httpClient.getConnectionManager().shutdown();
    }

    private boolean shouldRetry(HttpRequestBase method, Exception exception, int retries)
    {
        if(retries > config.getMaxErrorRetry())
            return false;
        if(method instanceof HttpEntityEnclosingRequest)
        {
            HttpEntity entity = ((HttpEntityEnclosingRequest)method).getEntity();
            if(entity != null && !entity.isRepeatable())
                return false;
        }
        if((exception instanceof NoHttpResponseException) || (exception instanceof SocketException) || (exception instanceof SocketTimeoutException))
        {
            log.debug((new StringBuilder("Retrying on ")).append(exception.getClass().getName()).append(": ").append(exception.getMessage()).toString());
            return true;
        }
        if(exception instanceof AOSServiceException)
        {
            AOSServiceException ase = (AOSServiceException)exception;
            if(ase.getStatusCode() == 500 || ase.getStatusCode() == 503)
                return true;
            if(isThrottlingException(ase))
                return true;
        }
        return false;
    }

    private boolean isTemporaryRedirect(HttpResponse response)
    {
        int status = response.getStatusLine().getStatusCode();
        return status == 307 && response.getHeaders("Location") != null && response.getHeaders("Location").length > 0;
    }

    private boolean isRequestSuccessful(HttpResponse response)
    {
        int status = response.getStatusLine().getStatusCode();
        return status / 100 == 2;
    }

    private AOSResponseResult handleResponse(Request request, HttpResponseHandler responseHandler, HttpRequestBase method, HttpResponse apacheHttpResponse, ExecutionContext executionContext)
        throws IOException
    {
        com.aliyun.aos.http.HttpResponse httpResponse = createResponse(method, request, apacheHttpResponse);
        if(responseHandler.needsConnectionLeftOpen() && (method instanceof HttpEntityEnclosingRequest))
        {
            HttpEntityEnclosingRequest httpEntityEnclosingRequest = (HttpEntityEnclosingRequest)method;
            httpResponse.setContent(new HttpMethodReleaseInputStream(httpEntityEnclosingRequest));
        }
        try
        {
            AOSWebServiceResponse aosResponse = (AOSWebServiceResponse)responseHandler.handle(httpResponse);
            if(aosResponse == null)
            {
                throw new RuntimeException("Unable to unmarshall response metadata");
            } else
            {
                requestLog.info((new StringBuilder("Received successful response: ")).append(apacheHttpResponse.getStatusLine().getStatusCode()).append(", AOS Request ID: ").append(aosResponse.getRequestId()).toString());
                AOSResponseResult res = (AOSResponseResult)aosResponse.getResult();
                res.SetRequsetId(aosResponse.getRequestId());
                return (AOSResponseResult)aosResponse.getResult();
            }
        }
        catch(Exception e)
        {
            String errorMessage = (new StringBuilder("Unable to unmarshall response (")).append(e.getMessage()).append(")").toString();
            log.error(errorMessage, e);
            throw new AOSClientException(errorMessage, e);
        }
    }

    private AOSServiceException handleErrorResponse(Request request, HttpResponseHandler errorResponseHandler, HttpRequestBase method, HttpResponse apacheHttpResponse)
        throws IOException
    {
        int status = apacheHttpResponse.getStatusLine().getStatusCode();
        com.aliyun.aos.http.HttpResponse response = createResponse(method, request, apacheHttpResponse);
        if(errorResponseHandler.needsConnectionLeftOpen() && (method instanceof HttpEntityEnclosingRequestBase))
        {
            HttpEntityEnclosingRequestBase entityEnclosingRequest = (HttpEntityEnclosingRequestBase)method;
            response.setContent(new HttpMethodReleaseInputStream(entityEnclosingRequest));
        }
        AOSServiceException exception = null;
        try
        {
            exception = (AOSServiceException)errorResponseHandler.handle(response);
            requestLog.info((new StringBuilder("Received error response: ")).append(exception.toString()).toString());
        }
        catch(Exception e)
        {
            if(status == 503 && "Service Unavailable".equalsIgnoreCase(apacheHttpResponse.getStatusLine().getReasonPhrase()))
            {
                exception = new AOSServiceException("Service Unavailable");
                exception.setErrorType(com.aliyun.aos.AOSServiceException.ErrorType.Service);
                exception.setErrorCode("Service Unavailable");
            } else
            {
                String errorMessage = (new StringBuilder("Unable to unmarshall error response (")).append(e.getMessage()).append(")").toString();
                log.error(errorMessage, e);
                throw new AOSClientException(errorMessage, e);
            }
        }
        exception.setStatusCode(status);
        exception.setServiceName(request.getServiceName());
        return exception;
    }

    private com.aliyun.aos.http.HttpResponse createResponse(HttpRequestBase method, Request request, HttpResponse apacheHttpResponse)
        throws IOException
    {
        com.aliyun.aos.http.HttpResponse httpResponse = new com.aliyun.aos.http.HttpResponse(request);
        if(apacheHttpResponse.getEntity() != null)
            httpResponse.setContent(apacheHttpResponse.getEntity().getContent());
        httpResponse.setStatusCode(apacheHttpResponse.getStatusLine().getStatusCode());
        httpResponse.setStatusText(apacheHttpResponse.getStatusLine().getReasonPhrase());
        Header aheader[];
        int j = (aheader = apacheHttpResponse.getAllHeaders()).length;
        for(int i = 0; i < j; i++)
        {
            Header header = aheader[i];
            httpResponse.addHeader(header.getName(), header.getValue());
        }

        return httpResponse;
    }

    private void pauseExponentially(int retries, AOSServiceException previousException)
    {
        long scaleFactor = 300L;
        if(isThrottlingException(previousException))
            scaleFactor = 500 + random.nextInt(100);
        long delay = (long)(Math.pow(2D, retries) * (double)scaleFactor);
        delay = Math.min(delay, 20000L);
        log.debug((new StringBuilder("Retriable error detected, will retry in ")).append(delay).append("ms, attempt number: ").append(retries).toString());
        try
        {
            Thread.sleep(delay);
        }
        catch(InterruptedException e)
        {
            throw new AOSClientException(e.getMessage(), e);
        }
    }

    private boolean isThrottlingException(AOSServiceException ase)
    {
        if(ase == null)
            return false;
        else
            return "Throttling".equals(ase.getErrorCode());
    }

    protected void finalize()
        throws Throwable
    {
        shutdown();
        super.finalize();
    }

    private static final Log requestLog = LogFactory.getLog("com.aliyun.aos.request");
    public static final Log log = LogFactory.getLog(com/aliyun/aos/http/AOSHttpClient);
    private HttpClient httpClient;
    private static final int MAX_BACKOFF_IN_MILLISECONDS = 20000;
    private final ClientConfiguration config;
    private static final Random random = new Random();
    private static HttpRequestFactory httpRequestFactory = new HttpRequestFactory();
    private static HttpClientFactory httpClientFactory = new HttpClientFactory();

}
