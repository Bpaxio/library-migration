package ru.otus.bbpax.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.otus.bbpax.model.psql.GenreJPA;

import java.util.Optional;

/**
 * @author Vlad Rakhlinskii
 * Created on 14.01.2019.
 */
@Repository
public interface GenreRepo extends PagingAndSortingRepository<GenreJPA, Long> {
    Optional<GenreJPA> findFirstByName(String name);
}
