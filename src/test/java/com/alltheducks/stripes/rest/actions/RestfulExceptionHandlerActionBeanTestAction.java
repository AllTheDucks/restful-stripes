package com.alltheducks.stripes.rest.actions;

import com.alltheducks.stripes.rest.RestfulActionBean;
import com.alltheducks.stripes.rest.RestfulException;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Resolution;

/**
 * Created by Shane Argo on 9/01/15.
 */
public class RestfulExceptionHandlerActionBeanTestAction implements ActionBean {

    private ActionBeanContext context;

    public Resolution throwRestfulException() throws Exception {
        throw new RestfulException("Argh");
    }

    public Resolution throwException() throws Exception {
        throw new Exception("Argh");
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
