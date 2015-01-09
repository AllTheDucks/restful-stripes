package com.alltheducks.stripes.rest;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.controller.NameBasedActionResolver;

/**
 * Created by Shane Argo on 9/01/15.
 */
public class RestfulActionResolver extends NameBasedActionResolver {

    @Override
    public String getEventName(Class<? extends ActionBean> bean, ActionBeanContext context) {
        if(RestfulActionBean.class.isAssignableFrom(bean)) {
            return context.getRequest().getMethod().toLowerCase();
        }
        return super.getEventName(bean, context);
    }

}
