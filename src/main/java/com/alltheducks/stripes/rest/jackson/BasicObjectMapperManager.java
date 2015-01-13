package com.alltheducks.stripes.rest.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Shane Argo on 12/01/15.
 */
public class BasicObjectMapperManager implements ObjectMapperManager {

    @Override
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

}
