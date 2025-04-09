package ar.edu.unrn.couchbaseblogs.api;


import couchbaseblogspringboot.model.Page;
import java.util.List;

public interface PageService {
    List<Page> findById(String id);

    Page insertPage(Page page);
}
