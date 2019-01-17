package db2.onlineshop.entity.model;

import db2.onlineshop.entity.Genre;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "movie_genre")
public class MovieGenre extends Genre {
    @NaturalId
    @Column(name = "movie_id")
    private int movieId;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "MovieGenre{" +
                "movieId=" + movieId +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
