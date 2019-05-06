package ru.otus.bbpax.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.bbpax.model.mongo.Author;
import ru.otus.bbpax.model.psql.AuthorJPA;

import java.util.Optional;

/**
 * @author Vlad Rakhlinskii
 * Created on 14.01.2019.
 */
@Repository
public interface AuthorRepo extends PagingAndSortingRepository<AuthorJPA, Long> {
    default Optional<AuthorJPA> findFirstByContent(Author author) {
        return findFirstByContent(author.getName(), author.getSurname(), author.getCountry());
    }

    @Query(value = "select a from AuthorJPA a where a.name = :name and a.surname = :surname and a.country = :country")
    Optional<AuthorJPA> findFirstByContent(@Param("name") String name,
                                           @Param("surname") String surname,
                                           @Param("country") String country);
}
