package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.MovieDao;
import db2.onlineshop.dao.jdbc.builder.QueryBuilder;
import db2.onlineshop.dao.jdbc.mapper.MovieFullMapper;
import db2.onlineshop.dao.jdbc.mapper.MovieMapper;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.RequestParams;
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
public class JdbcMovieDao implements MovieDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final MovieMapper SIMPLE_MAPPER = new MovieMapper();
    private static final MovieFullMapper FULL_MAPPER = new MovieFullMapper(SIMPLE_MAPPER);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private String selectAll;
    private String selectRandom;
    private String selectByGenre;
    private String selectById;
    private String insertRow;

    @Override
    public List<Movie> getAll(RequestParams param) {
        long startTime = System.currentTimeMillis();

        String sql = selectAll;
        if (param != null) {
            sql = new QueryBuilder(selectAll).addSort(param).build();
        }
        log.trace("getAll:sql={}", sql);

        List<Movie> result = jdbcTemplate.query(sql, SIMPLE_MAPPER);
        log.debug("getAll:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Override
    public List<Movie> getRandom(int size) {
        long startTime = System.currentTimeMillis();
        log.debug("getRandom:size={}", size);
        List<Movie> result = jdbcTemplate.query(selectRandom, SIMPLE_MAPPER, size);
        log.debug("getRandom:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Override
    public List<Movie> getByGenre(int genreId) {
        long startTime = System.currentTimeMillis();
        log.debug("getByGenre:genreId={}", genreId);
        List<Movie> result = jdbcTemplate.query(selectByGenre, SIMPLE_MAPPER, genreId);
        log.debug("getByGenre:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Override
    public Movie getById(int id) {
        long startTime = System.currentTimeMillis();
        log.debug("getById:id={}", id);
        Movie result = jdbcTemplate.queryForObject(selectById, FULL_MAPPER, id);
        log.debug("getById:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Override
    public int add(Movie movie) {
        log.debug("add");
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", movie.getName())
                .addValue("nameNative", movie.getNameNative())
                .addValue("yearOfRelease", movie.getYearOfRelease())
                .addValue("description", movie.getDescription())
                .addValue("price", movie.getPrice())
                .addValue("picturePath", movie.getPicturePath());

        namedJdbcTemplate.update(insertRow, params, keyHolder, new String[]{"id"});

        int result = keyHolder.getKey().intValue();
        log.trace("add:result={}", result);

        return result;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        //-Duser.country=EN -Duser.language=en
        this.jdbcTemplate = jdbcTemplate;
    }
    @Autowired
    public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Autowired
    @Qualifier("selectAllMovies")
    public void setSelectAll(String selectAll) {
        this.selectAll = selectAll;
    }
    @Autowired
    @Qualifier("selectRandomMovies")
    public void setSelectRandom(String selectRandom) {
        this.selectRandom = selectRandom;
    }
    @Autowired
    @Qualifier("selectByGenreMovies")
    public void setSelectByGenre(String selectByGenre) {
        this.selectByGenre = selectByGenre;
    }
    @Autowired
    @Qualifier("selectByIdMovie")
    public void setSelectById(String selectById) {
        this.selectById = selectById;
    }
    @Autowired
    @Qualifier("insertMovie")
    public void setInsertRow(String insertRow) { this.insertRow = insertRow; }
}
