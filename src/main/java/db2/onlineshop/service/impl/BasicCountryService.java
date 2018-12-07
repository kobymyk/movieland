package db2.onlineshop.service.impl;

import db2.onlineshop.dao.CountryDao;
import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.CountryService;
import db2.onlineshop.service.MovieEnricher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicCountryService implements CountryService, MovieEnricher {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private CountryDao countryDao;

    @Override
    public List<Country> getByMovie(int movieId) {
        List<Country> result = countryDao.getByMovie(movieId);
        log.info("getByMovie:result.size={}", result.size());
        log.trace("getByMovie:result={}", result);

        return result;
    }

    @Override
    public void enrich(Movie movie) {
        int movieId = movie.getId();
        List<Country> countries = getByMovie(movieId);

        movie.setCountries(countries);
    }

    @Autowired
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }
}
