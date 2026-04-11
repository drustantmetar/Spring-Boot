package com.journalapp.springsecurity.springsecurity.Services;

import com.journalapp.springsecurity.springsecurity.Model.journals;
import com.journalapp.springsecurity.springsecurity.Model.users;
import com.journalapp.springsecurity.springsecurity.Repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {
    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private UserService userService;

    public void SaveData(journals journals,String username){
        Optional<users> user = userService.findByUsername(username);
       journals journal = journalRepository.save(journals);
        user.get().jounralList.add(journal);
        userService.saveData(user.orElse(null));
    }

    public List<journals> getData(){
        return journalRepository.findAll();
    }

    public void DeleteData(String username,String id)
    {
        Optional<users> user = userService.findByUsername(username);
        user.get().jounralList.removeIf(j -> j.getId().equals(id));
        userService.saveData(user.orElse(null));
        journalRepository.deleteById(id);
    }

    public void deleteById(String id)
    {
        journalRepository.deleteById(id);
    }

    public void findById(String id)
    {
        journalRepository.findById(id);
    }

}
