package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.CountryDao;
import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.MovieCompound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class JdbcCountryDao extends EntityTemplate<Country> implements CountryDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private NamedParameterJdbcTemplate namedJdbcTemplate;
    private SimpleJdbcCall jdbcCall;

    private BasicDataSource dataSource;

    private String insertReference;
    private String updateReference;

    public JdbcCountryDao() {
        super();
        setEntityClass(Country.class);
    }

    @Override
    @Transactional
    public void addReference(MovieCompound movie) {
        log.trace("addReference");
        List<Country> countries = movie.getCountries();
        // todo: getParams
        SqlParameterSource[] params = new MapSqlParameterSource[countries.size()];
        int i = 0;
        for (Country country : countries) {
            params[i++] = new MapSqlParameterSource()
                    .addValue("movieId", movie.getMovie().getId())
                    .addValue("countryId", country.getId());
        }
        //log.trace("addReference:params={}", params);

        namedJdbcTemplate.batchUpdate(insertReference, params);
    }

    @Override
    @Transactional
    public void updateReference(MovieCompound movie) {
        String countryIds = movie.getCountries().stream()
                .map(p -> String.valueOf(p.getId()))
                .collect(Collectors.joining(",", "{\"result:\" [", "] }"));

        SqlParameterSource params = new MapSqlParameterSource()
                //.addValue("p_movie_id", movie.getId())
                .addValue("p_country_ids", countryIds);

        jdbcCall.withProcedureName("update_movie_country")
            .execute(params);
    }

    @Autowired
    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;

        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcCall = new SimpleJdbcCall(dataSource);
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
