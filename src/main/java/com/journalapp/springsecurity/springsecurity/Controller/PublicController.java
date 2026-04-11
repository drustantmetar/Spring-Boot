package com.journalapp.springsecurity.springsecurity.Controller;

import com.journalapp.springsecurity.springsecurity.Model.users;
import com.journalapp.springsecurity.springsecurity.Services.UserDetailsServiceImpls;
import com.journalapp.springsecurity.springsecurity.Services.UserService;
import com.journalapp.springsecurity.springsecurity.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.slf4j.LoggerFactory.getLogger;

@RequestMapping("/public")
@RestController
@Slf4j
public class PublicController {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsServiceImpls  userDetailsServiceImpls;

    // In built class we can used because of the dependencies
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil  jwtUtil;

    // Logger
   private static final Logger logger = getLogger(String.valueOf(PublicController.class));

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody users user) {

        try
        {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveData(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e)
        {

            log.warn("The username should be unique : {} warn",user.getUsername(),e);
            log.info("The username should be unique : {} info",user.getUsername(),e);
            log.error("The username should be unique : {} error ",user.getUsername(),e);
            log.debug("The username should be unique : {} debug",user.getUsername(),e);
            log.trace("The username should be unique : {} trace",user.getUsername(),e);

//            logger.warn("The username should be unique : {} - Logger warn ",user.getUsername(),e);
//            logger.info("The username should be unique : {} - Logger info",user.getUsername(),e);
//            logger.error("The username should be unique : {} - Logger error",user.getUsername(),e);
//            logger.debug("The username should be unique : {} - Logger debug ",user.getUsername(),e);
//            logger.trace("The username should be unique : {} - Logger trace ",user.getUsername(),e);
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody users user) {

        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            // UserDetails - is in built class
            UserDetails userDetails = userDetailsServiceImpls.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt,HttpStatus.OK);
        }
        catch(Exception e)
        {
            log.error("Error occurred while generating the Authentication Token"+e);
            return new ResponseEntity<>("Incorrect Username & Password",HttpStatus.UNAUTHORIZED);
        }

    }

}
