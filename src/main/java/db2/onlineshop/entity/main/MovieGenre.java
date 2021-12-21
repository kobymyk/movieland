package db2.onlineshop.entity.main;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "movie_genre")
public class MovieGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movieGenreSequence")
    @SequenceGenerator(name = "movieGenreSequence", sequenceName = "movie_genre_seq", allocationSize = 1)
    private int id;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
}
