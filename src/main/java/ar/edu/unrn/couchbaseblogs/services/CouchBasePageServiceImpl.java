package ar.edu.unrn.couchbaseblogs.services;

import ar.edu.unrn.couchbaseblogs.api.PageService;
import ar.edu.unrn.couchbaseblogs.model.Page;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.query.QueryOptions;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class CouchBasePageServiceImpl extends CouchBaseService implements PageService {
  private final String collectionName = "pages";

    public CouchBasePageServiceImpl(Cluster couchbaseCluster) {
        super(couchbaseCluster);
    }

  // TODO: Change collection name when created
  @Override
  public List<Page> findById(String id) {
    return executeOperation(
        collection -> {
          String query = "SELECT * FROM " + bucket.name() + " WHERE type = 'page' AND id = $1";
          return cluster
              .query(query, QueryOptions.queryOptions().parameters(JsonArray.from(id)))
              .rowsAs(Page.class);
        },
        collectionName);
  }

  @Override
  public Page insertPage(Page page) {
    return executeOperation(
        collection -> {
            // Convertir LocalDate a String antes de la inserción
            Map<String, Object> pageMap = new HashMap<>();
            pageMap.put("id", page.getId());
            pageMap.put("title", page.getTitle());
            pageMap.put("text", page.getText());
            pageMap.put("author", page.getAuthor());
            pageMap.put("date", page.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE)); // Convierte LocalDate a String

            collection.insert(page.getId(), pageMap);
          return page;
        },
        collectionName);
  }
}
