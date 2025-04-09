package ar.edu.unrn.couchbaseblogs.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorPostCount {
    private String author;
    private Integer count;
}
