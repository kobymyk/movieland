package db2.onlineshop.service.impl;

import db2.onlineshop.dao.CountryDao;
import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.compound.CountryItem;
import db2.onlineshop.entity.compound.MovieItems;
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
    public List<CountryItem> getByMovie(int movieId) {
        log.debug("getByMovie:movieId={}", movieId);
        List<CountryItem> result = countryDao.getByMovie(movieId);
        log.debug("getByMovie:result.size={}", result.size());

        return result;
    }

    @Override
    public List<Country> getAll() {
        log.debug("getAll");
        List<Country> result = countryDao.getAll();
        log.debug("getAll:result.size={}", result.size());

        return result;
    }

    @Override
    public void enrich(MovieItems movie) {
        int movieId = movie.getId();
        log.debug("enrich:movieId={}", movieId);
        List<CountryItem> countries = getByMovie(movieId);

        movie.setCountries(countries);
    }

    @Override
    public void addReference(MovieItems movie) {
        log.debug("addReference");
        countryDao.addReference(movie);
    }

    @Override
    public void editReference(MovieItems movie) {
        countryDao.updateReference(movie);
    }

    @Autowired
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }
}
