package seo.dale.spring.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Repeater {

    @Autowired
    private Performance performance;

    public void repeat(int num) {
        for (int i = 0; i < num; i++) {
            performance.perform();
        }
    }

}
