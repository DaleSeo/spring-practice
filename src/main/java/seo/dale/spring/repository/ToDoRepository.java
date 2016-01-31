package seo.dale.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seo.dale.spring.model.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
