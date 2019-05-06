package ru.otus.bbpax.processor;

import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.bbpax.model.mongo.Comment;
import ru.otus.bbpax.model.psql.AuthorJPA;
import ru.otus.bbpax.model.psql.BookJPA;
import ru.otus.bbpax.model.psql.CommentJPA;
import ru.otus.bbpax.model.psql.GenreJPA;
import ru.otus.bbpax.repository.AuthorRepo;
import ru.otus.bbpax.repository.BookRepo;
import ru.otus.bbpax.repository.GenreRepo;

import java.util.Objects;

@Component
@AllArgsConstructor
public class CommentProcessor implements ItemProcessor<Comment, CommentJPA> {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final GenreRepo genreRepo;

    @Override
    public CommentJPA process(Comment comment) throws Exception {
        AuthorJPA author = authorRepo.findFirstByContent(comment.getBook().getAuthor())
                .orElseThrow(Exception::new);
        GenreJPA genre = genreRepo.findFirstByName(comment.getBook().getGenre().getName())
                .orElseThrow(Exception::new);
        BookJPA bookJPA = bookRepo.findAllByContent(comment.getBook())
                .stream()
                .filter(book -> Objects.equals(book.getAuthor().getName(), author.getName())
                        || Objects.equals(book.getAuthor().getSurname(), author.getSurname())
                        || Objects.equals(book.getAuthor().getCountry(), author.getCountry())
                )
                .filter(book -> Objects.equals(book.getGenre().getName(), genre.getName()))
                .findFirst()
                .orElseThrow(Exception::new);
        CommentJPA commentJPA = new CommentJPA(comment.getUsername(),comment.getMessage(), bookJPA);
        commentJPA.setCreated(comment.getCreated());
        return commentJPA;
    }
}
