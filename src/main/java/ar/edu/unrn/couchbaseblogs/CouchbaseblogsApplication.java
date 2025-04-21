package ar.edu.unrn.couchbaseblogs;

import ar.edu.unrn.couchbaseblogs.api.PageService;
import ar.edu.unrn.couchbaseblogs.api.PostService;
import ar.edu.unrn.couchbaseblogs.model.Page;
import ar.edu.unrn.couchbaseblogs.model.Post;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CouchbaseblogsApplication {

  private final PageService pageService;
  private final PostService postService;

  public CouchbaseblogsApplication(
      @Qualifier("couchBasePageServiceImpl") PageService pageService,
      @Qualifier("couchBasePostServiceImpl") PostService postService) {
    this.pageService = pageService;
    this.postService = postService;
  }

  public static void main(String[] args) {
    SpringApplication.run(CouchbaseblogsApplication.class, args);
  }

  private void insertPage() {
    LocalDateTime today = LocalDateTime.now().minusDays(10);
    Page page1 =
        Page.builder()
            .id("1")
            .title("Patagonia, tierra de dinosaurios.")
            .text(
                "Secretos sobre el surgimiento, desarrollo y extinción de los dinosaurios en la Patagonia.")
            .author("Aliberti Mateo")
            .date(today.toLocalDate())
            .build();

    Page page2 =
        Page.builder()
            .id("2")
            .title("Los autos de los 90 en Argentina")
            .text(
                "Un repaso por los autos que marcaron una época en Argentina. Desde el Volkswagen Gol hasta el Renault 19.")
            .author("Aliberti Mateo")
            .date(today.toLocalDate())
            .build();

    pageService.insertPage(page1);
    pageService.insertPage(page2);
  }

  private void insertPost() {
    LocalDateTime firstDate = LocalDateTime.now().minusDays(8);
    LocalDateTime secondDate = LocalDateTime.now().minusDays(10);
    Post post1 =
        Post.builder()
            .id("1")
            .title("El toro carnívoro: Carnotaurus Sastrei")
            .text(
                "El Toro Carnívoro (Carnotaurus) fue uno de los temibles depredadores del periódo Cretácico. Los huesos de este animal fueron locaizados en los campos de un estanciero de apellido Sastre que termnó por darle apellido a esta criatura que paseó sus diez metros de longitud lo que hoy se conoce como la Pampa de Gastre, en la provincia de Chubut.")
            .resume(
                "Uno de los mayores depredadores carnívoros de la Patagonia, el Carnotaurus Sastrei, fue descubierto en la provincia de Chubut.")
            .tags(Arrays.asList("Patagonia", "Cretácico", "Carnívoro"))
            .relatedLinks(
                Arrays.asList(
                    "https://www.patagonia.com.ar/dinosaurios/662_Carnotaurus+Sastrei.html",
                    "https://www.patagonia.com.ar/El+Choc%C3%B3n/207_Rub%C3%A9n+Carolini+el+descubridor+del+dinosaurio+carn%C3%ADvoro+m%C3%A1s+grande.html"))
            .author("Aliberti Mateo")
            .date(firstDate.toLocalDate())
            .build();
    Post post2 =
        Post.builder()
            .id("2")
            .title("El carnívoro más grande del mundo")
            .text(
                "El 25 de julio de 1993, el mecánico desocupado Rubén Carolini se topó con los huesos fosilizados del que se transformaría en el dinosaurio carnívoro más grande del mundo, que desplazó del reinado nada menos que al famosísimo Tyrannosaurus Rex de Estados Unidos.")
            .resume("El descubrimiento del Gigante del Sur")
            .tags(Arrays.asList("Patagonia", "Cretácico", "Carnívoro"))
            .relatedLinks(
                List.of("https://www.patagonia.com.ar/dinosaurios/663_Gigantosaurus+Carolini.html"))
            .author("Aliberti Mateo")
            .date(secondDate.toLocalDate())
            .build();

    Post post3 =
        Post.builder()
            .id("3")
            .title("El Volkswagen Gol: El auto más vendido de los 90")
            .text(
                "El Volkswagen Gol fue el auto más vendido de los 90 en Argentina. Un auto compacto, económico y confiable que se ganó el corazón de los argentinos.")
            .resume("El auto más vendido de los 90 en Argentina")
            .tags(Arrays.asList("Volkswagen", "Gol", "Argentina"))
            .relatedLinks(List.of("https://es.wikipedia.org/wiki/Volkswagen_Gol"))
            .author("Aliberti Mateo")
            .date(secondDate.toLocalDate())
            .build();
    Post post4 =
        Post.builder()
            .id("4")
            .title("El Renault 19: Un clásico de los 90")
            .text(
                "El Renault 19 fue uno de los autos más populares de los 90 en Argentina. Un auto familiar, cómodo y seguro que se ganó el respeto de los argentinos.")
            .resume("Un clásico de los 90 en Argentina")
            .tags(Arrays.asList("Renault", "19", "Argentina"))
            .relatedLinks(List.of("https://es.wikipedia.org/wiki/Renault_19"))
            .author("Aliberti Mateo")
            .date(firstDate.toLocalDate())
            .build();

    postService.insertPost(post1);
    postService.insertPost(post2);
    postService.insertPost(post3);
    postService.insertPost(post4);
  }

  @Bean
  public CommandLineRunner commandLineRunner() {
    return args -> {
       //insertPage();
       //insertPost();
    };
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*").allowedOrigins("http://localhost:3000");
      }
    };
  }
}
