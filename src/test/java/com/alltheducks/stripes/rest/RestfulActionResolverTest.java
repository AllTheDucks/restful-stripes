package com.alltheducks.stripes.rest;

import com.alltheducks.stripes.rest.actions.RestfulActionResolverTestAction;
import net.sourceforge.stripes.controller.DispatcherServlet;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.mock.MockRoundtrip;
import net.sourceforge.stripes.mock.MockServletContext;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RestfulActionResolverTest {

        @Test
        public void testGet() throws Exception {
            final MockServletContext context = constructContext();
            try {
                MockRoundtrip mockRoundtrip = new MockRoundtrip(context, RestfulActionResolverTestAction.class);
                mockRoundtrip.getRequest().setMethod("GET");
                mockRoundtrip.execute();

                assertEquals(mockRoundtrip.getOutputString(), "\"get\"");
                assertNull(mockRoundtrip.getDestination());
            }
            finally {
                cleanUpContext(context);
            }
        }

        @Test
        public void testPut() throws Exception {
            final MockServletContext context = constructContext();
            try {
                MockRoundtrip mockRoundtrip = new MockRoundtrip(context, RestfulActionResolverTestAction.class);
                mockRoundtrip.getRequest().setMethod("PUT");
                mockRoundtrip.execute();

                assertEquals(mockRoundtrip.getOutputString(), "\"put\"");
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
            filterParams.put("ActionResolver.Class", "com.alltheducks.stripes.rest.RestfulActionResolver");
            context.addFilter(StripesFilter.class, "StripesFilter", filterParams);

            // Add the Stripes Dispatcher
            context.setServlet(DispatcherServlet.class, "StripesDispatcher", null);

            return context;
        }

        public void cleanUpContext(MockServletContext context) {
            context.getFilters().get(0).destroy();
        }

}