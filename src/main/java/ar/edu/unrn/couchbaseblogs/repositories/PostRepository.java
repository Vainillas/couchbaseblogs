package ar.edu.unrn.couchbaseblogs.repositories;

import ar.edu.unrn.couchbaseblogs.dto.AuthorPostCount;
import ar.edu.unrn.couchbaseblogs.model.Post;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.Repository;

public interface PostRepository extends Repository<Post,String> {
    /**
     * Cuenta la cantidad de posts para un autor específico.
     * Utiliza el filtro automático de tipo (#{ #n1ql.filter}) para asegurar
     * que solo se cuenten documentos de tipo Post.
     * También usa #{ #n1ql.bucket} para referenciar el bucket actual.
     *
     * @param author El nombre del autor a buscar.
     * @return Un DTO con el conteo y el nombre del autor.
     */
    @Query("SELECT COUNT(*) AS count " +
            "FROM #{#n1ql.bucket}.#{#n1ql.scope}.posts " +
            "WHERE author = $1 " +
            "GROUP BY author")
    Number countPostsByAuthor(String author);

    @Query("SELECT UUID() as __id, COUNT(*) AS count, author " +
            "FROM #{#n1ql.bucket}.#{#n1ql.scope}.posts " +
            "GROUP BY author")
    AuthorPostCount[] contarLosPostsPorAutor();
}
