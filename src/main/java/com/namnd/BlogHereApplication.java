package com.namnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BlogHereApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogHereApplication.class, args);
    }

}
