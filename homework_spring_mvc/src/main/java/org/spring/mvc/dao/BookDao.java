package org.spring.mvc.dao;

import org.spring.mvc.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addBook(Book book){
        String sql = "INSERT INTO books (title, author, year) VALUES (?, ?, ?)";
        int i = jdbcTemplate.update(sql, book.getBookTitle(), book.getBookAuthor(), book.getBookYear());
        return i;
    }
}
