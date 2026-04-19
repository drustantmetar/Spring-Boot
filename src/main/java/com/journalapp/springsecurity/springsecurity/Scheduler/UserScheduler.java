package com.journalapp.springsecurity.springsecurity.Scheduler;

import com.journalapp.springsecurity.springsecurity.Model.journals;
import com.journalapp.springsecurity.springsecurity.Model.users;
import com.journalapp.springsecurity.springsecurity.Repository.mongoTempleteImpl;
import com.journalapp.springsecurity.springsecurity.Services.EmailService;
import com.journalapp.springsecurity.springsecurity.Services.SentimentAnalysisService;
import com.journalapp.springsecurity.springsecurity.cache.AppCache;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    EmailService  emailService;

    @Autowired
    mongoTempleteImpl mongoTemplete;

    @Autowired
    SentimentAnalysisService  sentimentAnalysisService;

    @Autowired
    AppCache appCache;

    @Scheduled(cron = "0 18 11 ? * SUN")
    public void fetchUserAnsSendSaMail()
    {
        List<users> allUsers = mongoTemplete.getDetails();
        for(users user : allUsers)
        {
            List<journals> jounralList = user.getJounralList();
            List<String> filteredJournalEntries = jounralList.stream().filter(x -> x.getDate().isBefore(LocalDateTime.now())).map(x -> x.getTitle()).collect(Collectors.toList());
            String combineEntries = String.join(", ", filteredJournalEntries);
            System.out.println("combineEntries "+combineEntries);
            String sentiment = sentimentAnalysisService.getSentiment(combineEntries);
            System.out.println("The value of the sentiment is "+sentiment);
            emailService.sendMail(user.getEmail(),"Sentiment Trigger for last 50 Seconds",sentiment);

        }

    }

    // the app cache will reload every 10 minutes
    @Scheduled(cron = "0 0/10 * 1/1 * ?")
    public void reloadCache()
    {
        appCache.init();
    }
}
