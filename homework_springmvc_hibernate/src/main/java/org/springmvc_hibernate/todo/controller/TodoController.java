package org.springmvc_hibernate.todo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springmvc_hibernate.todo.entity.Todo;
import org.springmvc_hibernate.todo.service.TodoService;

import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping("/home")
    public String home(HttpSession session){
        List<Todo> todoList = todoService.findAll();
        session.setAttribute("todoList", todoList);
        return "home";
    }

    @RequestMapping("/new")
    public String showTodo(){
        return "new_todo";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveTodo(@ModelAttribute("todo") Todo todo, HttpSession session){
        todoService.save(todo);
        session.setAttribute("msg", "Дело добавлено успешно");
        return "redirect:/home";
    }

    @RequestMapping("/edit/{id}")
    public String showTodo(@PathVariable("id") int id, Model m){
        Todo todo = todoService.getTodoById(id);
        m.addAttribute("editTodo", todo);
        return "edit_todo";
    }

    @RequestMapping(value = "/updateTodo", method = RequestMethod.POST)
    public String updateTodo(@ModelAttribute("todo") Todo todo, @RequestParam("id") long id, HttpSession session){
        todoService.update(todo, id);
        session.setAttribute("msg", "Дело обновлено успешно");
        return "redirect:/home";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") long id, HttpSession session){
        todoService.deleteTodoById(id);
        session.setAttribute("msg", "Дело удалено успешно");
        return "redirect:/home";
    }

}














