package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.GenreDao;
import db2.onlineshop.dao.MovieDao;
import db2.onlineshop.dao.jdbc.mapper.GenreMapper;
import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class JdbcGenreDao implements GenreDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final GenreMapper ROW_MAPPER = new GenreMapper();

    private JdbcTemplate jdbcTemplate;
    private String sqlSelectGenres;

    @Override
    public List<Genre> getAll() {
        long startTime = System.currentTimeMillis();
        log.debug("getAll:startTime={}", startTime);
        List<Genre> result = jdbcTemplate.query(sqlSelectGenres, ROW_MAPPER);
        log.info("getAll:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setSqlSelectGenres(String sqlSelectGenres) {
        this.sqlSelectGenres = sqlSelectGenres;
    }
}
