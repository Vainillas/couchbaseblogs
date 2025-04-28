package ar.edu.unrn.couchbaseblogs.dto;

import lombok.*;
import org.springframework.data.annotation.PersistenceCreator;

@Getter
@Setter
@Builder
public class AuthorPostCount {
    private Integer count;
    private String author;

    @PersistenceCreator
    public AuthorPostCount(Number count, String author) {
        this.count = count.intValue();
        this.author = author;
    }

}
