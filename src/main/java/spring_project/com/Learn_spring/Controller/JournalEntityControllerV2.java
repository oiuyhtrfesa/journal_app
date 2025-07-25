package spring_project.com.Learn_spring.Controller;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import spring_project.com.Learn_spring.Entry.JournalEntry;
import spring_project.com.Learn_spring.Entry.UserEntry;
import spring_project.com.Learn_spring.Service.JournalEntryService;
import spring_project.com.Learn_spring.Service.UserService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/journal")
public class JournalEntityControllerV2 {
    @Autowired
    JournalEntryService journalEntryService;
    @Autowired
    UserService userService;
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getJournalById(@PathVariable ObjectId id)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        UserEntry userEntry=userService.findUserByName(name);
        List<JournalEntry> journalEntries=userEntry.getJournalEntryList().stream().filter(x->x.getId().equals(id)).toList();
        if(!journalEntries.isEmpty())
        {
        Optional<JournalEntry> journalEntry=journalEntryService.getSpecificId(id);
        if(journalEntry.isPresent())
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournalsOfAUser()
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        UserEntry userEntry=userService.findUserByName(name);
        if(userEntry!=null) {
            List<JournalEntry> all = userEntry.getJournalEntryList();
            if (all != null && !all.isEmpty())
                return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> SetEntity(@RequestBody JournalEntry entity)
    {
        try{
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String name=authentication.getName();
            entity.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(entity,name);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<?> changeEntity(@RequestBody JournalEntry entity,@PathVariable ObjectId id)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String  name=authentication.getName();
        UserEntry userEntry=userService.findUserByName(name);
        List<JournalEntry> journalEntries=userEntry.getJournalEntryList().stream().filter(x->x.getId().equals(id)).toList();
        if(!journalEntries.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.getSpecificId(id);
            if(journalEntry.isPresent()) {
                JournalEntry journalEntry1=journalEntry.get();
                journalEntry1.setTitle(entity.getTitle() != null && !entity.getTitle().equals("") ? entity.getTitle() : journalEntry1.getTitle());
                journalEntry1.setContent(entity.getContent() != null && !entity.getContent().equals("") ? entity.getContent() : journalEntry1.getContent());
                journalEntryService.saveEntry(journalEntry1);
                return new ResponseEntity<>(journalEntry1,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        journalEntryService.deleteById(id,name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
