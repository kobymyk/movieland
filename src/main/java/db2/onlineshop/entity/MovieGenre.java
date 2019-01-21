package db2.onlineshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "movie_genre")
public class MovieGenre {
    @Id
    private int id;
    @Column(name = "movie_id")
    private int movieId;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Genre genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "MovieGenre{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", genre=" + genre +
                '}';
    }
}
