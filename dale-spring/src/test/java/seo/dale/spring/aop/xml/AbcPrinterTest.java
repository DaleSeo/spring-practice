package seo.dale.spring.aop.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AbcPrinterTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/aop-context.xml");
        AbcPrinter printer = context.getBean(AbcPrinter.class);
        printer.print();
    }

}
