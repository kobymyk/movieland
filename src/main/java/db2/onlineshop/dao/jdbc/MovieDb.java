package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.MovieDao;
import db2.onlineshop.dao.jdbc.mapper.MovieMapper;
import db2.onlineshop.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
public class MovieDb implements MovieDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final MovieMapper ROW_MAPPER = new MovieMapper();

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String sqlSelectMovies;
    @Autowired
    private String sqlRandomMovies;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        Locale.setDefault(Locale.ENGLISH);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getAll() {
        long startTime = System.currentTimeMillis();
        log.debug("getAll:startTime={}", startTime);
        List<Movie> result = jdbcTemplate.query(sqlSelectMovies, ROW_MAPPER);
        log.info("getAll:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Override
    public List<Movie> getRandom(int size) {
        long startTime = System.currentTimeMillis();
        log.debug("getRandom:startTime={}", startTime);
        List<Movie> result = jdbcTemplate.query(sqlRandomMovies, ROW_MAPPER, size);
        log.info("getRandom:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }
}
