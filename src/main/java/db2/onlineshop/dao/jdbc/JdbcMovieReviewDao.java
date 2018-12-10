package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.MovieReviewDao;
import db2.onlineshop.entity.MovieReview;
import db2.onlineshop.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMovieReviewDao implements MovieReviewDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private String insertRow;

    @Override
    public int add(MovieReview movieReview) {
        int movieId = movieReview.getMovieId();
        log.info("add:movieId={}", movieId);
        MapSqlParameterSource params = getParams(movieReview);

        int result = namedJdbcTemplate.update(insertRow, params);
        log.debug("add:result={}", result);

        return result;
    }

    private MapSqlParameterSource getParams(MovieReview movieReview) {
        MapSqlParameterSource result = new MapSqlParameterSource();
        result.addValue("movie_id", movieReview.getMovieId());
        Review review = movieReview.getReview();
        result.addValue("user_id", review.getUser().getId());
        result.addValue("text", review.getText());
        log.trace("getParams:result=()", result);

        return result;
    }

    @Autowired
    public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Autowired
    @Qualifier("insertReview")
    public void setInsertRow(String insertRow) {
        this.insertRow = insertRow;
    }
}
