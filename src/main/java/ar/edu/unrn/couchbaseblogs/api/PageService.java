package ar.edu.unrn.couchbaseblogs.api;


import ar.edu.unrn.couchbaseblogs.model.Page;


public interface PageService {
    Page findById(String id);

    Page insertPage(Page page);

    void deletePage(String id);

    Page[] findAll();
}
