package com.alltheducks.stripes.rest;

/**
 * Created by Shane Argo on 9/01/15.
 */
public class RestfulException extends Exception {

    public static final int DEFAULT_HTTP_STATUS_CODE = 500;

    private int httpStatusCode;

    public RestfulException() {
        this(DEFAULT_HTTP_STATUS_CODE);
    }

    public RestfulException(String message) {
        this(DEFAULT_HTTP_STATUS_CODE, message);
    }

    public RestfulException(String message, Throwable cause) {
        this(DEFAULT_HTTP_STATUS_CODE, message, cause);
    }

    public RestfulException(Throwable cause) {
        this(DEFAULT_HTTP_STATUS_CODE, cause);
    }

    public RestfulException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        this(DEFAULT_HTTP_STATUS_CODE, message, cause, enableSuppression, writableStackTrace);
    }

    public RestfulException(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public RestfulException(int httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public RestfulException(int httpStatusCode, String message, Throwable cause) {
        super(message, cause);
        this.httpStatusCode = httpStatusCode;
    }

    public RestfulException(int httpStatusCode, Throwable cause) {
        super(cause);
        this.httpStatusCode = httpStatusCode;
    }

    public RestfulException(int httpStatusCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.httpStatusCode = httpStatusCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
