package com.alltheducks.stripes.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.StripesConstants;
import net.sourceforge.stripes.exception.DefaultExceptionHandler;
import net.sourceforge.stripes.exception.StripesServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Shane Argo on 9/01/15.
 */
public class RestfulExceptionHandler extends DefaultExceptionHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Resolution handleRestfulException(RestfulException ex, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(ex.getHttpStatusCode());
        return new JsonResolution(ex, objectMapper);
    }

    public Resolution handleGeneric(Exception ex, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionBean bean = (ActionBean) request.getAttribute(StripesConstants.REQ_ATTR_ACTION_BEAN);

        if(bean != null && bean instanceof RestfulActionBean) {
            response.setStatus(RestfulException.DEFAULT_HTTP_STATUS_CODE);
            return new JsonResolution(ex, objectMapper);
        }

        if(ex instanceof ServletException) {
            throw (ServletException)ex;
        } else if (ex instanceof IOException) {
            throw (IOException)ex;
        } else {
            throw new StripesServletException(ex);
        }
    }

}
