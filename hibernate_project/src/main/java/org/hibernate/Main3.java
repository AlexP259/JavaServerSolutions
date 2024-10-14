package org.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.entity.Employee;

import java.util.List;

public class Main3 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();

        try{
            Session session = factory.getCurrentSession();
            session.beginTransaction();

//            List<Employee> employees = session.createQuery("from Employee").list();
//            List<Employee> employees = session.createQuery("from Employee WHERE name = 'Игорь'").list();
            List<Employee> employees = session.createQuery("from Employee WHERE name = 'Игорь' AND salary > 650").list();
            for (Employee employee : employees) {
                System.out.println(employee);
            }

            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
