package db2.onlineshop.service.impl;

import db2.onlineshop.dao.GenericDao;
import db2.onlineshop.dao.MovieCountryDao;
import db2.onlineshop.entity.common.Country;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.MovieCountry;
import db2.onlineshop.service.CountryService;
import db2.onlineshop.service.Child;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService, Child<Movie> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private GenericDao<Country> countryDao;
    @Autowired
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
        log.debug("addReference:movieId={}", movieId);

        for (Country country : movie.getCountries()) {
            MovieCountry movieCountry = new MovieCountry();
            movieCountry.setMovieId(movieId);
            movieCountry.setCountry(country);
            movieCountryDao.add(movieCountry);
        }
    }

}
