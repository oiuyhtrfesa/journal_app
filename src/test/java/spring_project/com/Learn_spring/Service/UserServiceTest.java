package spring_project.com.Learn_spring.Service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ArgumentsSources;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring_project.com.Learn_spring.Entry.UserEntry;
import spring_project.com.Learn_spring.Repo.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @ParameterizedTest
    @ValueSource(strings = {//if integer ints={}
            "Anu",
            "Gani",
            "Vai"
    })
    void findUserByName(String name) {
        assertNotNull(5);
        UserEntry userEntry=userRepository.findByUserName(name);assertNotNull(userEntry);
    }


    @ParameterizedTest
    @CsvSource({
            "1,2,3"
    })
    void test(int a,int b,int expected)
    {
        assertEquals(expected,a+b);
    }

    @ParameterizedTest
    @ArgumentsSource(UserArguments.class)
    void testNewUser(UserEntry userEntry){
        userService.saveNewUser(userEntry);
    }
}