package ar.edu.unrn.couchbaseblogs;

import ar.edu.unrn.couchbaseblogs.api.PageService;
import ar.edu.unrn.couchbaseblogs.api.PostService;
import ar.edu.unrn.couchbaseblogs.model.Page;
import ar.edu.unrn.couchbaseblogs.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CouchbaseblogsApplicationTests {

  @Test
  void contextLoads() {}

  @Autowired
  @Qualifier("couchBasePageServiceImpl")
  private PageService pageService;

  @Autowired
  @Qualifier("couchBasePostServiceImpl")
  private PostService postService;

  @Test
  void testPageServiceInsertAndFind() {
    // Crear una página de prueba
    Page testPage =
        Page.builder()
            .id("test-page-id-1")
            .title("Página de Prueba")
            .text("Contenido de prueba para la página")
            .author("Autor de Prueba")
            .date(LocalDate.now())
            .build();

    // Insertar la página
    Page insertedPage = pageService.insertPage(testPage);

    // Verificar que la página se insertó correctamente
    assertNotNull(insertedPage);
    assertEquals("test-page-id-1", insertedPage.getId());

    // Buscar la página por ID
    Page foundPage = pageService.findById("test-page-id-1");

    // Verificar que la página se encontró correctamente
    assertNotNull(foundPage);
    assertEquals("Página de Prueba", foundPage.getTitle());
    assertEquals("Autor de Prueba", foundPage.getAuthor());

    // Limpiar la base de datos después de la prueba
    pageService.deletePage("test-page-id-1");
  }

  @Test
  void testPostServiceInsertAndFind() {
    // Crear un post de prueba
    Post testPost =
        Post.builder()
            .id("test-post-id-1")
            .title("Post de Prueba")
            .text("Contenido de prueba para el post")
            .resume("Resumen del post de prueba")
            .tags(List.of("prueba", "test", "java"))
            .relatedLinks(List.of("http://example.com"))
            .author("Autor de Prueba")
            .date(LocalDate.now())
            .build();

    // Insertar el post
    Post insertedPost = postService.insertPost(testPost);

    // Verificar que el post se insertó correctamente
    assertNotNull(insertedPost);
    assertEquals("test-post-id-1", insertedPost.getId());

    // Buscar el post por ID
    Post foundPost = postService.findPost("test-post-id-1");

    // Verificar que el post se encontró correctamente
    assertNotNull(foundPost);
    assertEquals("Post de Prueba", foundPost.getTitle());
    assertEquals(3, foundPost.getTags().size());

    // Limpiar la base de datos después de la prueba
    postService.deletePost("test-post-id-1");
  }

  @Test
  void testPageServiceUpdate() {
    // Crear una página inicial
    Page testPage =
        Page.builder()
            .id("test-page-update")
            .title("Página Original")
            .text("Contenido original")
            .author("Autor Original")
            .date(LocalDate.now())
            .build();

    pageService.insertPage(testPage);

    // Actualizar la página
    testPage.setTitle("Página Actualizada");
    testPage.setText("Contenido actualizado");
    Page updatedPage = pageService.insertPage(testPage);

    // Verificar actualización
    assertNotNull(updatedPage);
    assertEquals("Página Actualizada", updatedPage.getTitle());
    assertEquals("Contenido actualizado", updatedPage.getText());

    // Verificar que los cambios persisten
    Page foundPage = pageService.findById("test-page-update");
    assertEquals("Página Actualizada", foundPage.getTitle());

    // Limpiar la base de datos después de la prueba
    pageService.deletePage("test-page-update");
  }

  @Test
  void testPostServiceUpdate() {
    // Crear un post inicial
    Post testPost =
        Post.builder()
            .id("test-post-update")
            .title("Post Original")
            .text("Contenido original")
            .resume("Resumen original")
            .tags(List.of("original", "test"))
            .author("Autor Original")
            .date(LocalDate.now())
            .build();

    postService.insertPost(testPost);

    // Actualizar el post
    testPost.setTitle("Post Actualizado");
    testPost.setResume("Resumen actualizado");
    testPost.setTags(List.of("actualizado", "test", "modificado"));

    Post updatedPost = postService.insertPost(testPost);

    // Verificar actualización
    assertNotNull(updatedPost);
    assertEquals("Post Actualizado", updatedPost.getTitle());
    assertEquals("Resumen actualizado", updatedPost.getResume());
    assertEquals(3, updatedPost.getTags().size());

    // Limpiar la base de datos después de la prueba
    postService.deletePost("test-post-update");
  }

  @Test
  void testPostServiceFindByText() {
    // Crear posts con etiquetas específicas
    Post post1 =
        Post.builder()
            .id("test-post-tag-1")
            .title("Post con etiqueta Java")
            .text("Contenido sobre Java")
            .tags(List.of("java", "programación"))
            .author("Autor Test")
            .date(LocalDate.now())
            .build();

    Post post2 =
        Post.builder()
            .id("test-post-tag-2")
            .title("Post con etiqueta Spring")
            .text("Contenido sobre Spring y Java")
            .tags(List.of("spring", "java", "framework"))
            .author("Autor Test")
            .date(LocalDate.now())
            .build();

    postService.insertPost(post1);
    postService.insertPost(post2);

    // Buscar posts por etiqueta
    List<Post> javaPosts = postService.searchPosts("java");

    // Verificar resultados
    assertNotNull(javaPosts);
    assertTrue(javaPosts.size() >= 2);
    assertTrue(javaPosts.stream().anyMatch(p -> p.getId().equals("test-post-tag-1")));
    assertTrue(javaPosts.stream().anyMatch(p -> p.getId().equals("test-post-tag-2")));

    // Buscar otra etiqueta
    List<Post> springPosts = postService.searchPosts("spring");
    assertFalse(springPosts.isEmpty());
    assertTrue(springPosts.stream().anyMatch(p -> p.getId().equals("test-post-tag-2")));
    assertFalse(springPosts.stream().anyMatch(p -> p.getId().equals("test-post-tag-1")));

    // Limpiar la base de datos después de la prueba
    postService.deletePost("test-post-tag-1");
    postService.deletePost("test-post-tag-2");
  }

  @Test
  void testPostServiceFindByAuthor() {
    // Crear posts con diferentes autores
    Post post1 =
        Post.builder()
            .id("test-post-author-1")
            .title("Post de Autor1")
            .text("Contenido de Autor1")
            .author("Autor1")
            .date(LocalDate.now())
            .build();

    Post post2 =
        Post.builder()
            .id("test-post-author-2")
            .title("Post de Autor2")
            .text("Contenido de Autor2")
            .author("Autor2")
            .date(LocalDate.now())
            .build();

    postService.insertPost(post1);
    postService.insertPost(post2);

    // Buscar posts por autor
    List<Post> autor1Posts = postService.getPostsByAuthor("Autor1");

    // Verificar resultados
    assertNotNull(autor1Posts);
    assertTrue(autor1Posts.stream().anyMatch(p -> p.getId().equals("test-post-author-1")));
    assertFalse(autor1Posts.stream().anyMatch(p -> p.getId().equals("test-post-author-2")));

    // Limpiar la base de datos después de la prueba
    postService.deletePost("test-post-author-1");
    postService.deletePost("test-post-author-2");
  }

  @Test
  void testPostServiceDate() {
    // Crear posts con diferentes fechas
    Post post1 =
        Post.builder()
            .id("test-post-date-1")
            .title("Post de Fecha1")
            .text("Contenido de Fecha1")
            .author("Autor1")
            .date(LocalDate.of(2023, 10, 1))
            .build();

    Post post2 =
        Post.builder()
            .id("test-post-date-2")
            .title("Post de Fecha2")
            .text("Contenido de Fecha2")
            .author("Autor2")
            .date(LocalDate.of(2023, 10, 2))
            .build();

    postService.insertPost(post1);
    postService.insertPost(post2);

    // Buscar posts
    Post firstPost = postService.findPost("test-post-date-1");
    Post secondPost = postService.findPost("test-post-date-2");

    // Verificar resultados
    assertNotNull(firstPost);
    assertNotNull(secondPost);
    assertEquals(firstPost.getDate(), LocalDate.of(2023, 10, 1));
    assertEquals(secondPost.getDate(), LocalDate.of(2023, 10, 2));
    // Limpiar la base de datos después de la prueba
    postService.deletePost("test-post-date-1");
    postService.deletePost("test-post-date-2");
  }
}
