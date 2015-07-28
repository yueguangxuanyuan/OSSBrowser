// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HttpClientFactory.java

package com.aliyun.aos.http;

import com.aliyun.aos.ClientConfiguration;
import java.io.IOException;
import java.net.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.*;
import org.apache.commons.logging.Log;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.*;

// Referenced classes of package com.aliyun.aos.http:
//            ConnectionManagerFactory, AOSHttpClient

class HttpClientFactory
{
    private static class TrustingSocketFactory
        implements SchemeSocketFactory, LayeredSchemeSocketFactory
    {

        private static SSLContext createSSLContext()
            throws IOException
        {
            try
            {
                SSLContext context = SSLContext.getInstance("TLS");
                context.init(null, new TrustManager[] {
                    new TrustingX509TrustManager(null)
                }, null);
                return context;
            }
            catch(Exception e)
            {
                throw new IOException(e.getMessage());
            }
        }

        private SSLContext getSSLContext()
            throws IOException
        {
            if(sslcontext == null)
                sslcontext = createSSLContext();
            return sslcontext;
        }

        public Socket createSocket(HttpParams params)
            throws IOException
        {
            return getSSLContext().getSocketFactory().createSocket();
        }

        public Socket connectSocket(Socket sock, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpParams params)
            throws IOException, UnknownHostException, ConnectTimeoutException
        {
            int connTimeout = HttpConnectionParams.getConnectionTimeout(params);
            int soTimeout = HttpConnectionParams.getSoTimeout(params);
            SSLSocket sslsock = (SSLSocket)(sock == null ? createSocket(params) : sock);
            if(localAddress != null)
                sslsock.bind(localAddress);
            sslsock.connect(remoteAddress, connTimeout);
            sslsock.setSoTimeout(soTimeout);
            return sslsock;
        }

        public boolean isSecure(Socket sock)
            throws IllegalArgumentException
        {
            return true;
        }

        public Socket createLayeredSocket(Socket arg0, String arg1, int arg2, boolean arg3)
            throws IOException, UnknownHostException
        {
            return getSSLContext().getSocketFactory().createSocket();
        }

        private SSLContext sslcontext;

        private TrustingSocketFactory()
        {
            sslcontext = null;
        }

        TrustingSocketFactory(TrustingSocketFactory trustingsocketfactory)
        {
            this();
        }
    }

    private static class TrustingX509TrustManager
        implements X509TrustManager
    {

        public X509Certificate[] getAcceptedIssuers()
        {
            return X509_CERTIFICATES;
        }

        public void checkServerTrusted(X509Certificate ax509certificate[], String s)
            throws CertificateException
        {
        }

        public void checkClientTrusted(X509Certificate ax509certificate[], String s)
            throws CertificateException
        {
        }

        private static final X509Certificate X509_CERTIFICATES[] = new X509Certificate[0];


        private TrustingX509TrustManager()
        {
        }

        TrustingX509TrustManager(TrustingX509TrustManager trustingx509trustmanager)
        {
            this();
        }
    }


    HttpClientFactory()
    {
    }

    public HttpClient createHttpClient(ClientConfiguration config)
    {
        String userAgent = config.getUserAgent();
        if(!userAgent.equals(ClientConfiguration.DEFAULT_USER_AGENT))
            userAgent = (new StringBuilder(String.valueOf(userAgent))).append(", ").append(ClientConfiguration.DEFAULT_USER_AGENT).toString();
        HttpParams httpClientParams = new BasicHttpParams();
        HttpProtocolParams.setUserAgent(httpClientParams, userAgent);
        HttpConnectionParams.setConnectionTimeout(httpClientParams, config.getConnectionTimeout());
        HttpConnectionParams.setSoTimeout(httpClientParams, config.getSocketTimeout());
        HttpConnectionParams.setStaleCheckingEnabled(httpClientParams, true);
        HttpConnectionParams.setTcpNoDelay(httpClientParams, true);
        int socketSendBufferSizeHint = config.getSocketBufferSizeHints()[0];
        int socketReceiveBufferSizeHint = config.getSocketBufferSizeHints()[1];
        if(socketSendBufferSizeHint > 0 || socketReceiveBufferSizeHint > 0)
            HttpConnectionParams.setSocketBufferSize(httpClientParams, Math.max(socketSendBufferSizeHint, socketReceiveBufferSizeHint));
        ThreadSafeClientConnManager connectionManager = ConnectionManagerFactory.createThreadSafeClientConnManager(config, httpClientParams);
        DefaultHttpClient httpClient = new DefaultHttpClient(connectionManager, httpClientParams);
        Scheme sch = new Scheme("https", 443, new TrustingSocketFactory(null));
        httpClient.getConnectionManager().getSchemeRegistry().register(sch);
        String proxyHost = config.getProxyHost();
        int proxyPort = config.getProxyPort();
        if(proxyHost != null && proxyPort > 0)
        {
            AOSHttpClient.log.info((new StringBuilder("Configuring Proxy. Proxy Host: ")).append(proxyHost).append(" ").append("Proxy Port: ").append(proxyPort).toString());
            HttpHost proxyHttpHost = new HttpHost(proxyHost, proxyPort);
            httpClient.getParams().setParameter("http.route.default-proxy", proxyHttpHost);
            String proxyUsername = config.getProxyUsername();
            String proxyPassword = config.getProxyPassword();
            String proxyDomain = config.getProxyDomain();
            String proxyWorkstation = config.getProxyWorkstation();
            if(proxyUsername != null && proxyPassword != null)
                httpClient.getCredentialsProvider().setCredentials(new AuthScope(proxyHost, proxyPort), new NTCredentials(proxyUsername, proxyPassword, proxyWorkstation, proxyDomain));
        }
        return httpClient;
    }
}
