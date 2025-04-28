package ar.edu.unrn.couchbaseblogs.dto;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.PersistenceCreator;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorPostCount {
    private String id;
    private Integer count;
    private String author;

    @PersistenceCreator
    public AuthorPostCount(String id, Number count, String author) {
        this.id = id;
        this.count = count.intValue();
        this.author = author;
    }

}
