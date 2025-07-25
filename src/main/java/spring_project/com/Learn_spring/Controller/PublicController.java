package spring_project.com.Learn_spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_project.com.Learn_spring.Entry.UserEntry;
import spring_project.com.Learn_spring.Service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserService userService;
    @PostMapping
    public void saveUser(@RequestBody UserEntry userEntry)
    {
        userService.saveNewUser(userEntry);
    }
    @GetMapping("getall")
    public ResponseEntity<?> getAllUsers()
    {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
