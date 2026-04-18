package com.journalapp.springsecurity.springsecurity.service;

import com.journalapp.springsecurity.springsecurity.Services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JavaMailSendtest {

    @Autowired
    EmailService emailService;

    @Test
    public void mailTest()
    {
        emailService.sendMail("drustant.metar@gmail.com","Mail From Java Code","This is mail from Java Spring boot" +
                "application");
    }
}
