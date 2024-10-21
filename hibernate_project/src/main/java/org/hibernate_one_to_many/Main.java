package org.hibernate_one_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate_one_to_many.entity.Department;
import org.hibernate_one_to_many.entity.Employee;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg2.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();


        Session session = null;
        try {
//            внесем сотрудников и департамент:
//            session = factory.getCurrentSession();
//            Department department = new Department("IT", 1300, 700);
//            Employee employee1 = new Employee("Виктор", "Романов",800);
//            Employee employee2 = new Employee("Ирина", "Комина",1000);
//
//            department.addEmployeeToDepartment(employee1);
//            department.addEmployeeToDepartment(employee2);
//            session.beginTransaction();
//            session.persist(department);
//            session.getTransaction().commit();


//            получим данные о департаменте:
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Department department = session.get(Department.class, 1);
//            System.out.println(department);
//            System.out.println(department.getEmployees());
//            session.getTransaction().commit();


//            получим данные о сотруднике:
            session = factory.getCurrentSession();
            session.beginTransaction();
            Employee employee = session.get(Employee.class, 4);
            session.remove(employee);
            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }

    }
}
