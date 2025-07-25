package spring_project.com.Learn_spring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import spring_project.com.Learn_spring.Entry.UserEntry;
import spring_project.com.Learn_spring.Repo.UserRepository;
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntry byUserName = userRepository.findByUserName(username);
        if(byUserName!=null)
        {
            return User.builder()
                    .username(byUserName.getUserName())
                    .password(byUserName.getPassword())
                    .roles(byUserName.getRoles().toArray(new String[0]))
                    .build();
        }
        throw  new UsernameNotFoundException("User not found " + username);
    }
}
