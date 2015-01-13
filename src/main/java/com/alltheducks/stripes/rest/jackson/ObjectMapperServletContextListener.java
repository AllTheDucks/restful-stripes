package com.alltheducks.stripes.rest.jackson;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.reflect.Constructor;

/**
 * Created by Shane Argo on 12/01/15.
 */
public class ObjectMapperServletContextListener implements ServletContextListener {

    public static final String OBJECT_MAPPER_MANAGER_CLASS_ATTRIBUTE = "ObjectMapperManager.class";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final String className = sce.getServletContext().getInitParameter(OBJECT_MAPPER_MANAGER_CLASS_ATTRIBUTE);

        if(className == null) {
            return;
        }

        final Class<?> managerClass;
        try {
            managerClass = ObjectMapperServletContextListener.class.getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(!managerClass.isAssignableFrom(ObjectMapperManager.class)) {
            throw new RuntimeException(String.format("The class specified by %s is not of type %s.", OBJECT_MAPPER_MANAGER_CLASS_ATTRIBUTE, ObjectMapperManager.class.getCanonicalName()));
        }

        final ObjectMapperManager objectMapperManager;
        try {
            objectMapperManager = (ObjectMapperManager) managerClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);

        }

        ObjectMapperManagerFactory.setObjectMapperManager(objectMapperManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ObjectMapperManagerFactory.setObjectMapperManager(null);
    }

}
