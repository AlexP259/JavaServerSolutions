package org.hibernate2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate2.entity.Detail;
import org.hibernate2.entity.Employee;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        Session session = null;
        try{
//            Вносим
//            session = factory.getCurrentSession();
//            Employee employee = new Employee("Роман", "Соколов", "IT", 500);
//            Detail detail = new Detail("Москва", "+7 984 336 34 53", "roman24@mail.ru");
//            employee.setEmployeeDetail(detail);
//            session.beginTransaction();
//            session.persist(employee);
//            session.getTransaction().commit();

//            Еще вносим
//            session = factory.getCurrentSession();
//            Employee employee = new Employee("Игорь", "Тигров", "Sales", 700);
//            Detail detail = new Detail("Воронеж", "+7 944 352 64 23", "igort@mail.ru");
//            employee.setEmployeeDetail(detail);
//            session.beginTransaction();
//            session.persist(employee);
//            session.getTransaction().commit();

//            Смотрим
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Employee employee = session.get(Employee.class, 2);
//            System.out.println(employee.getEmployeeDetail());
//            session.getTransaction().commit();

//            Удаляем
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Employee employee = session.get(Employee.class, 2);
//            session.remove(employee);
//            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }


    }
}
