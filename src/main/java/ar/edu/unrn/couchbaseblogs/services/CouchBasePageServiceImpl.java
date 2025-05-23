package ar.edu.unrn.couchbaseblogs.services;

import ar.edu.unrn.couchbaseblogs.api.PageService;
import ar.edu.unrn.couchbaseblogs.model.Page;
import com.couchbase.client.java.Cluster;


import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CouchBasePageServiceImpl extends CouchBaseService implements PageService {
  private final CouchbaseTemplate template;

    public CouchBasePageServiceImpl(Cluster couchbaseCluster, CouchbaseTemplate template) {
        super(couchbaseCluster);
        this.template = template;
    }

  @Override
  @Transactional(readOnly = true)
  public Page findById(String id) {
    return template.findById(Page.class).one(id);
  }

  @Override
  public Page insertPage(Page page) {
    return template.upsertById(Page.class).one(page);
  }

  @Override
  public void deletePage(String id) {
    template.removeById(Page.class).one(id);
  }

  @Override
  public Page[] findAll() {
    return template.findByQuery(Page.class).all().toArray(new Page[0]);
  }
}
