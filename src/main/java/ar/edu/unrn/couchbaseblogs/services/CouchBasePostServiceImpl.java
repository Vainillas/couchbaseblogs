package ar.edu.unrn.couchbaseblogs.services;

import ar.edu.unrn.couchbaseblogs.api.PostService;
import ar.edu.unrn.couchbaseblogs.dto.AuthorPostCount;
import ar.edu.unrn.couchbaseblogs.model.Post;
import com.couchbase.client.java.Cluster;

import java.util.List;

import com.couchbase.client.java.json.JsonObject;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.query.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CouchBasePostServiceImpl extends CouchBaseService implements PostService {
    private final CouchbaseTemplate template;

  public CouchBasePostServiceImpl(Cluster couchBaseCluster, CouchbaseTemplate template) {
    super(couchBaseCluster);
      this.template = template;
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
    String statement = "SELECT COUNT(*) AS count, author FROM `couchbase-blogs`.posts WHERE author = $author GROUP BY author";
    JsonObject placeHolder = JsonObject.create().put("author", nombreautor);
    N1QLQuery query = new N1QLQuery(N1QLExpression.x(statement));
    return template.findByQuery(AuthorPostCount.class).matching(query).oneValue();
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
