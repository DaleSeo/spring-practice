package seo.dale.spring.todo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import seo.dale.spring.todo.model.ToDo;
import seo.dale.spring.todo.repository.ToDoRepository;
import seo.dale.spring.todo.service.ToDoJpaService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ToDoJpaServiceTest {

    @InjectMocks
    private ToDoJpaService service;

    @Mock
    private ToDoRepository repository;

    @Test
    public void testAdd() throws Exception {
        ToDo toDo = new ToDo.Builder().title("title 1").description("description 1").build();

        when(repository.save(toDo)).thenReturn(toDo);

        service.add(toDo);

        verify(repository).save(toDo);
    }

    @Test
    public void testDeleteById() throws Exception {

    }

    @Test
    public void testFindAll() throws Exception {

    }

    @Test
    public void testFindById() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }
}