package ar.edu.unrn.couchbaseblogs.services;

import ar.edu.unrn.couchbaseblogs.api.PostService;
import ar.edu.unrn.couchbaseblogs.dto.AuthorPostCount;
import ar.edu.unrn.couchbaseblogs.model.Post;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.query.QueryOptions;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CouchBasePostServiceImpl extends CouchBaseService implements PostService {
    private final String collectionName = "posts";
  public CouchBasePostServiceImpl(Cluster couchBaseCluster) {
    super(couchBaseCluster);
  }
//TODO: Change collection name when created
  @Override
  public List<Post> getLatest4Posts() {
    return executeOperation(
        collection -> {
          String query =
              "SELECT * FROM "+ bucket.name() +" WHERE type = 'post' ORDER BY date DESC LIMIT 4";
          return cluster.query(query).rowsAs(Post.class);
        },
        collectionName);
  }

  @Override
  public List<Post> getPostsByAuthor(String nombreautor) {
    return executeOperation(
        collection -> {
          String query = "SELECT * FROM "+ bucket.name() +" WHERE type = 'post' AND author = $1";
          return cluster
              .query(query, QueryOptions.queryOptions().parameters(JsonArray.from(nombreautor)))
              .rowsAs(Post.class);
        },
            collectionName);
  }

  @Override
  public List<Post> searchPosts(String text) {
    return executeOperation(
        collection -> {
          String query =
              "SELECT * FROM "+ bucket.name() +" WHERE type = 'post' AND (title LIKE $1 OR text LIKE $1)";
          return cluster
              .query(
                  query, QueryOptions.queryOptions().parameters(JsonArray.from("%" + text + "%")))
              .rowsAs(Post.class);
        },
            collectionName);
  }

  @Override
  public List<AuthorPostCount> getPostCounts() {
    return executeOperation(
        collection -> {
          String query =
              "SELECT author, COUNT(*) AS postCount FROM "+ bucket.name() +" WHERE type = 'post' GROUP BY author";
          return cluster.query(query).rowsAs(AuthorPostCount.class);
        },
            collectionName);
  }

  @Override
  public List<Post> findPost(String id) {
    return executeOperation(
        collection -> {
          String query = "SELECT * FROM "+ bucket.name() +" WHERE type = 'post' AND id = $1";
          return cluster
              .query(query, QueryOptions.queryOptions().parameters(JsonArray.from(id)))
              .rowsAs(Post.class);
        },
            collectionName);
  }

  @Override
  public Post insertPost(Post post) {
    return executeOperation(
        collection -> {
          collection.insert(post.getId(), post);
          return post;
        },
            collectionName);
  }
}
