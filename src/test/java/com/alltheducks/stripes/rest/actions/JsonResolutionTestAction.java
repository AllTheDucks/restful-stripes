package com.alltheducks.stripes.rest.actions;

import com.alltheducks.stripes.rest.JsonResolution;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Resolution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shane Argo on 8/01/15.
 */
public class JsonResolutionTestAction implements ActionBean {

    private ActionBeanContext context;
    private static ObjectMapper reuseObjectMapper = new ObjectMapper();

    public Resolution returnNull() {
        return new JsonResolution(null);
    }

    public Resolution returnInt() {
        return new JsonResolution(3);
    }

    public Resolution returnArray() {
        final ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello");
        strings.add("Howdy");
        strings.add("G'day");

        return new JsonResolution(strings);
    }

    public Resolution returnMap() {
        final HashMap<String, String> map = new HashMap<String, String>();
        map.put("yes", "oui");
        map.put("no", "aucun");

        return new JsonResolution(map);
    }

    public Resolution reuseObjectMapperA() {
        return new JsonResolution("hello", reuseObjectMapper);
    }

    public Resolution reuseObjectMapperB() {
        return new JsonResolution("howdy", reuseObjectMapper);
    }

    public Resolution returnSimpleObject() {
        final SimpleObject simpleObject = new SimpleObject();
        simpleObject.setaString("hello");
        simpleObject.setAnInt(3);
        simpleObject.setaDouble(1.23);

        return new JsonResolution(simpleObject);
    }

    public Resolution returnComplexObject() {
        final SimpleObject simpleObject1 = new SimpleObject();
        simpleObject1.setaString("one");
        simpleObject1.setAnInt(1);
        simpleObject1.setaDouble(1.11);

        final SimpleObject simpleObject2 = new SimpleObject();
        simpleObject2.setaString("two");
        simpleObject2.setAnInt(2);
        simpleObject2.setaDouble(2.22);

        final SimpleObject simpleObject3 = new SimpleObject();
        simpleObject3.setaString("three");
        simpleObject3.setAnInt(3);
        simpleObject3.setaDouble(3.33);

        final ArrayList<SimpleObject> simpleObjectList = new ArrayList<SimpleObject>();
        simpleObjectList.add(simpleObject1);
        simpleObjectList.add(simpleObject2);
        simpleObjectList.add(simpleObject3);

        final HashMap<String, SimpleObject> simpleObjectMap = new HashMap<String, SimpleObject>();
        simpleObjectMap.put("one", simpleObject1);
        simpleObjectMap.put("two", simpleObject2);
        simpleObjectMap.put("three", simpleObject3);

        final ComplexObject complexObject = new ComplexObject();
        complexObject.setaSimpleObject(simpleObject1);
        complexObject.setaList(simpleObjectList);
        complexObject.setaMap(simpleObjectMap);

        return new JsonResolution(complexObject);
    }

    public Resolution returnRecursiveReferenceObject() {
        final RecursiveReferenceObject recursiveReferenceObject = new RecursiveReferenceObject();
        recursiveReferenceObject.setaRecursiveReferenceObject(recursiveReferenceObject);

        return new JsonResolution(recursiveReferenceObject);
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    private class SimpleObject {

        private String aString;
        private int anInt;
        private double aDouble;

        public String getaString() {
            return aString;
        }

        public void setaString(String aString) {
            this.aString = aString;
        }

        public int getAnInt() {
            return anInt;
        }

        public void setAnInt(int anInt) {
            this.anInt = anInt;
        }

        public double getaDouble() {
            return aDouble;
        }

        public void setaDouble(double aDouble) {
            this.aDouble = aDouble;
        }

    }

    private class ComplexObject {

        private SimpleObject aSimpleObject;
        private List<SimpleObject> aList;
        private Map<String, SimpleObject> aMap;

        public SimpleObject getaSimpleObject() {
            return aSimpleObject;
        }

        public void setaSimpleObject(SimpleObject aSimpleObject) {
            this.aSimpleObject = aSimpleObject;
        }

        public List<SimpleObject> getaList() {
            return aList;
        }

        public void setaList(List<SimpleObject> aList) {
            this.aList = aList;
        }

        public Map<String, SimpleObject> getaMap() {
            return aMap;
        }

        public void setaMap(Map<String, SimpleObject> aMap) {
            this.aMap = aMap;
        }
    }

    private class RecursiveReferenceObject {

        private RecursiveReferenceObject aRecursiveReferenceObject;

        public RecursiveReferenceObject getaRecursiveReferenceObject() {
            return aRecursiveReferenceObject;
        }

        public void setaRecursiveReferenceObject(RecursiveReferenceObject aRecursiveReferenceObject) {
            this.aRecursiveReferenceObject = aRecursiveReferenceObject;
        }
    }

}
