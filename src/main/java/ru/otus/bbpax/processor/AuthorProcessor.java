package ru.otus.bbpax.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.bbpax.model.mongo.Author;
import ru.otus.bbpax.model.psql.AuthorJPA;

@Component
public class AuthorProcessor implements ItemProcessor<Author, AuthorJPA> {

    @Override
    public AuthorJPA process(Author author) throws Exception {
        AuthorJPA authorJPA = new AuthorJPA(author.getName(), author.getSurname(), author.getCountry());
        return authorJPA;
    }
}
