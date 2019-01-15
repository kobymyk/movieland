package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.RatingDao;
import db2.onlineshop.dao.jdbc.mapper.RatingMapper;
import db2.onlineshop.entity.Rating;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public class JdbcRatingDao implements RatingDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final RatingMapper ROW_MAPPER = new RatingMapper();

    SessionFactory sessionFactory;

    private BasicDataSource dataSource;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private String selectByMovie;

    @Override
    public void add(Rating rating) {
        int movieId = rating.getMovieId();
        log.trace("add:movieId={}", movieId);

        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("p_movie_id", movieId)
            .addValue("p_user_id", rating.getUser().getId())
            .addValue("p_rating", rating.getRating());
        log.trace("add:params={}", params);

        SimpleJdbcCall procAdd = new SimpleJdbcCall(dataSource)
                .withCatalogName("pkg_movie")
                .withProcedureName("set_movie_rating");
        Map outParams = procAdd.execute(params);
    }

    @Override
    public Rating getByMovie(int movieId, int userId) {
        log.trace("getByMovie:movieId={};userId={}", movieId, userId);
        Rating result =  (Rating) getCurrentSession().get(Rating.class, movieId);
        log.trace("getByMovie:result={}", result);

        return result;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;

        namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Autowired
    @Qualifier("selectByMovieRating")
    public void setSelectByMovie(String selectByMovie) {
        this.selectByMovie = selectByMovie;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
