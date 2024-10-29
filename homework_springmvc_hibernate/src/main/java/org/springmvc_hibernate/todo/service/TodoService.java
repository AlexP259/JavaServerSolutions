package org.springmvc_hibernate.todo.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc_hibernate.todo.entity.Todo;

import java.util.List;

@Service
@Transactional
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findAll(){
        return (List<Todo>) todoRepository.findAll();
    }

    public void save(Todo todo){
        todoRepository.save(todo);
    }

    public Todo getTodoById(long todoId){
        Todo todoDB = todoRepository.findById(todoId).get();
        return todoDB;
    }

    public Todo update(Todo todo, Long todoId){
        Todo todoDB = todoRepository.findById(todoId).get();
        todoDB.setTodo(todo.getTodo());
        return todoRepository.save(todoDB);
    }

    public void deleteTodoById(long todoId){
        todoRepository.deleteById(todoId);
    }

}
