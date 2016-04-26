package seo.dale.spring.core.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        if (applicationContext.getParent() != null) {
            return;
        }

        LOGGER.debug("loading data onto the database.");

        ToDo toDo1 = new ToDo.Builder("EBS radio").description("Aired at 7 AM and 5 PM.").build();
        repository.save(toDo1);

        ToDo toDo2 = new ToDo.Builder("Arirang news").description("Watch every morning.").build();
        repository.save(toDo2);
    }

}
