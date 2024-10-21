package org.hibernate2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate2.entity.Detail;
import org.hibernate2.entity.Employee;

public class Main2 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        Session session = null;
        try {
//            session = factory.getCurrentSession();
//            Employee employee = new Employee("Еблампия", "Волков", "Sales", 700);
//            Detail detail = new Detail("Ебловск", "+7 944 352 64 23", "igort@mail.ru");
////            employee.setEmployeeDetail(detail);
//            detail.setEmployee(employee);
//            session.beginTransaction();
//            session.persist(detail);
//            session.getTransaction().commit();


//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Detail detail = session.get(Detail.class, 3);
////            Employee employee = session.get(Employee.class, 9);
//            System.out.println(detail.getEmployee());
//            session.getTransaction().commit();


//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Detail detail = session.get(Detail.class, 3);
//            session.remove(detail);
//            session.getTransaction().commit();


            session = factory.getCurrentSession();
            session.beginTransaction();

            Detail detail = session.get(Detail.class, 7);
            detail.getEmployee().setEmployeeDetail(null);
            session.remove(detail);

            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
