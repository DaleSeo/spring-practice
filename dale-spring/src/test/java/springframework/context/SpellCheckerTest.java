package springframework.context;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class SpellCheckerTest {

    @Test
    public void testWithoutDependencyInjection() {
        Dictionary dictionary = new LiquorDictionary();
        SpellChecker checker = new SpellChecker(dictionary);
        List<Integer> indices = checker.checkDocument(Arrays.asList("Beer", "Soju", "Wine", "Makkolli", "Whiskey"));
        assertEquals(Arrays.asList(1, 3), indices);
    }

    @Test
    public void testWithAnnotationConfig() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpellCheckerConfig.class);
        SpellChecker checker = context.getBean(SpellChecker.class);
        List<Integer> indices = checker.checkDocument(Arrays.asList("Beer", "Soju", "Wine", "Makkolli", "Whiskey"));
        assertEquals(Arrays.asList(1, 3), indices);
    }

    @Test
    public void testWithXmlConfig() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context/spellCheckerContext.xml");
        SpellChecker checker = context.getBean(SpellChecker.class);
        List<Integer> indices = checker.checkDocument(Arrays.asList("Beer", "Soju", "Wine", "Makkolli", "Whiskey"));
        assertEquals(Arrays.asList(1, 3), indices);
    }

}
