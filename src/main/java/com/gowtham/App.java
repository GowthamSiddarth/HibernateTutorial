package com.gowtham;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

public class App {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        //session.beginTransaction();
        //insertEmployeeEntity(session);
        checkGetCurrentSession(session);

        HibernateUtil.shutDown();
    }

    static void checkGetCurrentSession(Session session) {
        System.out.println("Current Session Validation: " + (HibernateUtil.getSessionFactory().getCurrentSession() == session));
    }

    static void insertEmployeeEntity(Session session) {
        try {
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setEmail("gothsiddu@gmail.com");
            employeeEntity.setFirstName("Gowtham");
            employeeEntity.setLastName("Behara");

            session.save(employeeEntity);

            session.getTransaction().commit();
        } catch (ConstraintViolationException cve) {
            System.out.println(cve.getConstraintName() + ", " + cve.getMessage());
        }
    }
}
