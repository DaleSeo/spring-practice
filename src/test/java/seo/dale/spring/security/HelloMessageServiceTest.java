package seo.dale.spring.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HelloMessageServiceTest.TestConfiguration.class)
public class HelloMessageServiceTest {

    @Autowired
    private HelloMessageService messageService;

    @Test
    public void testGetMessage() {
        String msg = messageService.getMessage();
        System.out.println("##### " + msg);
    }

    @Configuration
    @ComponentScan("seo.dale.spring.security")
    public static class TestConfiguration {
    }

}