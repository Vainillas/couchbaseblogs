package ar.edu.unrn.couchbaseblogs.controllers;

import ar.edu.unrn.couchbaseblogs.api.PostService;
import ar.edu.unrn.couchbaseblogs.dto.AuthorPostCount;
import ar.edu.unrn.couchbaseblogs.model.Post;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/latest")
    public List<Post> getLatest4Posts() {
        // Implementa la lógica para recuperar los últimos 4 posts ordenados por fecha
        return this.postService.getLatest4Posts();
    }
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable String id) {
        // Implementa la lógica para recuperar un post por su ID
        return this.postService.findPost(id);
    }


    @GetMapping("/author/{nombreautor}")
    public List<Post> getPostsByAuthor(@PathVariable String nombreautor) {
        // Implementa la lógica para obtener todos los posts para un autor dado su nombre
        return this.postService.getPostsByAuthor(nombreautor);
    }
    @GetMapping("/byauthor/{nombreautor}")
    public AuthorPostCount getPostCountByAuthor(@PathVariable String nombreautor) {
        // Implementa la lógica para obtener la cantidad total de posts de un autor dado su nombre
        return this.postService.getPostCounts(nombreautor);
    }
    @GetMapping("/byauthor")
    public AuthorPostCount getPostCountByAuthor() {
        // Implementa la lógica para obtener la cantidad total de posts de un autor dado su nombre
        return this.postService.getPostCounts();
    }
    @GetMapping("/search/{text}")
    public List<Post> searchPosts(@PathVariable String text) {
        // Implementa la lógica para realizar una búsqueda libre dentro del texto del documento
        return this.postService.searchPosts(text);
    }

}
