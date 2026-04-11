package com.journalapp.springsecurity.springsecurity.Controller;

import com.journalapp.springsecurity.springsecurity.Model.journals;
import com.journalapp.springsecurity.springsecurity.Model.users;
import com.journalapp.springsecurity.springsecurity.Repository.UserRepository;
import com.journalapp.springsecurity.springsecurity.Services.JournalService;
import com.journalapp.springsecurity.springsecurity.Services.UserService;
import com.journalapp.springsecurity.springsecurity.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
@Component
public class JournalController {
    @Autowired
    private JournalService journalService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/saveData")
    private ResponseEntity<?> saveData(@RequestBody journals journals)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userFound = auth.getName();

        journals.setDate(LocalDateTime.now());
        journalService.SaveData(journals, userFound);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getJournals")
    public List<journals> getJournals()
    {
        return journalService.getData();
    }

    @Autowired
    UserService userService;
    @GetMapping("/getJournalByUsername")
    public ResponseEntity<Object> getDataByUsername()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userFound = auth.getName();
        Optional<users> user = userService.findByUsername(userFound);
        if(user.get().getJounralList().isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(user.get().getJounralList(), HttpStatus.OK);
        }
    }

    @GetMapping("/GetByID/{id}")
    public ResponseEntity<?> getJournalById(@PathVariable String id)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userFound = auth.getName();
        Optional<users> user = userService.findByUsername(userFound);
        if(user.isPresent())
        {
            System.out.println("User present call getByID");
            List<journals> jounralList = user.get().getJounralList().stream().filter(j -> j.getId().equals(id)).collect(Collectors.toList());
            if(!jounralList.isEmpty())
            {
                System.out.println("check journal inside");
                return new ResponseEntity<>(jounralList,HttpStatus.OK);
            }
        }
         return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/DeleteByID/{id}")
    public ResponseEntity<?> deleteJournalById(@PathVariable String id)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth==null || !auth.isAuthenticated())
        {
            return new ResponseEntity<>("AUTHENTICATION FAILED",HttpStatus.UNAUTHORIZED);
        }

        String username = auth.getName();
        Optional<users> userFound = userService.findByUsername(username);
        if(!userFound.isPresent())
        {
            return new ResponseEntity<>("USER NOT FOUND",HttpStatus.NOT_FOUND);
        }
        // Get all the present user details
        users user = userFound.get();
        boolean userResult = user.getJounralList().removeIf(x -> x.getId().equals(id));
        if(userResult)
        {
            userService.saveData(user);
            journalService.deleteById(id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateJournalEntries/{id}")
    public ResponseEntity<?> updateJournalEntries(@RequestBody journals newEntry,@PathVariable String id)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth==null || !auth.isAuthenticated())
        {
            return new ResponseEntity<>("AUTHENTICATION FAILED",HttpStatus.UNAUTHORIZED);
        }

        String username = auth.getName();
        Optional<users> userFound = userService.findByUsername(username);
        if(!userFound.isPresent())
        {
            return new ResponseEntity<>("USER NOT FOUND",HttpStatus.NOT_FOUND);
        }

        users user = userFound.get();
        List<journals> oldJournalList  = user.getJounralList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());

        if(oldJournalList.isEmpty())
        {
            return new ResponseEntity<>("Journal Not Found ",HttpStatus.NOT_FOUND);
        }
        journals oldEntry = oldJournalList.get(0);
        oldEntry.setDate(LocalDateTime.now());
        oldEntry.setTitle(newEntry.getTitle());
        oldEntry.setContent(newEntry.getContent());

        journalService.SaveData(oldEntry,username);
        userService.saveData(user);

       return new ResponseEntity<>(HttpStatus.OK);
    }

}