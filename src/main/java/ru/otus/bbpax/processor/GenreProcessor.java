package ru.otus.bbpax.processor;

import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.bbpax.model.mongo.Genre;
import ru.otus.bbpax.model.psql.GenreJPA;
import ru.otus.bbpax.repository.GenreRepo;

@Component
@AllArgsConstructor
public class GenreProcessor implements ItemProcessor<Genre, GenreJPA> {
    private final GenreRepo genreRepo;

    @Override
    public GenreJPA process(Genre genre) throws Exception {
        return genreRepo.findFirstByName(genre.getName())
                .orElse(new GenreJPA(genre.getName()));
    }
}
