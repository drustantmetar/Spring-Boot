package com.journalapp.springsecurity.springsecurity.Repository;

import com.journalapp.springsecurity.springsecurity.Model.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

// mongotTempleteImpl + UserController + ConfigJournalAppEntity + AppCache
@Component
public class mongoTempleteImpl {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<users> getDetails()
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").exists(true));
        query.addCriteria(Criteria.where("sentimentAnalysis").exists(true));
        List<users> users = mongoTemplate.find(query, users.class);
        return users;
    }
}
