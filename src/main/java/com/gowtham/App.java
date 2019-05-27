package com.gowtham;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        insertEmployeeEntity(session);

        HibernateUtil.shutDown();
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
