package com.alltheducks.stripes.rest.actions;

import com.alltheducks.stripes.rest.JsonResolution;
import com.alltheducks.stripes.rest.RestfulActionBean;
import com.alltheducks.stripes.rest.RestfulException;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Resolution;

/**
 * Created by Shane Argo on 9/01/15.
 */
public class RestfulExceptionHandlerRestfulActionBeanTestAction extends RestfulActionBean {

    private ActionBeanContext context;

    public Resolution throwRestfulException() throws Exception {
        throw new RestfulException("Argh");
    }

    public Resolution throwException() throws Exception {
        throw new Exception("Argh");
    }

    public Resolution throwRestfulException501() throws Exception {
        throw new RestfulException(501, "Argh");
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }
}
