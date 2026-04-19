package com.journalapp.springsecurity.springsecurity.Services;

import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysisService {


    // It's a part of machine learning
    public String getSentiment(String text)
    {
        System.out.println("sentiment value from sentService "+text);
        return text;

    }
}
