package db2.onlineshop.entity.main;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@NamedNativeQuery(
        name = "addRating",
        query = "CALL pkg_movie.set_movie_rating(:p_movie_id, :p_user_id, :p_rating)",
        resultClass = MovieRating.class
)
@Data
@Entity
@Table(name = "movie_rating")
public class MovieRating extends Rating {
    @NaturalId
    @Column(name = "movie_id")
    private int movieId;

}
