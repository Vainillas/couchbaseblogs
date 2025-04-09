package ar.edu.unrn.couchbaseblogs.model;


import java.time.LocalDate;
import java.util.List;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    private String id;
    private String title;
    private String author;
    private String text;
    private List<String> tags;
    private String resume;
    private List<String> relatedLinks;
    private LocalDate date;
}
