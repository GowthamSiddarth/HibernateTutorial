package com.gowtham;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

public class App {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        //session.beginTransaction();
        //insertEmployeeEntity(session);
        //checkGetCurrentSession(session);
        //checkStatelessSession();
        getVsLoad(session);

        HibernateUtil.shutDown();
    }

    static void getVsLoad(Session session) {
        Transaction transaction = session.beginTransaction();

        try {
            EmployeeEntity employeeEntity1 = (EmployeeEntity) session.get(EmployeeEntity.class, new Integer(5));
            System.out.println(employeeEntity1);

            EmployeeEntity employeeEntity2 = (EmployeeEntity) session.load(EmployeeEntity.class, new Integer(7));
            System.out.println(employeeEntity2);
        } catch (ObjectNotFoundException onfe) {
            System.out.println(onfe.getMessage());
        } finally {
            transaction.commit();
        }
    }

    static void checkStatelessSession() {
        System.out.println(HibernateUtil.getSessionFactory().openStatelessSession());
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
