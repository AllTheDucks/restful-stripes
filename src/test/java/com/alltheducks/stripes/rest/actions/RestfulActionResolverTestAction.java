package com.alltheducks.stripes.rest.actions;

import com.alltheducks.stripes.rest.JsonResolution;
import com.alltheducks.stripes.rest.RestfulActionBean;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Resolution;

/**
 * Created by Shane Argo on 9/01/15.
 */
public class RestfulActionResolverTestAction implements RestfulActionBean {

    private ActionBeanContext context;

    public Resolution get() {
        return new JsonResolution("get");
    }

    public Resolution put() {
        return new JsonResolution("put");
    }

    public Resolution post() {
        return new JsonResolution("post");
    }

    public Resolution delete() {
        return new JsonResolution("delete");
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
