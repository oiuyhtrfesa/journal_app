package spring_project.com.Learn_spring.Entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "journal_entries")
public class JournalEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;
 }
