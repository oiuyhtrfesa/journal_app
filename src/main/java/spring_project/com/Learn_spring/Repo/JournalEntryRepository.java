package spring_project.com.Learn_spring.Repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import spring_project.com.Learn_spring.Entry.JournalEntry;
import spring_project.com.Learn_spring.Entry.UserEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
}
