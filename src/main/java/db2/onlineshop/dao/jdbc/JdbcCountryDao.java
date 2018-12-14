package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.CountryDao;
import db2.onlineshop.dao.jdbc.mapper.CountryMapper;
import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class JdbcCountryDao implements CountryDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final CountryMapper ROW_MAPPER = new CountryMapper();

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;
    private SimpleJdbcCall jdbcCall;

    private BasicDataSource dataSource;

    private String selectByMovie;
    private String selectAll;
    private String insertReference;

    private String updateReference;

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
    public void addReference(Movie movie) {
        log.trace("addReference");
        List<Country> countries = movie.getCountries();
        // todo: getParams
        SqlParameterSource[] params = new MapSqlParameterSource[countries.size()];
        int i = 0;
        for (Country country : countries) {
            params[i++] = new MapSqlParameterSource()
                    .addValue("movieId", movie.getId())
                    .addValue("countryId", country.getId());
        }
        log.trace("addReference:params={}", params);

        namedJdbcTemplate.batchUpdate(insertReference, params);
    }

    @Override
    public void updateReference(Movie movie) {
        String countryIds = movie.getCountries().stream()
                .map(p -> String.valueOf(p.getId()))
                .collect(Collectors.joining(",", "{\"result:\" [", "] }"));

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("p_movie_id", movie.getId())
                .addValue("p_country_ids", countryIds);

        jdbcCall.withProcedureName("update_movie_country")
            .execute(params);
    }

    @Autowired
    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;
        //this.dataSource.setAccessToUnderlyingConnectionAllowed(true);

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcCall = new SimpleJdbcCall(dataSource);
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
    @Autowired
    @Qualifier("insertMovieCountry")
    public void setUpdateReference(String updateReference) {
        this.updateReference = updateReference;
    }
}
