package org.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.entity.Employee;

public class Main4 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();

        try{
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            session.createQuery("DELETE from Employee where surname = 'Сазонов'").executeUpdate();
//            Employee emp = session.get(Employee.class, 1);
//            emp.setSalary(1500);

//            session.createQuery("UPDATE Employee set salary = 1000 WHERE name = 'Игорь'").executeUpdate();

//            session.remove(emp);    // delete()

            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
