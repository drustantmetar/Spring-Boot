package com.journalapp.springsecurity.springsecurity.cache;

import com.journalapp.springsecurity.springsecurity.Model.ConfigJournalAppEntity;
import com.journalapp.springsecurity.springsecurity.Repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {


    // WEATHER_API_KEY - This is the stored database key value
    public enum keys
    {
        WEATHER_API_KEY
    }

    @Autowired
    ConfigJournalAppRepository configJournalAppRepository;
    
    public Map<String,String> Cache;


    @PostConstruct
    public void init() {
        Cache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        if (all != null && !all.isEmpty()) {
            for (ConfigJournalAppEntity entity : all) {
                if (entity.getKey() != null && entity.getValue() != null) {
                    Cache.put(entity.getKey(), entity.getValue());
                }
            }
            System.out.println("✅ Cache initialized with " + Cache.size() + " entries.");
        } else {
            System.out.println("⚠️ No config entries found in DB, cache not initialized.");
        }
    }

}
