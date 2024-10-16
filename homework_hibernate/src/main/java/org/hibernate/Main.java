package org.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.entity.Book;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Book.class).buildSessionFactory();

        try{
            Session session = factory.getCurrentSession();

//            вносим данные:
//            Book book = new Book("Git для чайников", "Питер Джексон", 2012);
//            session.beginTransaction();
//            session.persist(book);
//            session.getTransaction().commit();

////            и сразу получаем данные по ID внесенного объекта:
//            int myId = book.getId();
//            session.beginTransaction();
//            Book book2 = session.get(Book.class, myId);
//            session.getTransaction().commit();
//            System.out.println(book2);

//            получаем данные по ID без внесения данных:
//            session.beginTransaction();
//            Book book2 = session.get(Book.class, 2);
//            session.getTransaction().commit();
//            System.out.println(book2);

//            Выведем все данные из таблицы:
//            session.beginTransaction();
//            List<Book> books = session.createQuery("from Book").list();
//            for (Book book : books) {
//                System.out.println(book);
//            }
//            session.getTransaction().commit();

//            Выведем данные из БД по title:
//            session.beginTransaction();
//            List<Book> books = session.createQuery("FROM Book WHERE title = 'Git для чайников'").list();
//            for (Book book : books) {
//                System.out.println(book);
//            }
//            session.getTransaction().commit();

//            Изменим данные в БД по ID:
//            session.beginTransaction();
//            Book book = session.get(Book.class, 6);
//            book.setTitle("Язык C для чайников");
//            session.getTransaction().commit();

//            Изменим данные для нескольких записей в таблице, придется писать запрос на HQL, так как мы не можем создать один экземпляр Book, который связывался бы с несколькими записями в БД:
//            session.beginTransaction();
//            session.createQuery("UPDATE Book SET author = 'Стив Роджерс' WHERE year = 2012").executeUpdate();
//            session.getTransaction().commit();

//            Удалим данные из БД по ID:
//            session.beginTransaction();
//            Book book = session.get(Book.class, 4);
//            session.remove(book);
//            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
