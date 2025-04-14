package ar.edu.unrn.couchbaseblogs.controllers;

import ar.edu.unrn.couchbaseblogs.api.PageService;
import ar.edu.unrn.couchbaseblogs.model.Page;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pages")
@CrossOrigin(origins = "*")
public class PagesController {

    private final PageService pageService ;

    public PagesController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping
    @Operation(summary = "Recupera todas las páginas")
    public Page[] getAllPages() {
        return this.pageService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera una pagina en base a su id")
    public Page getPageById(@PathVariable String id) {
        return this.pageService.findById(id);
    }
    @PostMapping
    @Operation(summary = "Inserta una página")
    public Page insertPage(@RequestBody Page page) {
        return this.pageService.insertPage(page);
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception exception) {
        exception.printStackTrace();
    }

}
