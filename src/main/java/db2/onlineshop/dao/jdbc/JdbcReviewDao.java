package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.ReviewDao;
import db2.onlineshop.dao.jdbc.mapper.ReviewMapper;
import db2.onlineshop.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class JdbcReviewDao implements ReviewDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final ReviewMapper ROW_MAPPER = new ReviewMapper();

    private JdbcTemplate jdbcTemplate;

    private String selectByMovie;

    @Override
    public List<Review> getByMovie(int movieId) {
        long startTime = System.currentTimeMillis();
        List<Review> result = jdbcTemplate.query(selectByMovie, ROW_MAPPER, movieId);
        log.info("getByMovie:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    @Qualifier("selectByMovieReview")
    public void setSelectByMovie(String selectByMovie) {
        this.selectByMovie = selectByMovie;
    }
}
