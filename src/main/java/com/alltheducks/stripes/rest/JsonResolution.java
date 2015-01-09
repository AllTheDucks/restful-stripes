package com.alltheducks.stripes.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sourceforge.stripes.action.StreamingResolution;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Shane Argo on 8/01/15.
 */
public class JsonResolution extends StreamingResolution {

    public static final String CONTENT_TYPE = "application/json";

    final private Object object;
    final private ObjectMapper objectMapper;

    public JsonResolution(Object object) {
        this(object, new ObjectMapper());
    }

    public JsonResolution(Object object, ObjectMapper objectMapper) {
        super(CONTENT_TYPE);
        this.object = object;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void stream(HttpServletResponse response) throws Exception {
        objectMapper.writeValue(response.getWriter(), object);
    }

}
