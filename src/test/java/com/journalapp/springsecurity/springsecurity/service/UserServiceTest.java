package com.journalapp.springsecurity.springsecurity.service;
import com.journalapp.springsecurity.springsecurity.Model.users;
import com.journalapp.springsecurity.springsecurity.Repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

// Runs the test cases / class / method by using Code for Coverage for test class reports

@SpringBootTest
public class UserServiceTest {



    @Autowired
    UserRepository userRepository;

    @Disabled
    @Test
    public void basicTest()
    {
        assertEquals(2, 2);
        assertEquals(2, 3-1);
    }
    @Disabled
    @Test
    public void Test2()
    {
//        assertEquals(210, 1-1);
        assertEquals(50, 25+25);
        assertTrue(10>=10);
    }

    @Disabled
    @Test
    public void testGetUsername()
    {
        assertNotNull(userRepository.findByUsername("Drustant"));
        Optional<users> user = userRepository.findByUsername("Drustant");
        assertTrue(user.isPresent());
        assertFalse(user.get().jounralList.isEmpty());
    }
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "Drustant",
            "Madhav",
            "Sharayu",
            "Shiv",
            "Kiran"
    })
    public void checkName(String name)
    {
        Optional<users> user = userRepository.findByUsername(name);
        assertNotNull(user.get().getJounralList());
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "20,20,40",
            "40,5,60"
    })
    public void paraTest(int a, int b ,int expected)
    {
        assertEquals(a+b,expected);
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "Drustant",
            "Sharayu",
            "shiv",
            "radhe"
    })
    public void checkUsername(String username)
    {
        Optional<users> user =  userRepository.findByUsername(username);
        assertTrue(user.isPresent());
    }
    // Provide the Argument provide by by JUnit Test
    // Such types of annotations are useful to perform some operation before and after testcases runs
    //    @BeforeAll
    //    @BeforeEach
    //    @AfterAll
    //    @AfterEach
}
