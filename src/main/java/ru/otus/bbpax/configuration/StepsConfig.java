package ru.otus.bbpax.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.bbpax.listener.ProcessListener;
import ru.otus.bbpax.listener.ReadListener;
import ru.otus.bbpax.listener.WriteListener;
import ru.otus.bbpax.model.mongo.Author;
import ru.otus.bbpax.model.mongo.Book;
import ru.otus.bbpax.model.mongo.Comment;
import ru.otus.bbpax.model.mongo.Genre;
import ru.otus.bbpax.model.psql.AuthorJPA;
import ru.otus.bbpax.model.psql.BookJPA;
import ru.otus.bbpax.model.psql.CommentJPA;
import ru.otus.bbpax.model.psql.GenreJPA;
import ru.otus.bbpax.processor.AuthorProcessor;
import ru.otus.bbpax.processor.BookProcessor;
import ru.otus.bbpax.processor.CommentProcessor;
import ru.otus.bbpax.processor.GenreProcessor;

@Slf4j
@Configuration
public class StepsConfig {
    public static final String GENRE_STEP_ID = "genreStep";
    public static final String AUTHOR_STEP_ID = "authorStep";
    public static final String BOOK_STEP_ID = "bookStep";
    public static final String COMMENT_STEP_ID = "commentStep";
    public static final Integer CHUNK_SIZE = 2;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean(GENRE_STEP_ID)
    public Step genreStep(MongoItemReader<Genre> reader, GenreProcessor processor, ItemWriter<GenreJPA> writer) {
        return stepBuilderFactory.get(GENRE_STEP_ID)
                .<Genre, GenreJPA> chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(new ReadListener<>(GENRE_STEP_ID))
                .listener(new WriteListener<>(GENRE_STEP_ID))
                .listener(new ProcessListener<>(GENRE_STEP_ID))
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {log.info("Начало пачки");}
                    public void afterChunk(ChunkContext chunkContext) {log.info("Конец пачки");}
                    public void afterChunkError(ChunkContext chunkContext) {log.info("Ошибка пачки");}
                })
                .build();
    }

    @Bean(AUTHOR_STEP_ID)
    public Step authorStep(MongoItemReader<Author> reader, AuthorProcessor processor, ItemWriter<AuthorJPA> writer) {
        return stepBuilderFactory.get(AUTHOR_STEP_ID)
                .<Author, AuthorJPA> chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(new ReadListener<>(AUTHOR_STEP_ID))
                .listener(new WriteListener<>(AUTHOR_STEP_ID))
                .listener(new ProcessListener<>(AUTHOR_STEP_ID))
                .build();
    }

    @Bean(BOOK_STEP_ID)
    public Step bookStep(MongoItemReader<Book> reader, BookProcessor processor, ItemWriter<BookJPA> writer) {
        return stepBuilderFactory.get(BOOK_STEP_ID)
                .<Book, BookJPA> chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(new ReadListener<>(BOOK_STEP_ID))
                .listener(new WriteListener<>(BOOK_STEP_ID))
                .listener(new ProcessListener<>(BOOK_STEP_ID))
                .build();
    }

    @Bean(COMMENT_STEP_ID)
    public Step commentStep(MongoItemReader<Comment> reader, CommentProcessor processor, ItemWriter<CommentJPA> writer) {
        return stepBuilderFactory.get(COMMENT_STEP_ID)
                .<Comment, CommentJPA> chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(new ReadListener<>(COMMENT_STEP_ID))
                .listener(new WriteListener<>(COMMENT_STEP_ID))
                .listener(new ProcessListener<>(COMMENT_STEP_ID))
                .build();
    }
}
