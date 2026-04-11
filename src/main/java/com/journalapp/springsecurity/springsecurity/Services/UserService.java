package com.journalapp.springsecurity.springsecurity.Services;

import com.journalapp.springsecurity.springsecurity.Model.users;
import com.journalapp.springsecurity.springsecurity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void saveData(users user) {
        userRepository.save(user);
    }

    public List<users> findAll() {
        return userRepository.findAll();
    }

    public Optional<users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public users deleteById(String id)
    {
         userRepository.deleteById(id);

        return null;
    }

}
