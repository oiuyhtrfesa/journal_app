package spring_project.com.Learn_spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import spring_project.com.Learn_spring.Entry.UserEntry;
import spring_project.com.Learn_spring.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUSers()
    {
        List<UserEntry> userEntryList= userService.getAllUsers();
        if(userEntryList!=null && !userEntryList.isEmpty())
            return new ResponseEntity<>(userEntryList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody UserEntry userEntry)
    {
        userService.saveAdmin(userEntry);
    }
}
