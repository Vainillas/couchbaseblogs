package ar.edu.unrn.couchbaseblogs.model;

import java.time.LocalDate;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Page {
    @Id
    private String id;
    private String title;
    private String author;
    private String text;
    private LocalDate date;

}
