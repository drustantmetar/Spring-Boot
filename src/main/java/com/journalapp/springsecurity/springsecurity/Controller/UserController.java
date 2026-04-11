package com.journalapp.springsecurity.springsecurity.Controller;

import com.journalapp.springsecurity.springsecurity.Model.users;
import com.journalapp.springsecurity.springsecurity.Repository.UserRepository;
import com.journalapp.springsecurity.springsecurity.Services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

//@RequestMapping("/userController")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepository;


//    @PostMapping("/saveData")
//    public ResponseEntity<?> SaveDetails(@RequestBody users user)
//    {
//       userService.saveData(user);
//       return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody users user) {

//        System.out.println("Update details API calls 1 ");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("Update details API calls 2 ");
        String username = auth.getName(); // current logged-in user
        System.out.println("call from controller :: Update details API calls 3 "+username);
        Optional<users> myUserData = userService.findByUsername(username);
        if(myUserData.isPresent()) {
            System.out.println("condition checked use present :: "+myUserData);
            users myUser = myUserData.get(); // unwrap the Optional
            myUser.setUsername(user.getUsername());
            // Encode password before saving
            myUser.setPassword(passwordEncoder.encode(user.getPassword()));
            myUser.setRoles(user.getRoles());
            userService.saveData(myUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping
    public ResponseEntity<User> findByUsername(@RequestBody users user) {
        userService.saveData(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<users> getAllData()
    {
        return  userService.findAll();
    }

    @GetMapping("{username}")

    public ResponseEntity<Optional<users>> getByUserName(@PathVariable String username)
    {
         return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }

    @DeleteMapping("/deleteByUsername")
    public ResponseEntity<?> deleteByUsername()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userFound = auth.getName();
        Optional<users> userData=userRepository.findByUsername(userFound);
        if(userData.isPresent())
        {
            userRepository.delete(userData.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteById(@PathVariable String id)
//    {
//        userService.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}