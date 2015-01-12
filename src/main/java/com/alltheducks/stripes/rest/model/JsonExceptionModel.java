package com.alltheducks.stripes.rest.model;

/**
 * Created by Shane Argo on 12/01/15.
 */
public class JsonExceptionModel {

    private String message;
    private String exceptionClass;
    private String canonicalExceptionClass;

    public JsonExceptionModel(Exception ex) {
        this.setMessage(ex.getMessage());
        this.setExceptionClass(ex.getClass().getSimpleName());
        this.setCanonicalExceptionClass(ex.getClass().getCanonicalName());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public void setExceptionClass(String exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public String getCanonicalExceptionClass() {
        return canonicalExceptionClass;
    }

    public void setCanonicalExceptionClass(String canonicalExceptionClass) {
        this.canonicalExceptionClass = canonicalExceptionClass;
    }

}
