package db2.onlineshop.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "movie_rating")
public class Rating {
    @Id
    @NaturalId
    @Column(name = "movie_id")
    private int movieId;
    @NaturalId
    @Column(name = "user_id")
    private int userId;
    @Column(nullable = false)
    private double rating;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "movieId=" + movieId +
                ", userId=" + userId +
                ", rating=" + rating +
                '}';
    }
}
