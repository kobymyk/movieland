package db2.onlineshop.entity;


import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "all_movie_genre")
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
        return "Genre{" +
                "movieId=" + movieId +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
