package spring_project.com.Learn_spring.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import spring_project.com.Learn_spring.Entry.UserEntry;
import spring_project.com.Learn_spring.Repo.UserRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
class CustomUserDetailsServiceTest {
    @InjectMocks
    CustomUserDetailsService customUserDetailsService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(UserEntry.builder().userName("Anu").password("Anu").roles(new ArrayList<>()).build());
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("Anu");
        Assertions.assertNotNull(userDetails);

    }
}