## Base de Datos: Couchbase

Couchbase es una base de datos NoSQL de tipo documental, lo que significa que almacena datos en formato JSON. A diferencia de las bases de datos relacionales, Couchbase no utiliza tablas con esquemas rígidos, sino que permite una mayor flexibilidad en la estructura de los datos.

### Particularidades de Couchbase

1.  **Modelo de Datos Flexible:**
    *   **Documentos JSON:** Los datos se almacenan en documentos JSON, lo que permite una estructura de datos flexible y dinámica. No es necesario definir un esquema fijo de antemano.

2.  **Escalabilidad Horizontal:**
    *   **Distribuida:** Couchbase está diseñada para ser distribuida en múltiples nodos, lo que permite escalar horizontalmente añadiendo más servidores al clúster.
    *   **Alta Disponibilidad:** La replicación de datos entre nodos asegura la alta disponibilidad y la tolerancia a fallos.

3.  **Rendimiento:**
    *   **Caché en Memoria:** Couchbase utiliza una caché en memoria para acelerar las operaciones de lectura y escritura.
    *   **Indexación:** Permite crear índices secundarios para optimizar las consultas.

4.  **Consultas:**
    *   **N1QL (SQL para JSON):** Couchbase ofrece un lenguaje de consulta llamado N1QL, que es similar a SQL pero adaptado para trabajar con documentos JSON.
    *   **Consultas Flexibles:** N1QL permite realizar consultas complejas sobre los datos, incluyendo joins, agrupaciones y agregaciones.

5.  **Buckets y Colecciones:**
    *   **Buckets:** Son contenedores lógicos para los datos. Un clúster de Couchbase puede tener múltiples buckets.
    *   **Colecciones:** Dentro de un bucket, se pueden crear colecciones para organizar los documentos.

### Uso en la Aplicación

En esta aplicación, se utilizan dos colecciones dentro del bucket `couchbase-blogs`:

*   **pages:** Almacena documentos que representan páginas web.
*   **posts:** Almacena documentos que representan publicaciones de blog.

### Consultas en la Aplicación

Las consultas en la aplicación se realizan utilizando mayormente el CouchbaseTemplate de Spring Data Couchbase. Para las proyecciones se utiliza la anotación de @Query y un DTO ya que el template no acepta queries personalizadas con parámetros
A continuación, se describen las principales consultas y cómo se implementaron:

1.  **Obtener los últimos 4 posts:**
    *   **Objetivo:** Recuperar los 4 posts más recientes.
    *   **Implementación:** Se utiliza `template.findByQuery` con un `Sort` descendente por el campo `date` y un `limit` de 4.
        ```java
            template.findByQuery(Post.class).matching(new Query().with(Sort.by(Sort.Order.desc("date"))).limit(4)).all().stream().toList();
        ```
    *   **N1QL equivalente:**
        ```sql
        SELECT * FROM `couchbase-blogs`.`_default`.`posts` ORDER BY date DESC LIMIT 4;
        ```

   2.  **Obtener posts por autor:**
       *   **Objetivo:** Recuperar todos los posts de un autor específico.
       *   **Implementación:** Se utiliza `template.findByQuery` con un `QueryCriteria` que filtra por el campo `author`.
       ```java
        template.findByQuery(Post.class).matching(new Query().addCriteria(QueryCriteria.where("author").is(nombreautor));).all();
        ``` 
       *   **N1QL equivalente:**
           ```sql
           SELECT * FROM `couchbase-blogs`.`_default`.`posts` WHERE author = "nombre_del_autor";
           ```

      3.  **Buscar posts por texto:**
          *   **Objetivo:** Buscar posts que contengan un texto específico en el campo `text`.
          *   **Implementación:** Se utiliza `template.findByQuery` con un `QueryCriteria` que utiliza `containing(true, text)` para realizar una búsqueda de texto.
          ```java
          template.findByQuery(Post.class).matching(new Query().addCriteria(QueryCriteria.where("text").containing(true, text));).all();
          ```
          *   **N1QL equivalente:**
              ```sql
              SELECT * FROM `couchbase-blogs`.`_default`.`posts` WHERE text LIKE "%texto_a_buscar%";
              ```

4.  **Contar posts por autor:**
    *   **Objetivo:** Contar la cantidad de posts para cada autor
    *   **Implementación:** Se utiliza una consulta N1QL personalizada en el repositorio `PostRepository` con la anotación `@Query`. Se utiliza un DTO para hacer la proyección de los datos.
    ```java
    @Query("SELECT UUID() as __id, COUNT(*) AS count, author " +
            "FROM #{#n1ql.bucket}.#{#n1ql.scope}.posts " +
            "GROUP BY author")
    AuthorPostCount[] contarLosPostsPorAutor();
    ```
