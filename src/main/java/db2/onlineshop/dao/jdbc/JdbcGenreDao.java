package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.GenreDao;
import db2.onlineshop.dao.jdbc.mapper.GenreMapper;
import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.compound.GenreItem;
import db2.onlineshop.entity.compound.MovieItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class JdbcGenreDao implements GenreDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final GenreMapper ROW_MAPPER = new GenreMapper();

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private String selectAll;
    private String selectByMovie;
    private String insertReference;

    @Override
    public List<Genre> getAll() {
        long startTime = System.currentTimeMillis();
        //List<Genre> result = jdbcTemplate.query(selectAll, ROW_MAPPER);
        log.info("getAll:duration={}", System.currentTimeMillis() - startTime);

        return null;
    }

    @Override
    public List<GenreItem> getByMovie(int movieId) {
        long startTime = System.currentTimeMillis();
        //List<GenreItem> result = jdbcTemplate.query(selectByMovie, ROW_MAPPER, movieId);
        log.info("getByMovie:duration={}", System.currentTimeMillis() - startTime);

        return null;
    }

    @Override
    @Transactional
    public void addReference(MovieItems movie) {
        log.trace("addReference");
        List<GenreItem> genres = movie.getGenres();
        // todo: getParams
        SqlParameterSource[] params = new MapSqlParameterSource[genres.size()];
        int i = 0;
        for (GenreItem item : genres) {
            params[i++] = new MapSqlParameterSource()
                    .addValue("movieId", movie.getId())
                    .addValue("countryId", item.getId());
        }
        log.trace("addReference:params={}", params);

        namedJdbcTemplate.batchUpdate(insertReference, params);
    }

    @Override
    @Transactional
    public void editReference(MovieItems movie) {
        // todo:
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
    @Qualifier("selectAllGenres")
    public void setSelectAll(String selectAll) {
        this.selectAll = selectAll;
    }

    @Autowired
    @Qualifier("selectByMovieGenres")
    public void setSelectByMovie(String selectByMovie) {
        this.selectByMovie = selectByMovie;
    }

    @Autowired
    @Qualifier("insertMovieGenres")
    public void setInsertReference(String insertReference) {
        this.insertReference = insertReference;
    }
}
