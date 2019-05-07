package ru.otus.bbpax.model.psql;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Vlad Rakhlinskii
 * Created on 10.01.2019.
 */
@Entity
@Data
@Table(name = "book")
@NoArgsConstructor
public class BookJPA {

    @Id
    @SequenceGenerator(name = "book_seq_gen",
            sequenceName = "book_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_gen")
    private Long id;

    @Column
    private String name;

    @Column
    private Integer publicationDate;

    @Column
    private String publishingOffice;

    @Column
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private GenreJPA genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorJPA author;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "book")
    private List<CommentJPA> comments;

    public BookJPA(String name,
                   Integer publicationDate,
                   String publishingOffice,
                   BigDecimal price,
                   GenreJPA genre,
                   AuthorJPA author) {
        this.name = name;
        this.publicationDate = publicationDate;
        this.publishingOffice = publishingOffice;
        this.price = price;
        this.genre = genre;
        this.author = author;
    }

    public BookJPA(Long id,
                   String name,
                   Integer publicationDate,
                   String publishingOffice,
                   BigDecimal price,
                   GenreJPA genre,
                   AuthorJPA author) {
        this.id = id;
        this.name = name;
        this.publicationDate = publicationDate;
        this.publishingOffice = publishingOffice;
        this.price = price;
        this.genre = genre;
        this.author = author;
    }
}
