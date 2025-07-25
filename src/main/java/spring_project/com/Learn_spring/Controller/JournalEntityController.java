package spring_project.com.Learn_spring.Controller;

import org.springframework.web.bind.annotation.*;
import spring_project.com.Learn_spring.Entry.JournalEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntityController {
    //private Map<Long, JournalEntry> entryMap=new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAllJournals()
//    {
//        return new ArrayList<>(entryMap.values());
//    }
//    @GetMapping("{id}")
//    public JournalEntry getSpecificId(@PathVariable Long id)
//    {
//        return entryMap.get(id);
//    }
//    @PostMapping
//    public boolean SetEntity(@RequestBody JournalEntry entity)
//    {
//        entryMap.put(entity.getId(),entity);
//        return true;
//    }
//    @PutMapping("{id}")
//    public JournalEntry changeEntity(@PathVariable Long id,@RequestBody JournalEntry entity)
//    {
//        entryMap.put(id,entity);
//        return  entryMap.get(id);
//    }
//    @DeleteMapping("{id}")
//    public JournalEntry deleteEntry(@PathVariable Long id)
//    {
//        return entryMap.remove(id);
//    }
}
