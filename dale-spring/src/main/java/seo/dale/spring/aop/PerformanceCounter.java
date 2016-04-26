package seo.dale.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Aspect
public class PerformanceCounter {

    private static final AtomicInteger counter = new AtomicInteger();

    @Pointcut("execution(** seo.dale.spring.aop.Performance.perform(..))")
    public void perform() {
    }

    @Before("perform()")
    public void before() {
        int cnt = counter.incrementAndGet();
        System.out.println("- Count : " + cnt);
    }

    public int count() {
        return counter.get();
    }

}
