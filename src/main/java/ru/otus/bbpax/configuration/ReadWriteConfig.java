package ru.otus.bbpax.configuration;

import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.bbpax.model.mongo.Author;
import ru.otus.bbpax.model.mongo.Book;
import ru.otus.bbpax.model.mongo.Comment;
import ru.otus.bbpax.model.mongo.Genre;
import ru.otus.bbpax.model.psql.AuthorJPA;
import ru.otus.bbpax.model.psql.BookJPA;
import ru.otus.bbpax.model.psql.CommentJPA;
import ru.otus.bbpax.model.psql.GenreJPA;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;

@Configuration
public class ReadWriteConfig {

    @Bean
    public MongoItemReader<Genre> genreReader(MongoOperations mongoOps) {
        return new MongoItemReaderBuilder<Genre>()
                .name("genreReader")
                .template(mongoOps)
                .collection("genres")
                .targetType(Genre.class)
                .jsonQuery("{}")
                .sorts(new HashMap<String, Sort.Direction>() {
                    {
                        put("name", Sort.Direction.ASC);
                    }
                })
                .build();
    }

    @Bean
    public JpaItemWriter<GenreJPA> genreWriter(EntityManagerFactory factory) {
        JpaItemWriter<GenreJPA> wr = new JpaItemWriter<>();
        wr.setEntityManagerFactory(factory);
        return wr;
    }

    @Bean
    public MongoItemReader<Author> authorReader(MongoOperations mongoOps) {
        return new MongoItemReaderBuilder<Author>()
                .name("authorReader")
                .template(mongoOps)
                .collection("authors")
                .targetType(Author.class)
                .jsonQuery("{}")
                .sorts(new HashMap<String, Sort.Direction>() {
                    {
                        put("name", Sort.Direction.ASC);
                    }
                })
                .build();
    }

    @Bean
    public JpaItemWriter<AuthorJPA> authorWriter(EntityManagerFactory factory) {
        JpaItemWriter<AuthorJPA> wr = new JpaItemWriter<>();
        wr.setEntityManagerFactory(factory);
        return wr;
    }

    @Bean
    public MongoItemReader<Book> bookReader(MongoOperations mongoOps) {
        return new MongoItemReaderBuilder<Book>()
                .name("bookReader")
                .template(mongoOps)
                .collection("books")
                .targetType(Book.class)
                .jsonQuery("{}")
                .sorts(new HashMap<String, Sort.Direction>() {
                    {
                        put("name", Sort.Direction.ASC);
                    }
                })
                .build();
    }

    @Bean
    public JpaItemWriter<BookJPA> bookWriter(EntityManagerFactory factory) {
        JpaItemWriter<BookJPA> wr = new JpaItemWriter<>();
        wr.setEntityManagerFactory(factory);
        return wr;
    }

    @Bean
    public MongoItemReader<Comment> commentReader(MongoOperations mongoOps) {
        return new MongoItemReaderBuilder<Comment>()
                .name("commentReader")
                .template(mongoOps)
                .collection("comments")
                .targetType(Comment.class)
                .jsonQuery("{}")
                .sorts(new HashMap<String, Sort.Direction>() {
                    {
                        put("name", Sort.Direction.ASC);
                    }
                })
                .build();
    }

    @Bean
    public JpaItemWriter<CommentJPA> commentWriter(EntityManagerFactory factory) {
        JpaItemWriter<CommentJPA> wr = new JpaItemWriter<>();
        wr.setEntityManagerFactory(factory);
        return wr;
    }
}
