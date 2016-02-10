package seo.dale.spring.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seo.dale.spring.todo.model.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
