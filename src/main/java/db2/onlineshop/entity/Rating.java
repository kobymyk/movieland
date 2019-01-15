package db2.onlineshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "MOVIE_RATING")
public class Rating {
    @Id
    @Column(name = "movie_id")
    private int movieId;
    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "user_id")) })
    private User user;
    @Column(nullable = false)
    private double rating;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                ", user=" + user +
                ", rating=" + rating +
                '}';
    }
}
