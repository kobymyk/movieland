package db2.onlineshop.service.impl;

import db2.onlineshop.dao.CountryDao;
import db2.onlineshop.dao.MovieCountryDao;
import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.MovieCountry;
import db2.onlineshop.service.CountryService;
import db2.onlineshop.service.MovieChild;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicCountryService implements CountryService, MovieChild {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private CountryDao countryDao;
    private MovieCountryDao movieCountryDao;

    @Override
    public List<Country> getAll() {
        log.debug("getAll");
        List<Country> result = countryDao.getAll();
        log.debug("getAll:result.size={}", result.size());

        return result;
    }

    @Override
    public void enrich(Movie movie) {
        int movieId = movie.getId();
        log.debug("enrich:movieId={}", movieId);
        List<MovieCountry> movieCountries = movieCountryDao.listByKey("movieId", movieId);
        List<Country> countries = new ArrayList<>(movieCountries.size());
        for (MovieCountry movieCountry : movieCountries) {
            countries.add(movieCountry.getCountry());
        }
        log.debug("enrich:countries={}", countries);
        movie.setCountries(countries);
    }

    @Override
    public void merge(Movie movie, Movie result) {
        result.setCountries(movie.getCountries());
    }

    @Override
    public void addReference(Movie movie) {
        int movieId = movie.getId();
        List<Country> countries = movie.getCountries();
        log.debug("addReference:movieId={};countries={}", movieId, countries);

        for (Country country : countries) {
            MovieCountry movieCountry = new MovieCountry();
            movieCountry.setMovieId(movieId);
            movieCountry.setCountry(country);
            movieCountryDao.add(movieCountry);
        }
    }

    @Autowired
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Autowired
    public void setMovieCountryDao(MovieCountryDao movieCountryDao) {
        this.movieCountryDao = movieCountryDao;
    }
}
