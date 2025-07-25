package spring_project.com.Learn_spring.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import spring_project.com.Learn_spring.Entry.UserEntry;
import spring_project.com.Learn_spring.Repo.UserRepository;
import spring_project.com.Learn_spring.Service.UserService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserEntry userEntry)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        UserEntry userEntry1=userService.findUserByName(name);
        if(userEntry1!=null)
        {
            userEntry1.setUserName(userEntry.getUserName());
            userEntry1.setPassword(userEntry.getPassword());
            userService.saveNewUser(userEntry1);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUserByNAme()
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
