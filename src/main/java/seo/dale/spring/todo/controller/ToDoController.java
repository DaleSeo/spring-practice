package seo.dale.spring.todo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import seo.dale.spring.todo.exception.ToDoNotFoundException;
import seo.dale.spring.todo.model.ToDo;
import seo.dale.spring.todo.service.ToDoService;

import java.util.List;

@RestController
@RequestMapping("/toDo")
public class ToDoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoController.class);

    @Autowired
    private ToDoService service;

    @RequestMapping(method = RequestMethod.POST)
    public ToDo add(@RequestBody @Validated ToDo toAdd) {
        LOGGER.debug("Adding a new to-do entry with information: {}", toAdd);
        ToDo added = service.add(toAdd);
        LOGGER.debug("Added a to-do entry with information: {}", added);
        return added;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ToDo deleteById(@PathVariable Long id) throws ToDoNotFoundException {
        LOGGER.debug("Deleting a to-do entry with id: {}", id);
        ToDo deleted = service.deleteById(id);
        LOGGER.debug("Deleted to-do entry with information: {}", deleted);
        return deleted;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ToDo> findAll() {
        LOGGER.debug("Finding all to-do entries.");
        List<ToDo> foundList = service.findAll();
        LOGGER.debug("Found {} to-do entries.", foundList.size());
        return foundList;
    }

}
