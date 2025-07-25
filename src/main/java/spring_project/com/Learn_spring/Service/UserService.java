package spring_project.com.Learn_spring.Service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import spring_project.com.Learn_spring.Entry.UserEntry;
import spring_project.com.Learn_spring.Repo.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class UserService {
    @Autowired
    UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    private final Logger logger= LoggerFactory.getLogger(UserService.class);
    public void saveNewUser(UserEntry User) {
            User.setPassword(passwordEncoder.encode(User.getPassword()));
            User.setRoles(List.of("USERS"));
            userRepository.save(User);
    }
    public boolean saveUser(UserEntry user)
    {
        try {
            userRepository.save(user);
            return true;
        }
        catch (Exception e)
        {
//            logger.error("hhehhe");
//            logger.debug("hhehhe");
//            logger.warn("hhehhe");
//            logger.info("hhehhe");
//            logger.trace("hhehhe");
            return false;
        }

    }

    public List<UserEntry> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntry findUserByName(String name) {
        return userRepository.findByUserName(name);
    }

    public void saveAdmin(UserEntry userEntry) {
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(List.of("USERS","ADMIN"));
        userRepository.save(userEntry);
    }
}
//controller-->service-->repository
