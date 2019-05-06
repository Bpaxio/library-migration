package ru.otus.bbpax.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.bbpax.model.mongo.Genre;
import ru.otus.bbpax.model.psql.GenreJPA;

@Component
public class GenreProcessor implements ItemProcessor<Genre, GenreJPA> {

    @Override
    public GenreJPA process(Genre genre) throws Exception {
        GenreJPA genreJPA = new GenreJPA(genre.getName());
        return genreJPA;
    }
}
