package seo.dale.spring.core.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import seo.dale.spring.todo.model.ToDo;
import seo.dale.spring.todo.repository.ToDoRepository;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ToDoRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        LOGGER.debug("loading data onto the database.");

        ToDo toDo1 = new ToDo();
        toDo1.setTitle("Listen to the EBS radio show.");
        toDo1.setDescription("Aired at 7 AM and 5 PM");
        repository.save(toDo1);

        ToDo toDo2 = new ToDo();
        toDo2.setTitle("Watch the Arirang news");
        toDo2.setDescription("Every morning");
        repository.save(toDo2);
    }

}
