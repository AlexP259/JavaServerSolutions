package org.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.entity.Employee;

public class Main2 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();

        try{
            Session session = factory.getCurrentSession();
            Employee employee = new Employee("Виктория", "Николаенко", "HR", 700);
            session.beginTransaction();
            session.persist(employee);  // save()

//            session.getTransaction().commit();


            int myId = employee.getId();
//            session = factory.getCurrentSession();
//            session.beginTransaction();
            Employee employee2 = session.get(Employee.class, myId);

            session.getTransaction().commit();
            System.out.println(employee2);

        } finally {
            factory.close();
        }
    }
}











