package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.ReviewDao;
import db2.onlineshop.dao.jdbc.mapper.ReviewMapper;
import db2.onlineshop.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class JdbcReviewDao implements ReviewDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final ReviewMapper ROW_MAPPER = new ReviewMapper();

    private JdbcTemplate jdbcTemplate;
    private String selectReviewsByMovie;

    @Override
    public List<Review> getByMovie(int movieId) {
        long startTime = System.currentTimeMillis();
        List<Review> result = jdbcTemplate.query(selectReviewsByMovie, ROW_MAPPER);
        log.info("getByMovie:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setSelectReviewsByMovie(String selectReviewsByMovie) {
        this.selectReviewsByMovie = selectReviewsByMovie;
    }
}
