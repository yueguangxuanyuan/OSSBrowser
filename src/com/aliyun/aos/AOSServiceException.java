// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AOSServiceException.java

package com.aliyun.aos;

// Referenced classes of package com.aliyun.aos:
//            AOSClientException

public class AOSServiceException extends AOSClientException
{
    public static final class ErrorType extends Enum
    {

        public static ErrorType[] values()
        {
            ErrorType aerrortype[];
            int i;
            ErrorType aerrortype1[];
            System.arraycopy(aerrortype = ENUM$VALUES, 0, aerrortype1 = new ErrorType[i = aerrortype.length], 0, i);
            return aerrortype1;
        }

        public static ErrorType valueOf(String s)
        {
            return (ErrorType)Enum.valueOf(com.aliyun.aos.AOSServiceException.ErrorType.class, s);
        }

        public static final ErrorType Client;
        public static final ErrorType Service;
        public static final ErrorType Unknown;
        private static final ErrorType ENUM$VALUES[];

        static 
        {
            Client = new ErrorType("Client", 0);
            Service = new ErrorType("Service", 1);
            Unknown = new ErrorType("Unknown", 2);
            ENUM$VALUES = (new ErrorType[] {
                Client, Service, Unknown
            });
        }

        private ErrorType(String s, int i)
        {
            super(s, i);
        }
    }


    public AOSServiceException(String msg)
    {
        super(msg);
    }

    public AOSServiceException(String message, Exception cause)
    {
        super(message, cause);
    }

    public String getRequestId()
    {
        return requestId;
    }

    public void setRequestId(String requestId)
    {
        this.requestId = requestId;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public ErrorType getErrorType()
    {
        return errorType;
    }

    public void setErrorType(ErrorType errorType)
    {
        this.errorType = errorType;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(int statusCode)
    {
        this.statusCode = statusCode;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String toString()
    {
        return (new StringBuilder("Status Code: ")).append(statusCode).append(", AOS ServiceName: ").append(serviceName).append(", AOS RequestID: ").append(requestId).append(", AOS ErrorCode:").append(errorCode).append(", AOS ErrorMessage:").append(errorMessage).toString();
    }

    private static final long serialVersionUID = 1L;
    private String requestId;
    private String errorCode;
    private String errorMessage;
    private ErrorType errorType;
    private int statusCode;
    private String serviceName;
}
