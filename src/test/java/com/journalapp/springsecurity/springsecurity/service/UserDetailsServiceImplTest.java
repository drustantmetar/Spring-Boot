package com.journalapp.springsecurity.springsecurity.service;

import com.journalapp.springsecurity.springsecurity.Model.users;
import com.journalapp.springsecurity.springsecurity.Repository.UserRepository;
import com.journalapp.springsecurity.springsecurity.Services.UserDetailsServiceImpls;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;

@Disabled
//@ActiveProfiles("dev") -> we can set too the below code will runs from which property files
@SpringBootTest //-> It load the whole application context
public class UserDetailsServiceImplTest {
    // It load all the application context classes it getting load in big applicaiton it does't actually use the mock so
    // we need to use the different Annotations for it
    @Autowired
    UserDetailsServiceImpls userDetailsServiceImpls;

    @MockitoBean
    UserRepository  userRepository;


//    @InjectMocks
//    private UserDetailsServiceImpls userDetailsServiceImpls;
//    @Mock
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Disabled
    @Test
    void loadUserDetailsServiceImplsTest()
    {
//        when(userRepository.findByUsername(ArgumentMatchers.anyString()))
//                .thenReturn(Optional.of(users.builder()
//                        .username("RAM")
//                        .password("Ram")
//                        .roles(List.of("Admin"))   // this is a List<String>
//                        .build()));
//                // Arrays.asList("Drustant") or List.of("Drustant")


        Assertions.assertNotNull(userDetailsServiceImpls.loadUserByUsername("RAM"));
    }

}
