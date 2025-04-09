package ar.edu.unrn.couchbaseblogs.api;


import ar.edu.unrn.couchbaseblogs.model.Page;

import java.util.List;

public interface PageService {
    List<Page> findById(String id);

    Page insertPage(Page page);
}
