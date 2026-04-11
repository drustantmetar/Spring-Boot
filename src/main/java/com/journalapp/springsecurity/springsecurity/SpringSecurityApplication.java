package com.journalapp.springsecurity.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
//        ApplicationContext context = SpringApplication.run(SpringSecurityApplication.class, args);
//        System.out.println(context.getEnvironment().getProperty("spring.profiles.active"));
    }

}