package ar.edu.unrn.couchbaseblogs.controllers;

import ar.edu.unrn.couchbaseblogs.api.PageService;
import ar.edu.unrn.couchbaseblogs.model.Page;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pages")
@CrossOrigin(origins = "*")
public class PagesController {

    private final PageService pageService ;

    public PagesController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera una pagina en base a su id")
    public List<Page> getPageById(@PathVariable String id) {
        // Implementa la lógica para recuperar una página por su ID
        return this.pageService.findById(id);
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception exception) {
        exception.printStackTrace();
    }

}
