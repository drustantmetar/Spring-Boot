package com.journalapp.springsecurity.springsecurity.Repository;

import com.journalapp.springsecurity.springsecurity.Model.journals;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<journals,String> {


}
