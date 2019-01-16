package db2.onlineshop.entity;

import db2.onlineshop.entity.compound.Rating;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@org.hibernate.annotations.NamedNativeQuery(
        name = "addRating",
        query = "CALL pkg_movie.set_movie_rating(:p_movie_id, :p_user_id, :p_rating)",
        resultClass = MovieRating.class
)

@Entity
@Table(name = "movie_rating")
public class MovieRating extends Rating {
    @NaturalId
    @Column(name = "movie_id")
    private int movieId;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
