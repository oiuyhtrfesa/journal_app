package spring_project.com.Learn_spring.Repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import spring_project.com.Learn_spring.Entry.UserEntry;

public interface UserRepository extends MongoRepository<UserEntry, ObjectId> {
    UserEntry findByUserName(String name);
    void deleteByUserName(String name);
}
