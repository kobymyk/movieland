package db2.onlineshop.dao.generic.impl;

import db2.onlineshop.dao.generic.RatingDao;
import db2.onlineshop.entity.main.MovieRating;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcRatingDao extends AbstractGenericDao<MovieRating> implements RatingDao {

    @Override
    public void add(MovieRating movieRating) {
        log.trace("add:rating={}", movieRating);
        Query procedure = getSession().getNamedQuery("addRating")
                .setParameter("p_movie_id", movieRating.getMovieId())
                .setParameter("p_user_id", movieRating.getUserId())
                .setParameter("p_rating", movieRating.getRating());
        procedure.executeUpdate();
    }

    @Override
    public MovieRating getRating(int movieId, int userId) {
        log.trace("getByMovie:movieId={};userId={}", movieId, userId);
        MovieRating result =  (MovieRating) getSession().byNaturalId(MovieRating.class)
                .using("movieId", movieId)
                .using("userId", userId)
                .load();
        log.trace("getByMovie:result={}", result);

        return result;
    }
}
