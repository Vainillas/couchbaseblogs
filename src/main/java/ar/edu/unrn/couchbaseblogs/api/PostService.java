package ar.edu.unrn.couchbaseblogs.api;


import ar.edu.unrn.couchbaseblogs.dto.AuthorPostCount;
import ar.edu.unrn.couchbaseblogs.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getLatest4Posts();

    List<Post> getPostsByAuthor(String nombreautor);

    List<Post> searchPosts(String text);

    AuthorPostCount getPostCounts(String nombreautor);

    Post findPost(String id);

    Post insertPost(Post post);

    void deletePost(String id);
}
