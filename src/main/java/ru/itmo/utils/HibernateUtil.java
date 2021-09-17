package ru.itmo.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() throws ExceptionInInitializerError{
        try {
            Configuration configuration = new Configuration();
            configuration.addPackage("ru.itmo.entity");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            return configuration.buildSessionFactory(serviceRegistry);
//            Properties properties = new Properties();
//            properties.load(HibernateUtil.class.getClassLoader().getResourceAsStream("hibernate.properties"));
//
//            return new Configuration()
//                    .addProperties(properties)
//                    .addPackage("ru.itmo.entity")
//                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("build SeesionFactory failed :" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
