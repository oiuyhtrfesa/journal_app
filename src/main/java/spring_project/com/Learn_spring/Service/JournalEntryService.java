package spring_project.com.Learn_spring.Service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import spring_project.com.Learn_spring.Entry.JournalEntry;
import spring_project.com.Learn_spring.Entry.UserEntry;
import spring_project.com.Learn_spring.Repo.JournalEntryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class JournalEntryService {
    @Autowired
    JournalEntryRepository journalEntryRepository;
    @Autowired
    UserService userService;

    @Transactional
    public boolean saveEntry(JournalEntry journalEntry, String name) {
        UserEntry userEntry = userService.findUserByName(name);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry journalEntry1 = journalEntryRepository.save(journalEntry);
        userEntry.getJournalEntryList().add(journalEntry1);
        return userService.saveUser(userEntry);
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getSpecificId(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public void deleteById(ObjectId id, String username) {
        UserEntry userEntry = userService.findUserByName(username);
        boolean boo = userEntry.getJournalEntryList().removeIf(x -> x.getId().equals(id));
        if (boo) {
            userService.saveUser(userEntry);
            journalEntryRepository.deleteById(id);
            log.error(String.valueOf(getSpecificId(id)));
        }
    }
    public void saveEntry(JournalEntry journalEntry)
    {
        journalEntryRepository.save(journalEntry);
    }
}

//controller-->service-->repository
