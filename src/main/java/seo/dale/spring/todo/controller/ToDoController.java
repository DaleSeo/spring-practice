package seo.dale.spring.todo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import seo.dale.spring.todo.model.ToDo;
import seo.dale.spring.todo.service.ToDoService;

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

}
