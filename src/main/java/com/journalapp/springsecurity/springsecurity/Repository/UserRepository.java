package com.journalapp.springsecurity.springsecurity.Repository;

import com.journalapp.springsecurity.springsecurity.Model.users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<users, String> {
//    users findByUsername(String username);
    Optional<users> findByUsername(String username);

}
