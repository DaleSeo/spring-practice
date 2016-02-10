package seo.dale.spring.todo.controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import seo.dale.spring.TestUtils;
import seo.dale.spring.todo.ToDoTestConfig;
import seo.dale.spring.todo.model.ToDo;
import seo.dale.spring.todo.service.ToDoService;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ToDoTestConfig.class)
@WebAppConfiguration
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("/dbunit/todo/DatabaseSetup.xml")
@TestPropertySource("classpath:application.test.properties")
@Transactional
public class ToDoControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUP() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/toDo")
                .accept(TestUtils.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtils.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("EBS radio")))
                .andExpect(jsonPath("$[0].description", is("Aired at 7 AM and 5 PM.")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Arirang news")))
                .andExpect(jsonPath("$[1].description", is("Watch every morning.")));
    }

    @Test
    public void testFindById() throws Exception {
        mockMvc.perform(get("/toDo/{id}", 1)
                        .accept(TestUtils.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtils.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("EBS radio")))
                .andExpect(jsonPath("$.description", is("Aired at 7 AM and 5 PM.")));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/toDo/{id}", 1)
                        .accept(TestUtils.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtils.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("EBS radio")))
                .andExpect(jsonPath("$.description", is("Aired at 7 AM and 5 PM.")));
    }


    @Test
    public void testAdd() throws Exception {
        String title = "title1";
        String description = "description1";

        ToDo toAdd = new ToDo.Builder(title).description(description).build();
        mockMvc.perform(post("/toDo")
                        .accept(TestUtils.APPLICATION_JSON_UTF8)
                        .contentType(TestUtils.APPLICATION_JSON_UTF8)
                        .content(TestUtils.convertObjectToJsonBytes(toAdd))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtils.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.title", is(title)))
                .andExpect(jsonPath("$.description", is(description)));
    }

    @Test
    public void testUpdate() throws Exception {
        String title = "Updated Title";
        String description = "Updated Description";

        ToDo toUpdate = new ToDo.Builder(title).description(description).build();
        mockMvc.perform(put("/toDo/{id}", 1)
                        .accept(TestUtils.APPLICATION_JSON_UTF8)
                        .contentType(TestUtils.APPLICATION_JSON_UTF8)
                        .content(TestUtils.convertObjectToJsonBytes(toUpdate))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtils.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(title)))
                .andExpect(jsonPath("$.description", is(description)));
    }

}