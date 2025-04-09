package ar.edu.unrn.couchbaseblogs.api;

import couchbaseblogspringboot.dto.AuthorPostCount;
import couchbaseblogspringboot.model.Post;
import java.util.List;

public interface PostService {
    List<Post> getLatest4Posts();

    List<Post> getPostsByAuthor(String nombreautor);

    List<Post> searchPosts(String text);

    List<AuthorPostCount> getPostCounts();

    List<Post> findPost(String id);

    Post insertPost(Post post);
}
