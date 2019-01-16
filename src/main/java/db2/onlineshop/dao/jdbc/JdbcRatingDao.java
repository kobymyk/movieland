package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.RatingDao;
import db2.onlineshop.entity.Rating;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcRatingDao implements RatingDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private SessionFactory sessionFactory;
    private Session session;

    @Override
    public void add(Rating rating) {
        log.trace("add:rating={}", rating);
        Query procedure = session.getNamedQuery("addRating")
                .setParameter("p_movie_id", rating.getMovieId())
                .setParameter("p_user_id", rating.getUserId())
                .setParameter("p_rating", rating.getRating());
        procedure.executeUpdate();
    }

    @Override
    public Rating getByMovie(int movieId, int userId) {
        log.trace("getByMovie:movieId={};userId={}", movieId, userId);
        Rating result =  (Rating) session.byNaturalId(Rating.class)
                .using("movieId", movieId)
                .using("userId", userId)
                .load();
        log.trace("getByMovie:result={}", result);

        return result;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        try {
            this.session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            this.session = sessionFactory.openSession();
        }
    }
}
