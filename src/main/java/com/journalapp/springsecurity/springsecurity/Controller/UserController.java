package com.journalapp.springsecurity.springsecurity.Controller;

import com.journalapp.springsecurity.springsecurity.Model.users;
import com.journalapp.springsecurity.springsecurity.Repository.UserRepository;
import com.journalapp.springsecurity.springsecurity.Repository.mongoTempleteImpl;
import com.journalapp.springsecurity.springsecurity.Services.MarketDataAnalysisAPICall;
import com.journalapp.springsecurity.springsecurity.Services.UserService;
import com.journalapp.springsecurity.springsecurity.Services.WeatherApiCall;
import com.journalapp.springsecurity.springsecurity.api.response.WeatherResponse;
import com.journalapp.springsecurity.springsecurity.cache.AppCache;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepository;

    @Autowired
    MarketDataAnalysisAPICall  marketDataAnalysisAPICall;

    @Autowired
    WeatherApiCall   weatherApiCall;

    @Autowired
    AppCache appCache;

    @Autowired
    mongoTempleteImpl mongoTempleteImpl1;


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

    @GetMapping("/check-weather")
    public ResponseEntity<Object> getWaterinfo()
    {
        try
        {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            auth.getName();
            return new ResponseEntity<>("Hi Weather "+marketDataAnalysisAPICall.getInfo(),HttpStatus.OK);
        }
        catch(Exception e)
        {
            log.error("The exception in weather APU {}",e.getMessage());
        }
        return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/weather/{City}")
    public ResponseEntity<Object> weatherResponse(@PathVariable String City)
    {
        WeatherResponse weatherResponse = weatherApiCall.WeatherService(City);
        return new ResponseEntity<>(weatherResponse,HttpStatus.OK);
    }

    // We can run this api after doing changes into the DB then we do not need to restart the whole application
    @GetMapping("/refresh/apis")
    public ResponseEntity<Object> refreshAPIs()
    {
        appCache.init();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getSentiment")
    private ResponseEntity<Object> getAnalysis()
    {
        return new ResponseEntity<>(mongoTempleteImpl1.getDetails(),HttpStatus.OK);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteById(@PathVariable String id)
//    {
//        userService.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}