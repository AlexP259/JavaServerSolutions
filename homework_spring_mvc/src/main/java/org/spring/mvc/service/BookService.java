package org.spring.mvc.service;

import org.spring.mvc.dao.BookDao;
import org.spring.mvc.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService{
    @Autowired
    private BookDao dao;

    @Override
    public int registerBook(Book book) {
        int i = dao.addBook(book);
        return i;
    }
}
