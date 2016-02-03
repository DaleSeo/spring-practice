package seo.dale.spring.todo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seo.dale.spring.todo.exception.ToDoNotFoundException;
import seo.dale.spring.todo.model.ToDo;
import seo.dale.spring.todo.repository.ToDoRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ToDoJpaService implements ToDoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoJpaService.class);

    @Autowired
    private ToDoRepository repository;

    @Transactional
    @Override
    public ToDo add(ToDo added) {
        LOGGER.debug("Adding a new to-do entry with information: {}", added);

        return repository.save(added);
    }

    @Transactional(rollbackFor = ToDoNotFoundException.class)
    @Override
    public ToDo deleteById(Long id) throws ToDoNotFoundException {
        LOGGER.debug("Deleting a to-do entry with id: {}", id);
        ToDo deleted = findById(id);
        LOGGER.debug("Deleting to-do entry: {}", deleted);
        repository.delete(deleted);
        return deleted;
    }

    @Override
    public List<ToDo> findAll() {
        LOGGER.debug("Finding all to-do entries");
        return repository.findAll();
    }

    @Transactional(readOnly = true, rollbackFor = ToDoNotFoundException.class)
    @Override
    public ToDo findById(Long id) throws ToDoNotFoundException {
        LOGGER.debug("Finding a to-do entry with id: {}", id);
        ToDo found = repository.findOne(id);
        LOGGER.debug("Found to-do entry: {}", found);
        if (found == null) {
            throw new ToDoNotFoundException("No to-do entry fond with id: " + id);
        }
        return found;
    }

    @Transactional(rollbackFor = ToDoNotFoundException.class)
    @Override
    public ToDo update(ToDo updated) throws ToDoNotFoundException {
        LOGGER.debug("Updating contact with information: {}", updated);
        ToDo model = findById(updated.getId());
        LOGGER.debug("Found a to-do entry: {}", model);
        model.update(updated.getTitle(), updated.getDescription());
        LOGGER.debug("Updating to-do entry: {}", model);
        return model;
    }

}
