package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.CountryDao;
import db2.onlineshop.dao.jdbc.mapper.CountryMapper;
import db2.onlineshop.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class JdbcCountryDao implements CountryDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final CountryMapper ROW_MAPPER = new CountryMapper();

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private String selectByMovie;
    private String selectAll;
    private String insertReference;

    @Override
    public List<Country> getByMovie(int movieId) {
        log.trace("getByMovie");
        List<Country> result = jdbcTemplate.query(selectByMovie, ROW_MAPPER, movieId);
        log.trace("getByMovie:result", result);

        return result;
    }

    @Override
    public List<Country> getAll() {
        log.trace("getAll");
        List<Country> result = jdbcTemplate.query(selectAll, ROW_MAPPER);
        log.trace("getAll:result", result);

        return result;
    }

    @Override
    public void addReference(int movieId, List<Integer> countryIds) {
        int countrySize = countryIds.size();
        log.trace("addReference:countrySize={}", countrySize);

        MapSqlParameterSource[] params = new MapSqlParameterSource[countrySize];
        for (int i = 0; i < countrySize; i++) {
            params[i] = new MapSqlParameterSource()
                    .addValue("movieId", movieId)
                    .addValue("genreId", countryIds.get(i));
        }
        log.trace("addReference:params={}", params);

        namedJdbcTemplate.batchUpdate(insertReference, params);
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
    @Qualifier("selectByMovieCountries")
    public void setSelectByMovie(String selectByMovie) {
        this.selectByMovie = selectByMovie;
    }

    @Autowired
    @Qualifier("selectAllCountries")
    public void setSelectAll(String selectAll) {
        this.selectAll = selectAll;
    }

    @Autowired
    @Qualifier("insertMovieCountry")
    public void setInsertReference(String insertReference) {
        this.insertReference = insertReference;
    }
}
