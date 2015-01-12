package com.alltheducks.stripes.rest.actions;

import com.alltheducks.stripes.rest.RestfulActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;

/**
 * Created by Shane Argo on 12/01/15.
 */
public class ValidationErrorsTestAction extends RestfulActionBean {

    private ActionBeanContext context;

    @Validate(required = true, minvalue = 1, maxvalue = 10)
    private int anInt;

    public Resolution returnValidationErrors() {
        return null;
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }
}
