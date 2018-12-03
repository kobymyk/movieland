package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.MovieDao;
import db2.onlineshop.dao.jdbc.builder.QueryBuilder;
import db2.onlineshop.dao.jdbc.mapper.MovieMapper;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.SortParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class JdbcMovieDao implements MovieDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final MovieMapper ROW_MAPPER = new MovieMapper();

    private JdbcTemplate jdbcTemplate;

    private String sqlSelectMovies;
    private String sqlRandomMovies;
    private String sqlGenreIdMovies;
    private String selectMovieById;

    @Override
    public List<Movie> getAll(SortParam param) {
        long startTime = System.currentTimeMillis();

        String sql = sqlSelectMovies;
        if (param != null) {
            sql = new QueryBuilder(sqlSelectMovies).addSort(param).build();
        }
        log.trace("getAll:sql={}", sql);

        List<Movie> result = jdbcTemplate.query(sql, ROW_MAPPER);
        log.debug("getAll:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Override
    public List<Movie> getRandom(int size) {
        long startTime = System.currentTimeMillis();
        log.debug("getRandom:size={}", size);
        List<Movie> result = jdbcTemplate.query(sqlRandomMovies, ROW_MAPPER, size);
        log.debug("getRandom:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Override
    public List<Movie> getByGenre(int genreId) {
        long startTime = System.currentTimeMillis();
        log.debug("getByGenre:genreId={}", genreId);
        List<Movie> result = jdbcTemplate.query(sqlGenreIdMovies, ROW_MAPPER, genreId);
        log.debug("getByGenre:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Override
    public Movie getById(int id) {
        long startTime = System.currentTimeMillis();
        log.debug("getById:id={}", id);
        // todo: custom row mapper | DTO
        Movie result = jdbcTemplate.queryForObject(selectMovieById, ROW_MAPPER, id);
        log.debug("getById:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        //-Duser.country=EN -Duser.language=en
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setSqlSelectMovies(String sqlSelectMovies) {
        this.sqlSelectMovies = sqlSelectMovies;
    }

    @Autowired
    public void setSqlRandomMovies(String sqlRandomMovies) {
        this.sqlRandomMovies = sqlRandomMovies;
    }

    @Autowired
    public void setSqlGenreIdMovies(String sqlGenreIdMovies) {
        this.sqlGenreIdMovies = sqlGenreIdMovies;
    }

    @Autowired
    public void setSelectMovieById(String selectMovieById) {
        this.selectMovieById = selectMovieById;
    }
}
