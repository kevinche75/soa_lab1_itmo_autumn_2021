package ru.itmo.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.itmo.entity.*;

import java.util.Properties;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() throws ExceptionInInitializerError{
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(LabWork.class);
            configuration.addAnnotatedClass(Coordinates.class);
            configuration.addAnnotatedClass(Difficulty.class);
            configuration.addAnnotatedClass(Location.class);
            configuration.addAnnotatedClass(Person.class);
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
