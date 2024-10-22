package org.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.entity.Author;
import org.hibernate.entity.Book;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Author.class)
                .buildSessionFactory();

        Session session = null;

        try{
            session = factory.getCurrentSession();

//            Author author = new Author("Айн Рэнд");
//            Book book1 = new Book("Атлант расправил плечи", "Роман-антиутопия");
//            Book book2 = new Book("Идеал", "Роман");
//            Book book3 = new Book("Источник", "Роман-антиутопия");
//            Book book4 = new Book("Добродетель эгоизма", "Сборник эссе");
//            author.addBookToAuthor(book1);
//            author.addBookToAuthor(book2);
//            author.addBookToAuthor(book3);
//            author.addBookToAuthor(book4);

            session.beginTransaction();
//            Book book = session.get(Book.class, 1);
//            System.out.println(book + " " + book.getAuthors());

            Book book = session.get(Book.class, 2);
            session.remove(book);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }

    }
}
