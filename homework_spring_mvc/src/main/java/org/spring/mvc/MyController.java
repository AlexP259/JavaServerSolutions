package org.spring.mvc;

import org.spring.mvc.entity.Book;
import org.spring.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/main")
    public String main(){
        return "main";  // при обращении по адресу "/main" возвращает страницу main.jsp
    }

    @RequestMapping("/book_register")
    public String bookR312egister(){
        return "book_register";
    }

    @RequestMapping(path = "/createBook", method = RequestMethod.POST)
    public String regis325terBook(@ModelAttribute Book book, Model m){
        bookService.registerBook(book);
        m.addAttribute("book", book);
        return "success";
    }

}
