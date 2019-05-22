package com.gowtham;

import org.hibernate.Session;

public class App 
{
    public static void main( String[] args )
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmail("gothsiddu@gmail.com");
        employeeEntity.setFirstName("demo");
        employeeEntity.setLastName("user");

        session.save(employeeEntity);

        session.getTransaction().commit();
        HibernateUtil.shutDown();
    }
}
