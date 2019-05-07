package ru.otus.bbpax.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.bbpax.model.mongo.Book;
import ru.otus.bbpax.model.psql.BookJPA;

import java.util.List;
import java.util.Optional;

/**
 * @author Vlad Rakhlinskii
 * Created on 14.01.2019.
 */
@Repository
public interface BookRepo extends PagingAndSortingRepository<BookJPA, Long> {

    default List<BookJPA> findAllByContent(Book book) {
        return findAllByContent(
                book.getName(),
                book.getPublicationDate(),
                book.getPublishingOffice()
        );
    }

    @Query(value = "select b from BookJPA b where b.name = :name " +
            "and b.publicationDate = :publicationDate " +
            "and b.publishingOffice = :publishingOffice")
    List<BookJPA> findAllByContent(@Param("name") String name,
                                   @Param("publicationDate") Integer publicationDate,
                                   @Param("publishingOffice") String publishingOffice);
}
