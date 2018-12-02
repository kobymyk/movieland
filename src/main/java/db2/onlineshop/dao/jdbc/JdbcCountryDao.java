package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.CountryDao;
import db2.onlineshop.dao.jdbc.mapper.CountryMapper;
import db2.onlineshop.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class JdbcCountryDao implements CountryDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final CountryMapper ROW_MAPPER = new CountryMapper();

    private JdbcTemplate jdbcTemplate;
    private String selectCountriesByMovie;

    @Override
    public List<Country> getByMovie(int movieId) {
        long startTime = System.currentTimeMillis();
        List<Country> result = jdbcTemplate.query(selectCountriesByMovie, ROW_MAPPER);
        log.info("getByMovie:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setSelectCountriesByMovie(String selectCountriesByMovie) {
        this.selectCountriesByMovie = selectCountriesByMovie;
    }
}
