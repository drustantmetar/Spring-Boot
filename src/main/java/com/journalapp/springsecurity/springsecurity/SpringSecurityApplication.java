package com.journalapp.springsecurity.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;;

@SpringBootApplication
@EnableScheduling
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
//        ApplicationContext context = SpringApplication.run(SpringSecurityApplication.class, args);
//        System.out.println(context.getEnvironment().getProperty("spring.profiles.active"));
    }


    // used this to get the response from the External API by using RestTemplete
    // Here this Bean is Initialize the RestTemplete / It'a an implementation

}