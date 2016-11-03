package seo.dale.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RepeaterAspect {

    @Pointcut("execution(** seo.dale.spring.aop.Repeater.repeat(int))")
    public void repeat() {}

    @Before("repeat()")
    public void before() {
        System.out.println("@Before");
    }

    @After("repeat()")
    public void after() {
        System.out.println("@After");
    }

    @AfterReturning("repeat()")
    public void afterReturning() {
        System.out.println("@AfterReturning");
    }

    @AfterThrowing("repeat()")
    public void afterThrowing() {
        System.out.println("@AfterThrowing");
    }

    @Around("repeat()")
    public void around(ProceedingJoinPoint jp) {
        try {
            System.out.println("@Around - Before");
            jp.proceed();
            System.out.println("@Around - After Returning");
        } catch (Throwable throwable) {
            System.out.println("@Around - After Throwing");
        } finally {
            System.out.println("@Around - After");
        }
    }

}
