package seo.dale.spring.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AopConfig.class)
public class RepeaterTest {

    @Autowired
    private Repeater repeater;

    @Autowired
    private PerformanceCounter performanceCounter;

    @Test
    public void testRepeat() throws Exception {
        repeater.repeat(5);
        assertEquals(5, performanceCounter.count());
    }

}