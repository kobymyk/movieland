package db2.onlineshop.entity.model;


import db2.onlineshop.entity.Review;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class MovieReview extends Review {
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
        return "MovieReview{" +
                "movieId=" + movieId +
                ", id=" + id +
                ", text='" + text + '\'' +
                ", user=" + userId +
                '}';
    }
}
