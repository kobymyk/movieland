package db2.onlineshop.service.impl;

import db2.onlineshop.dao.CountryDao;
import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.compound.MovieCompound;
import db2.onlineshop.entity.model.MovieCountry;
import db2.onlineshop.service.CountryService;
import db2.onlineshop.service.MovieChild;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicCountryService implements CountryService, MovieChild {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private CountryDao countryDao;

    @Override
    public List<Country> getByMovie(int movieId) {
        log.debug("getByMovie:movieId={}", movieId);
        List<Country> result = countryDao.getByMovie(movieId);
        log.debug("getByMovie:result.size={}", result.size());

        return result;
    }

    @Override
    public List<MovieCountry> getAll() {
        log.debug("getAll");
        List<MovieCountry> result = countryDao.getAll();
        log.debug("getAll:result.size={}", result.size());

        return result;
    }

    @Override
    public void enrich(MovieCompound movie) {
        int movieId = movie.getId();
        log.debug("enrich:movieId={}", movieId);
        List<Country> countries = getByMovie(movieId);

        movie.setCountries(countries);
    }

    @Override
    public void addReference(MovieCompound movie) {
        log.debug("addReference");
        countryDao.addReference(movie);
    }

    @Override
    public void editReference(MovieCompound movie) {
        countryDao.updateReference(movie);
    }

    @Autowired
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }
}
