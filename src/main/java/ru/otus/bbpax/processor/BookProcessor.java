package ru.otus.bbpax.processor;

import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.bbpax.model.mongo.Book;
import ru.otus.bbpax.model.psql.AuthorJPA;
import ru.otus.bbpax.model.psql.BookJPA;
import ru.otus.bbpax.model.psql.GenreJPA;
import ru.otus.bbpax.repository.AuthorRepo;
import ru.otus.bbpax.repository.BookRepo;
import ru.otus.bbpax.repository.GenreRepo;

@Component
@AllArgsConstructor
public class BookProcessor implements ItemProcessor<Book, BookJPA> {

    private final AuthorRepo authorRepo;
    private final GenreRepo genreRepo;
    private final BookRepo bookRepo;

    @Override
    public BookJPA process(Book book) throws Exception {
        return bookRepo.findAllByContent(book)
                .stream()
                .findFirst()
                .orElseGet(() -> anotherBook(book));
    }

    private BookJPA anotherBook(Book book) {
        AuthorJPA author = authorRepo.findAllByContent(book.getAuthor()).stream()
                .findFirst().orElse(null);
        GenreJPA genre = genreRepo.findFirstByName(book.getGenre().getName())
                .orElseThrow(null);
        BookJPA bookJPA = new BookJPA();
        bookJPA.setAuthor(author);
        bookJPA.setGenre(genre);
        bookJPA.setName(book.getName());
        bookJPA.setPrice(book.getPrice());
        bookJPA.setPublicationDate(book.getPublicationDate());
        bookJPA.setPublishingOffice(book.getPublishingOffice());
        return bookJPA;
    }
}
