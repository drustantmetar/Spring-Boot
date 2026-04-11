package com.journalapp.springsecurity.springsecurity.Services;

import com.journalapp.springsecurity.springsecurity.Model.users;
import com.journalapp.springsecurity.springsecurity.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
// UserDetailsService - is a in-built method
@Service
public class UserDetailsServiceImpls implements UserDetailsService {

    private final UserRepository userRepository;

    // Constructor calling the UserRepository
    public UserDetailsServiceImpls(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


     // This class initially used for the basic authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Spring Security is trying to authenticate user: " + username);
        users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User   // User is inbuild class
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().toArray(new String[0]))
                .build();

//         .auth(user.getRoles().toArray(new String[0]))
//        - Role mismatch
//                - You’re using .roles(...). Spring automatically prefixes with ROLE_.
//                - If your DB already stores "ROLE_USER", Spring will make it "ROLE_ROLE_USER".
//                - Fix: store "USER" in MongoDB, not "ROLE_USER". Or switch to .authorities(...).

    }

}