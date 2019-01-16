package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.ReviewDao;
import db2.onlineshop.dao.jdbc.mapper.ReviewMapper;
import db2.onlineshop.entity.Review;
import db2.onlineshop.entity.compound.ReviewItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class JdbcReviewDao implements ReviewDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final ReviewMapper ROW_MAPPER = new ReviewMapper();

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private String selectByMovie;
    private String insertRow;

    @Override
    public List<ReviewItem> getByMovie(int movieId) {
        List<ReviewItem> result = jdbcTemplate.query(selectByMovie, ROW_MAPPER, movieId);
        log.trace("getByMovie:result={}", result);

        return result;
    }

    @Override
    public int add(int movieId, Review review) {
        log.trace("add:movieId={}", movieId);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("movie_id", movieId)
            .addValue("user_id", review.getUser().getId())
            .addValue("text", review.getText());

        namedJdbcTemplate.update(insertRow, params, keyHolder, new String[]{"id"});
        int result = keyHolder.getKey().intValue();
        log.trace("add:result={}", result);

        return result;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Autowired
    public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Autowired
    @Qualifier("selectByMovieReview")
    public void setSelectByMovie(String selectByMovie) {
        this.selectByMovie = selectByMovie;
    }
    @Autowired
    @Qualifier("insertReview")
    public void setInsertRow(String insertRow) {
        this.insertRow = insertRow;
    }
}
