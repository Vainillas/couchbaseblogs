package ar.edu.unrn.couchbaseblogs.model;


import java.time.LocalDate;
import java.util.List;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;


@Document
@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Collection("posts")
public class Post {
    @Id
    private String id;
    @Field
    private String title;
    @Field
    private String author;
    @Field
    private String text;
    @Field
    private List<String> tags;
    @Field
    private String resume;
    @Field
    private List<String> relatedLinks;
    @Field
    private LocalDate date;
}
