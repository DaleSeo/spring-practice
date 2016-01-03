package seo.dale.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class DaleSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaleSpringApplication.class, args);
    }
}
