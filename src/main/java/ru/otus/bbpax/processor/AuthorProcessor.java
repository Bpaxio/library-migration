package ru.otus.bbpax.processor;

import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.bbpax.model.mongo.Author;
import ru.otus.bbpax.model.psql.AuthorJPA;
import ru.otus.bbpax.repository.AuthorRepo;

@Component
@AllArgsConstructor
public class AuthorProcessor implements ItemProcessor<Author, AuthorJPA> {
    private final AuthorRepo authorRepo;

    @Override
    public AuthorJPA process(Author author) throws Exception {
        return authorRepo.findAllByContent(author)
                .stream()
                .findFirst()
                .orElse(new AuthorJPA(author.getName(), author.getSurname(), author.getCountry()));
    }
}
