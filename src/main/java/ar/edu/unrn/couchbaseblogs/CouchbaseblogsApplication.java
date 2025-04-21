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
    LocalDateTime farDate = LocalDateTime.now().minusDays(200);
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
    postService.insertPost(post1);
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
    postService.insertPost(post2);

    Post post3 =

        Post.builder()
            .id("3")
            .title("El Argentinosaurus Huinculensis: El gigante de la Patagonia")
            .text(
                "El Argentinosaurus Huinculensis fue uno de los dinosaurios más grandes que jamás haya existido. Con un peso estimado de entre 70 y 100 toneladas, este gigante herbívoro dominó la Patagonia durante el período Cretácico.")
            .resume(
                "El Argentinosaurus Huinculensis, uno de los dinosaurios más grandes que jamás haya existido, dominó la Patagonia durante el período Cretácico.")
            .tags(Arrays.asList("Patagonia", "Cretácico", "Herbívoro"))
            .relatedLinks(
                    List.of(
                            "https://www.patagonia.com.ar/dinosaurios/661_Argentinosaurus+Huinculensis.html"))
            .author("Aliberti Mateo")
            .date(firstDate.toLocalDate())
            .build();
    postService.insertPost(post3);

    Post post4 =
        Post.builder()
            .id("4")
            .title("El Amargasaurus Cazaui: Un gigante con espinas")
            .text(
                "El Amargasaurus Cazaui fue un dinosaurio herbívoro que vivió en la Patagonia durante el período Cretácico. Este gigante se caracterizaba por tener una doble hilera de espinas a lo largo de su cuello y espalda.")
            .resume(
                "El Amargasaurus Cazaui, un dinosaurio herbívoro con una doble hilera de espinas a lo largo de su cuello y espalda, vivió en la Patagonia durante el período Cretácico.")
            .tags(Arrays.asList("Patagonia", "Cretácico", "Herbívoro"))
            .relatedLinks(
                    List.of(
                            "https://www.patagonia.com.ar/dinosaurios/659_Amargasaurus+Casuei.html"))
            .author("Aliberti Mateo")
            .date(secondDate.toLocalDate())
            .build();
    postService.insertPost(post4);

    Post post5 = Post.builder()
        .id("5")
        .title("El Mapusaurus Roseae: Un depredador en manada")
        .text(
            "El Mapusaurus Roseae fue un dinosaurio carnívoro que vivió en la Patagonia durante el período Cretácico. Este depredador se caracterizaba por cazar en manada, lo que le permitía abatir presas de gran tamaño.")
        .resume(
            "El Mapusaurus Roseae, un depredador que cazaba en manada, vivió en la Patagonia durante el período Cretácico.")
        .tags(Arrays.asList("Patagonia", "Cretácico", "Carnívoro"))
        .relatedLinks(List.of("https://es.wikipedia.org/wiki/Mapusaurus_roseae"))
        .author("Aliberti Mateo")
        .date(firstDate.toLocalDate())
        .build();
    postService.insertPost(post5);

    Post post6 =
        Post.builder()
            .id("6")
            .title("El Saltasaurus Loricatus: Un gigante acorazado")
            .text(
                "El Saltasaurus Loricatus fue un dinosaurio herbívoro que vivió en la Patagonia durante el período Cretácico. Este gigante se caracterizaba por tener una armadura de placas óseas a lo largo de su cuerpo.")
            .resume(
                "El Saltasaurus Loricatus, un dinosaurio herbívoro con una armadura de placas óseas a lo largo de su cuerpo, vivió en la Patagonia durante el período Cretácico.")
            .tags(Arrays.asList("Patagonia", "Cretácico", "Herbívoro"))
            .relatedLinks(List.of("https://es.wikipedia.org/wiki/Saltasaurus_loricatus"))
            .author("Aliberti Mateo")
            .date(secondDate.toLocalDate())
            .build();
    postService.insertPost(post6);

    Post post7 =
        Post.builder()
            .id("7")
            .title("El Herrerasaurus Ischigualastensis: Un depredador temprano")
            .text(
                "El Herrerasaurus Ischigualastensis fue uno de los dinosaurios carnívoros más antiguos que se conocen. Con un peso estimado de entre 200 y 300 kilogramos, este depredador vivió en la Patagonia durante el período Triásico.")
            .resume(
                "El Herrerasaurus Ischigualastensis, uno de los dinosaurios carnívoros más antiguos que se conocen, vivió en la Patagonia durante el período Triásico.")
            .tags(Arrays.asList("Patagonia", "Triásico", "Carnívoro"))
            .relatedLinks(
                    List.of("https://es.wikipedia.org/wiki/Herrerasaurus_ischigualastensis"))
            .author("Aliberti Mateo")
            .date(firstDate.toLocalDate())
            .build();
    postService.insertPost(post7);

    Post post8 =
        Post.builder()
            .id("8")
            .title("El Piatnitzkysaurus Floresi: Un depredador ágil")
            .text(
                "El Piatnitzkysaurus Floresi fue un dinosaurio carnívoro que vivió en la Patagonia durante el período Jurásico. Con un peso estimado de entre 400 y 500 kilogramos, este depredador se caracterizaba por su agilidad y velocidad.")
            .resume(
                "El Piatnitzkysaurus Floresi, un dinosaurio carnívoro ágil y veloz, vivió en la Patagonia durante el período Jurásico.")
            .tags(Arrays.asList("Patagonia", "Jurásico", "Carnívoro"))
            .relatedLinks(List.of("https://es.wikipedia.org/wiki/Piatnitzkysaurus_floresi"))
            .author("Aliberti Mateo")
            .date(secondDate.toLocalDate())
            .build();
    postService.insertPost(post8);

    Post post9 =
        Post.builder()
            .id("9")
            .title("El Volkswagen Gol: El auto más vendido de los 90")
            .text(
                "El Volkswagen Gol fue el auto más vendido de los 90 en Argentina. Un auto compacto, económico y confiable que se ganó el corazón de los argentinos.")
            .resume("El auto más vendido de los 90 en Argentina")
            .tags(Arrays.asList("Volkswagen", "Gol", "Argentina"))
            .relatedLinks(List.of("https://es.wikipedia.org/wiki/Volkswagen_Gol"))
            .author("Aliberti Mateo")
            .date(farDate.toLocalDate())
            .build();
    Post post10 =
        Post.builder()
            .id("10")
            .title("El Renault 19: Un clásico de los 90")
            .text(
                "El Renault 19 fue uno de los autos más populares de los 90 en Argentina. Un auto familiar, cómodo y seguro que se ganó el respeto de los argentinos.")
            .resume("Un clásico de los 90 en Argentina")
            .tags(Arrays.asList("Renault", "19", "Argentina"))
            .relatedLinks(List.of("https://es.wikipedia.org/wiki/Renault_19"))
            .author("Aliberti Mateo")
            .date(farDate.toLocalDate())
            .build();

    postService.insertPost(post9);
    postService.insertPost(post10);
  }

  @Bean
  public CommandLineRunner commandLineRunner() {
    return args -> {
      // insertPage();
      // insertPost();
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
