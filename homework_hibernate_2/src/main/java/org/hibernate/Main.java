package org.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.entity.Book;
import org.hibernate.entity.Library;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Library.class)
                .buildSessionFactory();

        Session session = null;

        try{
            session = factory.getCurrentSession();
//            Book book1 = new Book("Незнайка на луне", "Н.Н. Носов", "Детская литература");
//            Book book2 = new Book("Незнайка на Меркуриии", "Н.Н. Носов", "Детская литература");
//            Book book3 = new Book("Незнайка на Венере", "Н.Н. Носов", "Детская литература");
//            Book book4 = new Book("Незнайка на Марсе", "Н.Н. Носов", "Детская литература");
//            Book book5 = new Book("Незнайка на Юпитере", "Н.Н. Носов", "Детская литература");
//            Book book6 = new Book("Незнайка на Сатурне", "Н.Н. Носов", "Детская литература");
//            Book book7 = new Book("Незнайка на Уране", "Н.Н. Носов", "Детская литература");
//
//            Library library1 = new Library("Детская библиотека №1", "Москва, ул.Пушкина, д.2");
//            Library library2 = new Library("Детская библиотека №2", "Москва, ул.Колотушкина, д.5");
//            Library library3 = new Library("Детская библиотека №3", "Москва, ул.Макарова, д.45");
//
//            library3.addBookToLibrary(book6);
//            library3.addBookToLibrary(book7);
//
//            session.beginTransaction();
//            session.persist(library3);
//            session.getTransaction().commit();



            session.beginTransaction();
            Book book = session.get(Book.class, 1);
            System.out.println(book);
            session.getTransaction().commit();


        } finally {
            session.close();
            factory.close();
        }

    }
}
