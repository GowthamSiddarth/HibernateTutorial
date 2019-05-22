package com.gowtham;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.io.File;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new AnnotationConfiguration().configure(new File("C:\\Users\\BeharaGowtham\\workspace\\intellij-workspace\\IdeaProjects\\hibernatetutorial\\src\\main\\resources\\hibernate.cfg.xml")).buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutDown() {
        getSessionFactory().close();
    }
}
