package com.alltheducks.stripes.rest;

import com.alltheducks.stripes.rest.actions.JsonResolutionTestAction;
import com.fasterxml.jackson.databind.JsonMappingException;
import net.sourceforge.stripes.controller.DispatcherServlet;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.exception.StripesServletException;
import net.sourceforge.stripes.mock.MockRoundtrip;
import net.sourceforge.stripes.mock.MockServletContext;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JsonResolutionTest {

    @Test
    public void testJsonResolution_withNull() throws Exception {
        testAction("returnNull", "null");
    }

    @Test
    public void testJsonResolution_withInt() throws Exception {
        testAction("returnInt", "3");
    }

    @Test
    public void testJsonResolution_withArray() throws Exception {
        testAction("returnArray", "[\"Hello\",\"Howdy\",\"G'day\"]");
    }

    @Test
    public void testJsonResolution_withMap() throws Exception {
        testAction("returnMap", "{\"yes\":\"oui\",\"no\":\"aucun\"}");
    }

    @Test
    public void testJsonResolution_withSimpleObject() throws Exception {
        testAction("returnSimpleObject", "{\"aString\":\"hello\",\"anInt\":3,\"aDouble\":1.23}");
    }

    @Test
    public void testJsonResolution_withComplexObject() throws Exception {
        testAction("returnComplexObject", "{\"aSimpleObject\":{\"aString\":\"one\",\"anInt\":1,\"aDouble\":1.11}," +
                "\"aList\":[{\"aString\":\"one\",\"anInt\":1,\"aDouble\":1.11}," +
                "{\"aString\":\"two\",\"anInt\":2,\"aDouble\":2.22}," +
                "{\"aString\":\"three\",\"anInt\":3,\"aDouble\":3.33}]," +
                "\"aMap\":{\"two\":{\"aString\":\"two\",\"anInt\":2,\"aDouble\":2.22}," +
                "\"one\":{\"aString\":\"one\",\"anInt\":1,\"aDouble\":1.11}," +
                "\"three\":{\"aString\":\"three\",\"anInt\":3,\"aDouble\":3.33}}}");
    }

    @Test(expected = StripesServletException.class )
    public void testJsonResolution_withRecursiveReferenceObject() throws Exception {
        final MockServletContext context = constructContext();
        try {
            MockRoundtrip mockRoundtrip = new MockRoundtrip(context, JsonResolutionTestAction.class);
            mockRoundtrip.execute("returnRecursiveReferenceObject");
        }
        finally {
            cleanUpContext(context);
        }
    }

    @Test
    public void testJsonResolution_reuseObjectMapper() throws Exception {
        testAction("reuseObjectMapperA", "\"hello\"");
        testAction("reuseObjectMapperB", "\"howdy\"");
    }

    private void testAction(String action, String expectedOutput) throws Exception {
        final MockServletContext context = constructContext();
        try {
            MockRoundtrip mockRoundtrip = new MockRoundtrip(context, JsonResolutionTestAction.class);
            mockRoundtrip.execute(action);

            assertEquals(mockRoundtrip.getOutputString(), expectedOutput);
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