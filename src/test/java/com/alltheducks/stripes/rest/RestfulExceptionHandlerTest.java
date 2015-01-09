package com.alltheducks.stripes.rest;

import com.alltheducks.stripes.rest.actions.RestfulExceptionHandlerActionBeanTestAction;
import com.alltheducks.stripes.rest.actions.RestfulExceptionHandlerRestfulActionBeanTestAction;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.controller.DispatcherServlet;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.mock.MockRoundtrip;
import net.sourceforge.stripes.mock.MockServletContext;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class RestfulExceptionHandlerTest {

    @Test
    public void testRestfulException_withRestfulActionBean() throws Exception {
        testAction(RestfulExceptionHandlerRestfulActionBeanTestAction.class, "throwRestfulException", "\"message\":\"Argh\"", 500);
    }

    @Test
    public void testException_withRestfulActionBean() throws Exception {
        testAction(RestfulExceptionHandlerRestfulActionBeanTestAction.class, "throwException", "\"message\":\"Argh\"", 500);
    }

    @Test
    public void testRestfulException501_withRestfulActionBean() throws Exception {
        testAction(RestfulExceptionHandlerRestfulActionBeanTestAction.class, "throwRestfulException501", "\"message\":\"Argh\"", 501);
    }

    @Test
    public void testRestfulException_withActionBean() throws Exception {
        testAction(RestfulExceptionHandlerActionBeanTestAction.class, "throwRestfulException", "\"message\":\"Argh\"", 500);
    }

    @Test(expected = Exception.class)
    public void testException_withActionBean() throws Exception {
        final MockServletContext context = constructContext();
        try {
            MockRoundtrip mockRoundtrip = new MockRoundtrip(context, RestfulExceptionHandlerActionBeanTestAction.class);
            mockRoundtrip.execute("throwException");
        }
        finally {
            cleanUpContext(context);
        }
    }

    private void testAction(Class<? extends ActionBean> bean, String action, String expectedOutputContains, int httpStatusCode) throws Exception {
        final MockServletContext context = constructContext();
        try {
            MockRoundtrip mockRoundtrip = new MockRoundtrip(context, bean);
            mockRoundtrip.execute(action);

            assertTrue(String.format("Response should contain string '%s'", expectedOutputContains), mockRoundtrip.getOutputString().contains(expectedOutputContains));
            assertEquals(httpStatusCode, mockRoundtrip.getResponse().getStatus());
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
        filterParams.put("ExceptionHandler.Class", "com.alltheducks.stripes.rest.RestfulExceptionHandler");
        context.addFilter(StripesFilter.class, "StripesFilter", filterParams);

        // Add the Stripes Dispatcher
        context.setServlet(DispatcherServlet.class, "StripesDispatcher", null);

        return context;
    }

    public void cleanUpContext(MockServletContext context) {
        context.getFilters().get(0).destroy();
    }

}