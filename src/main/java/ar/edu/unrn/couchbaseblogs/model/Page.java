package ar.edu.unrn.couchbaseblogs.model;

import java.time.LocalDate;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;

@Document
@Builder
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Collection("pages")
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
