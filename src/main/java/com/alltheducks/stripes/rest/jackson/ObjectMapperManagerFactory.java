package com.alltheducks.stripes.rest.jackson;

/**
 * Created by Shane Argo on 12/01/15.
 */
public class ObjectMapperManagerFactory {

    //Object for
    private static final Boolean LOCK = true;
    private static ObjectMapperManager objectMapperManager;

    public static ObjectMapperManager getObjectMapperManager() {
        if(objectMapperManager == null) {
            synchronized (LOCK) {
                if(objectMapperManager == null) {
                    objectMapperManager = new BasicObjectMapperManager();
                }
            }
        }
        return objectMapperManager;
    }

    public static void setObjectMapperManager(ObjectMapperManager objectMapperManager) {
        ObjectMapperManagerFactory.objectMapperManager = objectMapperManager;
    }

}
