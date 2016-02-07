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

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ToDoTestConfig.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@WebAppConfiguration
@DatabaseSetup("/dbunit/todo/DatabaseSetup.xml")
@TestPropertySource("classpath:application.test.properties")
public class ToDoControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUP() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testAdd() throws Exception {
        // Given
        String title = "title1";
        String description = "description1";

        ToDo toAdd = new ToDo.Builder(title).description(description).build();
        mockMvc.perform(
                // When
                post("/toDo")
                .contentType(TestUtils.APPLICATION_JSON_UTF8)
                .accept(TestUtils.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(toAdd))
        )
                // Then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtils.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(title)))
                .andExpect(jsonPath("$.description", is(description)));
    }



}