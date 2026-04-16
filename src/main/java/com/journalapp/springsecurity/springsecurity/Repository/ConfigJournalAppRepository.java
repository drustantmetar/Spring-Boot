package com.journalapp.springsecurity.springsecurity.Repository;

import com.journalapp.springsecurity.springsecurity.Model.ConfigJournalAppEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity,String> {
}
