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
//        query.addCriteria(Criteria.where("email").exists(true).ne(null).ne(""));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,10}$\n"));
//        query.addCriteria(Criteria.where("age").gte(20));
//        query.addCriteria(Criteria.where("roles").in("ADMIN","USERS"));

//        Criteria criteria = new  Criteria();
//        query.addCriteria(criteria.andOperator(Criteria.where("email").exists(true),Criteria.where("sentimentAnalysis").is(false)));

        List<users> userDetails = mongoTemplate.find(query, users.class);
        return userDetails;
    }
}
