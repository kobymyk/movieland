package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.GenreDao;
import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.MovieCompound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class JdbcGenreDao extends EntityTemplate<Genre> implements GenreDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private String insertReference;

    public JdbcGenreDao() {
        super();
        setEntityClass(Genre.class);
    }

    @Override
    @Transactional
    public void addReference(MovieCompound movie) {
        log.trace("addReference");
        List<Genre> genres = movie.getMovieGenres();
        // todo: getParams
        SqlParameterSource[] params = new MapSqlParameterSource[genres.size()];
        int i = 0;
        for (Genre item : genres) {
            params[i++] = new MapSqlParameterSource()
                    //.addValue("movieId", movie.getId())
                    .addValue("countryId", item.getId());
        }

        namedJdbcTemplate.batchUpdate(insertReference, params);
    }

    @Override
    @Transactional
    public void editReference(MovieCompound movie) {
        // todo:
    }

    @Autowired
    public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Autowired
    @Qualifier("insertMovieGenres")
    public void setInsertReference(String insertReference) {
        this.insertReference = insertReference;
    }
}
