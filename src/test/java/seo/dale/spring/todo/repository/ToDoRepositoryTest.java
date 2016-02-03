package seo.dale.spring.todo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import seo.dale.spring.PersistenceConfig;
import seo.dale.spring.todo.model.ToDo;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
public class ToDoRepositoryTest {

    @Autowired
    private ToDoRepository repository;

    @Test
    public void test() {
        ToDo added = new ToDo();
        added.setTitle("Title 1");
        added.setDescription("Description 1");

        System.out.println("# INSERT");
        repository.save(added); // INSERT SQL

        System.out.println("# SELECT ONE");
        ToDo found = repository.findOne(added.getId()); // SELECT SQL

        assertNotNull(added.getId());
        assertEquals(added.getTitle(), found.getTitle());
        assertEquals(added.getDescription(), found.getDescription());

        System.out.println("# SELECT ALL");
        List<ToDo> list1 = repository.findAll(); // SELECT SQL
        assertEquals(1, list1.size());

        System.out.println("# UPDATE");
        added.setTitle("Title 1-1"); // UPDATE SQL

        System.out.println("# DELETE");
        repository.delete(added); // DELETE SQL

        System.out.println("# SELECT ALL");
        List<ToDo> list2 = repository.findAll(); // SELECT SQL
        assertEquals(0, list2.size());
    }

}