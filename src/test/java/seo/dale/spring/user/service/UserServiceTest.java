package seo.dale.spring.user.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.util.ReflectionTestUtils;
import seo.dale.spring.PersistenceConfig;
import seo.dale.spring.user.model.User;
import seo.dale.spring.user.repository.UserRepository;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("/dbunit/user/DatabaseSetup.xml")
public class UserServiceTest {

    private UserServiceImpl service = new UserServiceImpl();

    @Autowired
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        ReflectionTestUtils.setField(service, "repository", repository);
    }

    @Test
    public void testFindAll() throws Exception {
        List<User> listed = service.findAll();
        System.out.println("# LISTED : " + listed);
        assertEquals(3, listed.size());
    }

    @Test
    public void testCreate() throws Exception {
        User toCreate = new User.Builder("admin", "admin1!").firstName("관리자").email("admin@dale.seo").build();
        User created = service.create(toCreate);
        System.out.println("# CREATED : " + created);
        assertEquals("admin@dale.seo", created.getEmail());
    }

    @Test
    public void testFindById() throws Exception {
        User found = service.findById(2L);
        System.out.println("# FOUND : " + found);
        assertEquals("Kate", found.getFirstName());
    }

    @Test
    public void testRemoveById() throws Exception {
        User removed = service.removeById(2L);
        System.out.println("# REMOVED : " + removed);
        assertNotNull(removed);
    }

    @Test
    public void testModify() throws Exception {
        User toModify = new User.Builder("kimrla38", "kate2637").firstName("Taylor").lastName("Swift").email("taylor@swift.us").build();
        toModify.setId(3L);
        User modified = service.modify(toModify);
        System.out.println("# Modified : " + modified);
        assertEquals("Taylor", modified.getFirstName());
    }

}