package db2.onlineshop.entity;

import db2.onlineshop.entity.Country;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "all_movie_country")
public class MovieCountry extends Country {
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
        return "Country{" +
                "movieId=" + movieId +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
