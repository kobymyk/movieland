package db2.onlineshop.entity.main;

import javax.persistence.*;

@Entity
@Table(name = "movie_genre")
public class MovieGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movieGenreSequence")
    @SequenceGenerator(name = "movieGenreSequence", sequenceName = "movie_genre_seq", allocationSize = 1)
    private int id;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "MovieGenre{" +
                "id=" + id +
                ", movie=" + movie +
                ", genre=" + genre +
                '}';
    }
}
