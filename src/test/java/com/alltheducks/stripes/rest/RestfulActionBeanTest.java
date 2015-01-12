package com.alltheducks.stripes.rest;

import com.alltheducks.stripes.rest.actions.JsonResolutionTestAction;
import com.alltheducks.stripes.rest.actions.ValidationErrorsTestAction;
import net.sourceforge.stripes.controller.DispatcherServlet;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.mock.MockRoundtrip;
import net.sourceforge.stripes.mock.MockServletContext;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RestfulActionBeanTest {

    @Test
    public void testValidationErrors() throws Exception {
        testAction("returnValidationErrors", "{" +
                "\"anInt\":[{\"message\":null," +
                "\"replacementParameters\":[null,null]," +
                "\"actionPath\":\"/rest/actions/ValidationErrorsTest.action\"," +
                "\"beanclass\":\"com.alltheducks.stripes.rest.actions.ValidationErrorsTestAction\"," +
                "\"messageKey\":\"validation.required.valueNotPresent\"," +
                "\"defaultScope\":\"validation.required\"," +
                "\"key\":\"valueNotPresent\"," +
                "\"fieldName\":\"anInt\"," +
                "\"fieldValue\":null}]" +
                "}");
    }

    private void testAction(String action, String expectedOutput) throws Exception {
        final MockServletContext context = constructContext();
        try {
            MockRoundtrip mockRoundtrip = new MockRoundtrip(context, ValidationErrorsTestAction.class);
            mockRoundtrip.execute(action);

            assertEquals(expectedOutput, mockRoundtrip.getOutputString());
            assertNull(mockRoundtrip.getDestination());
        }
        finally {
            cleanUpContext(context);
        }
    }

    public MockServletContext constructContext() {
        MockServletContext context = new MockServletContext("test");

        // Add the Stripes Filter
        Map<String, String> filterParams = new HashMap<String, String>();
        filterParams.put("ActionResolver.Packages", "com.alltheducks.stripes.rest.actions");
        context.addFilter(StripesFilter.class, "StripesFilter", filterParams);

        // Add the Stripes Dispatcher
        context.setServlet(DispatcherServlet.class, "StripesDispatcher", null);

        return context;
    }

    public void cleanUpContext(MockServletContext context) {
        context.getFilters().get(0).destroy();
    }

}