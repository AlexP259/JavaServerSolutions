package org.springmvc_hibernate.todo.service;


import org.springframework.data.repository.CrudRepository;
import org.springmvc_hibernate.todo.entity.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
