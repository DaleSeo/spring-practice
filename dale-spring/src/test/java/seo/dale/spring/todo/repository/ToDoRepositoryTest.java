package seo.dale.spring.todo.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import seo.dale.spring.PersistenceConfig;
import seo.dale.spring.todo.model.ToDo;

import javax.transaction.Transactional;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("/dbunit/todo/DatabaseSetup.xml")
public class ToDoRepositoryTest {

    @Autowired
    private ToDoRepository repository;

    @Test
    public void testFindAll() {
        System.out.println("# SELECT ALL");
        List<ToDo> list = repository.findAll(); // SELECT SQL
        assertEquals(2, list.size());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/todo/ExpectedDatabase-add.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testSave() {
        ToDo added = new ToDo();
        added.setTitle("title3");
        added.setDescription("description3");
        System.out.println("# INSERT");
        repository.save(added); // INSERT SQL
    }


    @Ignore
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
        assertEquals(3, list1.size());

        System.out.println("# UPDATE");
        added.setTitle("Title 1-1"); // UPDATE SQL

        System.out.println("# DELETE");
        repository.delete(added); // DELETE SQL

        System.out.println("# SELECT ALL");
        List<ToDo> list2 = repository.findAll(); // SELECT SQL
        assertEquals(0, list2.size());
    }

}