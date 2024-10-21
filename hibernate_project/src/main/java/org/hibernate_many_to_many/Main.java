package org.hibernate_many_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate_many_to_many.entity.Child;
import org.hibernate_many_to_many.entity.Section;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg2.xml")
                .addAnnotatedClass(Child.class)
                .addAnnotatedClass(Section.class)
                .buildSessionFactory();

        Session session = null;
        try {
            session = factory.getCurrentSession();

//            Section section = new Section("Футбол");
//            Child child1 = new Child("Вася", 5);
//            Child child2 = new Child("Федя", 6);
//            Child child3 = new Child("Зося", 7);
//            section.addChildToSection(child1);
//            section.addChildToSection(child2);
//            section.addChildToSection(child3);
//            session.beginTransaction();
//            session.persist(section);
//            session.getTransaction().commit();


//            Section section1 = new Section("Волейбол");
//            Section section2 = new Section("Шахматы");
//            Section section3 = new Section("Математика");
//            Child child = new Child("Сережа", 5);
//            child.addSectionToChild(section1);
//            child.addSectionToChild(section2);
//            child.addSectionToChild(section3);
//            session.beginTransaction();
//            session.persist(child);
//            session.getTransaction().commit();


//            session.beginTransaction();
//            Section section = session.get(Section.class, 1);
//            System.out.println(section);
//            System.out.println(section.getChildren());
//            session.getTransaction().commit();


//            session.beginTransaction();
//            Child child = session.get(Child.class, 4);
//            System.out.println(child);
//            System.out.println(child.getSections());
//            session.getTransaction().commit();


//            удаляем секцию, дети тоже удалятся (при cascadeType.ALL)
//            session.beginTransaction();
//            Section section = session.get(Section.class, 1);
//            session.remove(section);
//            session.getTransaction().commit();


            session.beginTransaction();
            Child child = session.get(Child.class, 4);
            session.remove(child);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
