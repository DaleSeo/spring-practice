package seo.dale.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class PeformanceImpl implements Performance {

    @Override
    public void perform() {
        System.out.println("# perform #");
    }

}
