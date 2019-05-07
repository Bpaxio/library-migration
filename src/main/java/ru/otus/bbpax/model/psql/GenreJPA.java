package ru.otus.bbpax.model.psql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Vlad Rakhlinskii
 * Created on 10.01.2019.
 */
@Entity
@Table(name = "genre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "books")
public class GenreJPA {
    @Id
    @SequenceGenerator(name = "genre_seq_gen",
            sequenceName = "genre_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_seq_gen")
    private Long id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
    List<BookJPA> books;

    public GenreJPA(String name) {
        this.name = name;
    }

    public GenreJPA(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
