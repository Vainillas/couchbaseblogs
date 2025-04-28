package ar.edu.unrn.couchbaseblogs.services;

import ar.edu.unrn.couchbaseblogs.api.PostService;
import ar.edu.unrn.couchbaseblogs.dto.AuthorPostCount;
import ar.edu.unrn.couchbaseblogs.model.Post;
import ar.edu.unrn.couchbaseblogs.repositories.PostRepository;
import com.couchbase.client.java.Cluster;

import java.util.List;

import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.query.*;
import org.springframework.data.couchbase.repository.query.N1qlCountQueryCreator;
import org.springframework.data.couchbase.repository.query.N1qlQueryCreator;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CouchBasePostServiceImpl extends CouchBaseService implements PostService {
    private final CouchbaseTemplate template;
    private final PostRepository postRepository;

  public CouchBasePostServiceImpl(Cluster couchBaseCluster, CouchbaseTemplate template, PostRepository postRepository) {
    super(couchBaseCluster);
      this.template = template;
      this.postRepository = postRepository;
  }
  @Override
  public List<Post> getLatest4Posts() {
    return template.findByQuery(Post.class).matching(
            new Query().with(Sort.by(Sort.Order.desc("date"))).limit(4)).all().stream().toList();
  }

  @Override
  public List<Post> getPostsByAuthor(String nombreautor) {
      Query query = new Query().addCriteria(QueryCriteria.where("author").is(nombreautor));
      return template.findByQuery(Post.class).matching(query).all();
  }

  @Override
  public List<Post> searchPosts(String text) {
    Query query = new Query().addCriteria(QueryCriteria.where("text").containing(true, text));
    return template.findByQuery(Post.class).matching(query).all();
  }

  @Override
  public AuthorPostCount getPostCounts(String nombreautor) {
    Number count = postRepository.countPostsByAuthor(nombreautor);
      return AuthorPostCount.builder().count(count.intValue()).author(nombreautor).build();
  }
  @Override
  public AuthorPostCount getPostCounts() {
    Number count = postRepository.countPostsByAuthor();
    return AuthorPostCount.builder().count(count.intValue()).author("ALA").build();
  }

  @Override
  public Post findPost(String id) {
    return template.findById(Post.class).one(id);
  }

  @Override
  public Post insertPost(Post post) {
    return template.upsertById(Post.class).one(post);
  }

  @Override
  public void deletePost(String id) {
    template.removeById(Post.class).one(id);
  }
}
