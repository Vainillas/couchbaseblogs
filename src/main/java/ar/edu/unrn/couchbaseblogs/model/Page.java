package ar.edu.unrn.couchbaseblogs.model;

import java.time.LocalDate;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Page {
    @Id
    private String id;
    @Field
    private String title;
    @Field
    private String author;
    @Field
    private String text;
    @Field
    private LocalDate date;

}
