package com.alltheducks.stripes.rest;

import com.alltheducks.stripes.rest.jackson.ObjectMapperManagerFactory;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 * Created by Shane Argo on 9/01/15.
 */
public abstract class RestfulActionBean implements ActionBean, ValidationErrorHandler {

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        return new JsonResolution(errors, ObjectMapperManagerFactory.getObjectMapperManager().getObjectMapper());
    }

}
