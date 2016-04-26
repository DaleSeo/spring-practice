package seo.dale.spring.todo.service;

import seo.dale.spring.todo.exception.ToDoNotFoundException;
import seo.dale.spring.todo.model.ToDo;

import java.util.List;

public interface ToDoService {

    ToDo add(ToDo added);

    ToDo deleteById(Long id) throws ToDoNotFoundException;

    List<ToDo> findAll();

    ToDo findById(Long id) throws ToDoNotFoundException;

    ToDo update(ToDo updated) throws ToDoNotFoundException;

}
